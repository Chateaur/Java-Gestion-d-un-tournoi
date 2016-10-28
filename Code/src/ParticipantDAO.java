import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
* Classe d'acces aux donnees contenues dans la table participant
* @author Romain
* @author Florent
* @version 1.0
* */
public class ParticipantDAO {

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
	* Constructeur de la classe
	*
	*/
	public ParticipantDAO(){
		
	}
	
	/**
	* Permet d'ajouter un participant dans la table participant
	* l'ID du partcipant est produit automatiquement par la base de donnees en utilisant une sequence
	* Le mode est auto-commit par defaut : chaque insertion est validee
	* @param nouvParticipant le participant a ajouter
	* @return le nombre de ligne ajoutees dans la table
	*/
	public static int ajouter(Participant nouvParticipant)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;
		//connexion a la base de donnees
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			//On instancie une equipeDAO pour récupérer le nom de l'équipe
			EquipeDAO e = new EquipeDAO ();
			
			//preparation de l'instruction SQL, chaque ? represente une valeur a communiquer dans l'insertion
			//les getters permettent de recuperer les valeurs des attributs d'un participant
			ps = con.prepareStatement("INSERT INTO participant (id,nom, prenom, sexe, datedenaissance, nationalite, equipe) VALUES (sequence_participant_id.NEXTVAL, ?, ?, ?, ?, ?,?)");
			ps.setString(1,nouvParticipant.getNom());
			ps.setString(2,nouvParticipant.getPrenom());
			ps.setString(3, nouvParticipant.getSexe());
			ps.setString(4,  nouvParticipant.getDateDeNaissance());
			ps.setString(5, nouvParticipant.getNationalite());
			
			//On vérifie si le participant à une équipe. Si oui, on l'enregistre dans la BDD...
			if (nouvParticipant.getEquipe()!= null)
				{
					ps.setInt(6, e.get_ID_Equipe(nouvParticipant.getEquipe()));
				}
			//...Si non, on enregistre "" pour son equipe
			else
				{
					ps.setString(6, "");
				}
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
	* Permet de recuperer un participant à partir de son id
	* @param l'id du participant à récupérer
	* @return le participant
	* @return null si aucun participant ne correspond a cet ID
	*/
	public Participant getParticipant(int participantID)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Participant retour=null;

		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM participant WHERE id = ?");
			ps.setInt(1,participantID);
			
			//On instancie une equipeDAO pour récupérer le nom de l'équipe
			EquipeDAO e = new EquipeDAO ();

			//on execute la requete
			//rs contient un pointeur situe juste avant la premiere ligne retournee
			rs=ps.executeQuery();
			//passe a la premiere (et unique) ligne retournee
			if(rs.next())
			retour=new Participant(rs.getString("nom"),rs.getString("prenom"), rs.getString("sexe"), rs.getString("datedenaissance"), rs.getString("Nationalite"), e.getEquipe_ID(rs.getInt("Equipe")).getNom());
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
	* Permet de supprimer un participant de la BDD
	* @param  le participant à supprimer
	*/
	public static void Supprimer_Participant (Participant p)
	{
		String rq;
		
		//On supprimer l'équipe de la BDD
		rq = "DELETE from participant WHERE nom = " + "'" + p.getNom()+ "'" + " and prenom = " + "'" + p.getPrenom()+ "'";
		BDD.execute_REQUETE(rq);
	}

	/**
	* Permet de recuperer tous les participants stockes dans la table participant
	* @return une ArrayList de participants
	*/
	public static List<Participant> getListeParticipant()
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Participant> retour=new ArrayList<Participant>();
		
		//Declaration des attributs d'un participant
		String nom;
		String prenom;
		String datedenaissance;
		String nationalite;
		String sexe;
		int id_equipe;
		
		//Initialisation des attributs
		nom = "";
		prenom = "";
		datedenaissance = "";
		nationalite = "";
		
		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM participant");
			
			//On instancie une equipeDAO pour récupérer le nom de l'équipe
			EquipeDAO e = new EquipeDAO ();

			//on execute la requete
			rs=ps.executeQuery();
			//on parcourt les lignes du resultat
			while(rs.next())
			{
				//On récupère les valeurs de la BDD dans les variables
				nom=rs.getString("nom");
				prenom = rs.getString("prenom");
				sexe = rs.getString("sexe");
				datedenaissance = rs.getString("datedenaissance");
				nationalite =  rs.getString("nationalite");
				id_equipe = rs.getInt("equipe");
				
				//On regarde si le participant n'avait pas d'équipe
				//la méthode wasNull permet de savoir si le dernier enregistrement parcourur (ici l'équipe) est null
				if ( rs.wasNull() == false)
				{
					//Si l'équipe n'est pas nulle on cherche son nom
					retour.add(new Participant(nom,prenom,sexe,datedenaissance,nationalite,e.getEquipe_ID(id_equipe).getNom()));
				}
				else
				{
					//Sinon on cree un participant sans equipe, "non renseignee"
					retour.add(new Participant(nom,prenom,sexe,datedenaissance,nationalite,"Non renseignee"));
				}
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
	 * Methode qui permet d'affecter une equipe a un participant
	 * @param nom_equipe
	 * @param nom_participant
	 * @param prenom_participant
 	*/
	public void Lier_Equipe_Participant (String nom_equipe, String nom_participant, String prenom_participant)
	{
		String rq;
		
		//on instancie une equipeDAO pour récupérer l'id de l'équipe
		EquipeDAO e = new EquipeDAO();
		
		rq="UPDATE participant SET equipe = '" + e.get_ID_Equipe(nom_equipe) + "' WHERE nom = '" + nom_participant + "' and prenom = '" + prenom_participant + "'";
		
		BDD.execute_REQUETE(rq);
	}
	
	/**
	 * Methode qui permet de voir si un participant existe dans la base de donnees
	 * @param nom
	 * @param prennom
	 * @return trouve un boolean (true si le participant existe false si il n'existe pas)
	 */
	public boolean Check_participant (String nom, String prenom)
	{
		boolean trouve = true;
		int id;
		
		id = get_id_participant(nom, prenom);
		
		if (getParticipant(id) == null)
		{
			trouve = false;
		}
		return trouve;	
	}
	
	/**
	* Permet de recuperer l'ID d'un participant à partir de son nom et prenom
	* @param nom du participant
	* @param prenom du participant
	* @return int id du participant
	* @return null si aucun participant ne correspond a ce nom et prenom
	*/
	public int get_id_participant (String nom, String prenom)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		int retour = 0;

		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT id FROM participant WHERE nom = ? and prenom = ?");
			ps.setString(1,nom);
			ps.setString(2, prenom);

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
	 * Methode qui permet de modifier un participant. Enregistre les modifs dans la BDD
	 * @param p
	 */
	public void mise_a_jour_participant (Participant p, int id)
	{
		String rq;
		
		//on instancie une equipeDAO pour récupérer l'id de l'équipe
		EquipeDAO e = new EquipeDAO();
	
		rq="UPDATE participant SET nom = '"+ p.getNom() + "', prenom = '" + p.getPrenom()+ "', nationalite = '" + p.getNationalite() +
			"', sexe = '" + p.getSexe() + "', datedenaissance = '" + p.getDateDeNaissance() + "' WHERE id = '" + id + "'";
	
		BDD.execute_REQUETE(rq);
	}
	
	/**
	 * Methode qui permet de connaitre le nombre de participants
	 * @return le nombre de participants
	 */
	public int get_nombre_participants ()
	{
		int nombre_participants=0;
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;

		//connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT COUNT (*) AS compteParticipants FROM PARTICIPANT");

			//on execute la requete
			//rs contient un pointeur situe jusute avant la premiere ligne retournee
			rs=ps.executeQuery();

			if(rs.next())
				nombre_participants= rs.getInt("compteParticipants");
			else
				nombre_participants =0;
		
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return nombre_participants;
	}
}