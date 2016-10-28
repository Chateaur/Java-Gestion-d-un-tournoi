import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Permet de faire de lien entre les equipes et la BDD
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class EquipeDAO {

/********** Attributs **********/
	
	/**
	* Parametres de connexion a la base de donnees oracle
	* URL, LOGIN et PASS sont des constantes
	* 1521 
	*/
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN="admin";
	final static String PASS="admin";
	
/********** Methodes **********/
	
	/**
	 * Constructeur EquipeDAO
	 */
	public EquipeDAO ()
	{
		
	}
	
	/**
	 * M�thode qui permet d'ajouter une equipe dans la BDD
	 * @param equipe
	 * @return retour
	 */
	public int ajouterEquipe(Equipe equipe)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;
		//connexion a la base de donnees
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//preparation de l'instruction SQL, chaque ? represente une valeur a communiquer dans l'insertion
			//les getters permettent de recuperer les valeurs des attributs souhaites de nouvArticle
			ps = con.prepareStatement("INSERT INTO equipe (id,nom_equipe) VALUES (sequence_equipe_id.NEXTVAL, ?)");
			ps.setString(1,equipe.getNom());

			//Execution de la requete
			retour=ps.executeUpdate();
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du preparedStatement et de la connexion
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}
	
	/**
	* Permet de recuperer une equipe de la base de donn�e � partir de l'equipe
	* permet de voir si un nom d'equipe est pr�sent dans la bdd
	* @param le nom de l'�quipe
	* @return l'�quipe (si elle est trouv�e)
	* @return null si aucune equipe ne correspond � l'equipe renseign�e
	*/
	public Equipe getEquipe(String nom_equipe)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Equipe retour=null;

		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM equipe WHERE nom_equipe = ?");
			ps.setString(1,nom_equipe);

			//on execute la requete
			//rs contient un pointeur situe jusute avant la premiere ligne retournee
			rs=ps.executeQuery();
			//passe a la premiere (et unique) ligne retournee
			if(rs.next())
			retour=new Equipe(rs.getString("nom_equipe"));
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
		//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}
	
	/**
	* Permet de recuperer une equipe � partir de son ID
	* @param reference l'ID de l'�quipe � recuperer
	* @return l'�quipe
	* @return null si aucune equipe ne correspond � cet ID
	*/
	public Equipe getEquipe_ID(int ID)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Equipe retour=null;

		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM equipe WHERE id = ?");
			ps.setInt(1,ID);

			//on execute la requete
			//rs contient un pointeur situe jusute avant la premiere ligne retournee
			rs=ps.executeQuery();
			//passe a la premiere (et unique) ligne retournee
			if(rs.next())
			retour=new Equipe(rs.getString("nom_equipe"));
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
		//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}
	
	/**
	* Permet de recuperer l'ID d'une �quipe � partir de son nom
	* @param le nom de l'�quipe dont on veut l'id
	* @return int ID de l'�quipe
	* @return null si aucune equipe ne correspond � ce nom
	*/
	public int get_ID_Equipe (String nom_equipe)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		int retour = 0;

		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM equipe WHERE nom_equipe = ?");
			ps.setString(1,nom_equipe);

			//on execute la requete
			//rs contient un pointeur situe jusute avant la premiere ligne retournee
			rs=ps.executeQuery();
			//passe a la premiere (et unique) ligne retournee
			if(rs.next())
			retour= rs.getInt("id");
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
		//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}
	
	/**
	 * Permet de supprimer une equipe
	 * Si on supprime une �quipe les participants auront automatiquement la valeur null gr�ce � la contrainte
	 * de cle etrangere
	 * @param equipe � supprimer
	 */
	public void Supprimer_Equipe (Equipe equi)
	{
		String rq;
		
		//On supprimer l'�quipe de la BDD
		rq = "DELETE from equipe WHERE nom_equipe = " + "'" + equi.getNom()+ "'";
		BDD.execute_REQUETE(rq);	
	}
	
	/**
	* Permet de recuperer toutes les equipes stockees dans la table equipe
	* @return retour une ArrayList d'�quipe
	*/
	public static List<Equipe> getListeEquipe()
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Equipe> retour=new ArrayList<Equipe>();

		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM equipe");

			//on execute la requete
			rs=ps.executeQuery();
			//on parcourt les lignes du resultat
			while(rs.next())
				if (!rs.getString("nom_equipe").equals("void"))
				{
					retour.add(new Equipe(rs.getString("nom_equipe")));
				}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du rs, du preparedStatement et de la connexion
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}
	
	/**
	 * Permet de r�up�rer les participants d'une �quipe
	 * @param String nomEquipe
	 */
	public static List<String> getListeParticipantsEquipe(String nomEquipe)
	{
		//Variables pour la r�cuperation du nom et prenom
		String nom;
		String prenom;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<String> retour=new ArrayList<String>();
		
		//EquipeDAO pour r�cup�rer l'ID d'une �quipe � partir de son nom
		EquipeDAO e = new EquipeDAO();

		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT nom, prenom FROM participant WHERE equipe = '" + e.get_ID_Equipe(nomEquipe) + "'");

			//on execute la requete
			rs=ps.executeQuery();
			//on parcourt les lignes du resultat
			while(rs.next())
			{
				nom = rs.getString("nom");
				prenom = rs.getString ("prenom");
				retour.add(nom + " " + prenom);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du rs, du preparedStatement et de la connexion
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}	
	
	/**
	 * M�thode qui permet de voir si une equipe existe dans la base de donn�es
	 * @param Equipe e
	 * @return trouve
	 */
	public boolean check_equipe(Equipe e)
	{
		boolean trouve=true;

		//La m�thode getEquipe(String nom_equipe) retourne null si l'�quipe n'existe pas dans La BDD
		if (this.getEquipe(e.getNom()) == null)
		{
			trouve = false;
		}
		return trouve;
	}
	
	/**
	 * M�thode qui permet de changer le nom d'une �quipe dans la BDD
	 * @param ancienNom
	 * @param nouveauNom
	 */
	public void modifier_nom_equipe(String ancienNom, String nouveauNom)
	{
		//rq contiendra la requete
		String rq;
		
		//on cr�e un objet BDD pour �xecuter la requ�te
		BDD bdd = new BDD();
		
		//On change le nom de l'�quipe des participants
		rq = "UPDATE participant SET equipe = '" + nouveauNom + "' WHERE equipe = '" + ancienNom + "'";
		bdd.execute_REQUETE(rq);
		
		//On change le nom de l'�quipe dans la table �quipe
		rq = "UPDATE equipe SET nom_equipe = '" + nouveauNom + "' WHERE nom_equipe = '" + ancienNom + "'";
		bdd.execute_REQUETE(rq);
	}
}