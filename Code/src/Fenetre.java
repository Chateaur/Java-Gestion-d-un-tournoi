import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

/**
 * Classe permettant l'affichage de la fenetre principale
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class Fenetre extends JFrame{

/********** Attributs **********/
	
	private static final long serialVersionUID=1L;
	
	/**
	 * CardLayout cl
	 */
	private CardLayout cl;

	/**
	 * JPanel master sur lequel reposent tous les autres panels
	 */
	JPanel master;
	
	/**
	 * JPanel menuPane
	 */
	private JPanel menuPane;
	
	/**
	 * JPanel footerPane
	 */
	private JPanel footerPane;
	
	/**
	 * String[] listContent liste de tous les conteneurs
	 */
	String[] listContent = {"ACCUEIL", "GERER_EQUIPE", "GERER_PARTICIPANT", "LIER_EQUIPE_PARTICIPANT"};
	
	/**
	 * JButton buttonAccueil
	 */
	private JButton buttonAccueil;
	
	/**
	 * JButton buttonGererEquipe 
	 */
	private JButton buttonGererEquipe;
	
	/**
	 * JButton buttonGererParticipant
	 */
	private JButton buttonGererParticipant;
	
	/**
	 * JButton buttonLierEquipeParticipant
	 */
	private JButton buttonLierEquipeParticipant;
	
	/**
	 * JButton buttonExit
	 */
	private JButton buttonExit;
	
	/**
	 * PanelAccueil accueilPane
	 */
	private PanelAccueil accueilPane;
	
	/**
	 * PanelGererEquipe gererEquipePane
	 */
	private PanelGererEquipe gererEquipePane;
	
	/**
	 * PanelGererParticipant gererParticipantPane
	 */
	private PanelGererParticipant gererParticipantPane;
	
	/**
	 * PanelLierEquipeParticipant lierEquipeParticipantPane
	 */
	private PanelLierEquipeParticipant lierEquipeParticipantPane;

/********** Methodes **********/
	
	/**
	 * Constructeur Fenetre
	 * @param e une equipeDAO
	 * @param p un participantDAO
	 */
	public Fenetre (EquipeDAO e, ParticipantDAO p){

		// Caracteristiques generales de la fenetre
		cl = new CardLayout();
		this.setTitle("Team managment alpha");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		
		// On initialise la BDD
		BDD.init_BDD(e, p);
		
		// Initialisation du conteneur principal
		master = new JPanel();
		
		// Initialisation des conteneurs
		accueilPane = new PanelAccueil(new ImageIcon("Images/Image_accueil.jpg").getImage());
		gererEquipePane = new PanelGererEquipe(e,p);	
		gererParticipantPane = new PanelGererParticipant(e,p);
		lierEquipeParticipantPane = new PanelLierEquipeParticipant(e,p);
		
		//accueilPane.add(new ImagePanel(new ImageIcon("Images/Image_accueil.jpg").getImage()));

		// Initialisation du panel contenant les boutons
		menuPane = new JPanel();
		menuPane.setBackground(new Color(1,1,65));
		footerPane = new JPanel();
		footerPane.setBackground(new Color(1,1,65));

		// Initialisation du bouton d'accueil
		buttonAccueil = new JButton("Accueil");
		buttonAccueil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cl.show(master, listContent[0]);
			}
		});
		// Initialisation du bouton de gestion d'equipes
		buttonGererEquipe = new JButton("Gerer Equipes");
		buttonGererEquipe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cl.show(master, listContent[1]);
			}
		});
		// Initialisation du bouton de gestion de participant
		buttonGererParticipant = new JButton("Gerer Participants");
		buttonGererParticipant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cl.show(master, listContent[2]);
			}
		});
		// Initialisation du bouton de lien entre participant et equipe
		buttonLierEquipeParticipant = new JButton("Attribuer Equipe");
		buttonLierEquipeParticipant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				lierEquipeParticipantPane.refresh();
				cl.show(master, listContent[3]);
			}
		});
		// Initialisation du bouton exit
		buttonExit = new JButton("Quitter");
		buttonExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				}
			});
		
		// Ajout des boutons necessaires au panel menuPane
		menuPane.add(buttonAccueil);
		menuPane.add(buttonGererEquipe);
		menuPane.add(buttonGererParticipant);
		menuPane.add(buttonLierEquipeParticipant);

		// Ajout des composants au footerPane
		footerPane.add(buttonExit);

		// Lien du layout au panel "master"
		master.setLayout(cl);

		// Ajout des cartes au panel "master"
		master.add(accueilPane, listContent[0]);
		master.add(gererEquipePane, listContent[1]);
		master.add(gererParticipantPane, listContent[2]);
		master.add(lierEquipeParticipantPane, listContent[3]);

		this.getContentPane().add(menuPane, BorderLayout.NORTH);
		this.getContentPane().add(master, BorderLayout.CENTER);
		this.getContentPane().add(footerPane, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
}