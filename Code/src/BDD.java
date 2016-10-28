import java.sql.*;

/**
 * Classe permettant de se connecter a la base de donnees.
 * Permet d'initialiser la BDD
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class BDD {

/********** Attributs **********/

	/**
	* Parametres de connexion a la base de donnees oracle
	* URL, LOGIN et PaSS sont des constantes
	*/
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "admin";
	final static String PaSS = "admin";
	
/********** Methodes  **********/
	
	/**
	 * Constructeur BDD on charge le pilote et on initialise la BDD
	 */
	public BDD()
	{
		// chargement du pilote de bases de donnees
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e2) {
			System.err.println("Impossible de charger le pilote de BDD");
		}
	}

	/**
	 * Methode qui permet d'initaliser la bdd
	 */
	public static void init_BDD(EquipeDAO e, ParticipantDAO p)
	{
		String rq;
		
		// On drop les deux tables
		rq = "DROP TaBLE participant";
		execute_REQUETE(rq);
		rq = "DROP TaBLE equipe";
		execute_REQUETE(rq);
		System.out.println("Tables detruites");
		
		// On drop les sequences
		rq= "DROP SEQUENCE sequence_equipe_id";
		execute_REQUETE(rq);
		rq= "DROP SEQUENCE sequence_participant_id";
		execute_REQUETE(rq);
		System.out.println("Sequences detruites");

		// On cree les tables
		rq = "CREATE TaBLE equipe (id NUMBER(10), nom_equipe VaRCHaR(20),  CONSTRaINT pk_id_equipe PRIMaRY KEY (id))";
		execute_REQUETE(rq);
		
		rq= "CREaTE TaBLE participant ("
				+ "id NUMBER(10), nom VaRCHaR(15), prenom VaRCHaR(15), "
				+ "sexe VARCHaR(10), datedenaissance VaRCHaR(30), "
				+ "nationalite VaRCHaR(20), equipe NUMBER(10) DEFaULT NULL,"
				+ "CONSTRaINT pk_id_part PRIMaRY KEY (id),"
				+ " CONSTRaINT fk_equipe"
				+ " FOREIGN KEY(equipe)"
				+ " REFERENCES equipe (id)"
				+ " ON DELETE SET NULL"
				+ ")";
		execute_REQUETE(rq);
	
		System.out.println("Tables crees");
				
		// On cree les sequences
		rq= "CREaTE SEQUENCE sequence_participant_id";
		execute_REQUETE(rq);
		rq= "CREaTE SEQUENCE sequence_equipe_id";
		execute_REQUETE(rq);
		System.out.println("Sequences crees");
		
		// Appel de la methide permettant de pre-rentrer des equipes et des participants
		generationEquipeParticipant(e, p);
	}
	
	/**
	 * Methode qui permet d'executer une requête SQL
	 * @param String requete (requete que l'on souhaite envoyer a la BDD)
	 */
	public static void execute_REQUETE(String requete)
	{
		Connection con = null;
		PreparedStatement ps = null;
		//connexion a la base de donnees
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PaSS);
			//preparation de l'instruction SQL, chaque ? represente une valeur a communiquer dans l'insertion
			//les getters permettent de recuperer les valeurs des attributs souhaites de nouvarticle
			ps = con.prepareStatement(requete);
			//Execution de la requete
			ps.executeUpdate();
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du preparedStatement et de la connexion
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
	}
	
	/**
	 * Permet de generer des equipes et des participants a l'ouverture du programme
	 * @param e
	 * @param p
	 */
	public static void generationEquipeParticipant(EquipeDAO e, ParticipantDAO p){
		Equipe equipe1 = new Equipe("Les Barbus");
		Equipe equipe2 = new Equipe("Yolo Team");
		Equipe equipe3 = new Equipe("Hipsters");
		Equipe equipe4 = new Equipe("Zboob");
		
		//On cree une equipe void qui ne va pas être affichee
		//Oblige pour enlever l'affectation d'un participant a une equipe
		//la fk sur id_equipe impose ce choix.
		Equipe equipe5 = new Equipe("void");
		
		e.ajouterEquipe(equipe1);
		e.ajouterEquipe(equipe2);
		e.ajouterEquipe(equipe3);
		e.ajouterEquipe(equipe4);
		e.ajouterEquipe(equipe5);
		
		Participant p1 = new Participant ("Hollande","Francois","m","29/11/1994","Francaise","Les Barbus");
		Participant p2 = new Participant ("Pilate","Ponce","m","10/20/2001","Congolaise","Les Barbus");
		Participant p3 = new Participant ("Vincent","Francky","m","12/52/4588","Marocaine","Les Barbus");
		Participant p4 = new Participant ("Daniel","Antoine","m","45/36/2157","Yolo Team");
		Participant p5 = new Participant ("Malou","Eddy","m","01/01/0101","Syriene", "Yolo Team");
		Participant p6 = new Participant ("Batman","Robin","m","29/11/1988","Espagnole","Yolo Team");
		Participant p7 = new Participant ("Willis","Bruce","m","10/20/2001","Grolandaise","Hipsters");
		Participant p8 = new Participant ("Iron","Hulk","f","12/34/5678","Brezilienne","Hipsters");
		Participant p9 = new Participant ("Obama","Barak","f","14/78/5269","Americaine", "Hipsters");
		Participant p10 = new Participant ("Poutine","Vladimir","m","14/25/3698","Russe", "Zboob");
		Participant p11 = new Participant ("Simon","Pierre","f","22/22/2222","Irakienee","Zboob");
		Participant p12 = new Participant ("?","Nabila","f","10/20/2001","Indienne","Zboob");
		Participant p13 = new Participant ("Bachelot","Roseline","f","33/33/3333","Chinoise");
		Participant p14 = new Participant ("Merkel","Angela","f","05/09/1978","Allemande");
		
		p.ajouter(p1);
		p.ajouter(p2);
		p.ajouter(p3);
		p.ajouter(p4);
		p.ajouter(p5);
		p.ajouter(p6);
		p.ajouter(p7);
		p.ajouter(p8);
		p.ajouter(p9);
		p.ajouter(p10);
		p.ajouter(p11);
		p.ajouter(p12);
		p.ajouter(p13);
		p.ajouter(p14);
	}
}