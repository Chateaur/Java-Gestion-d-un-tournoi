import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe permettant de Gerer les panneaux concernant les equipes
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelGererEquipe extends JPanel{

/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;

	/**
	 * CardLayout clEquipe
	 */
	private CardLayout clEquipe;
	
	/**
	 * JPanel contentPaneEquipe sur lequel reposent les panels ajouter, modifier et supprimer
	 */
	private JPanel contentPaneEquipe;
	
	/**
	 * PanelAjouterEquipe ajouterEquipePane
	 */
	private PanelAjouterEquipe ajouterEquipePane;
	
	/**
	 * PanelModifierEquipe modifierEquipePane
	 */
	private PanelModifierEquipe modifierEquipePane;
	
	/**
	 * PanelSupprimerEquipe supprimerEquipePane
	 */
	private PanelSupprimerEquipe supprimerEquipePane;
	
	/**
	 * PanelAfficherEquipe afficherEquipePane
	 */
	private PanelAfficherEquipe afficherEquipePane;
	
	/**
	 * JPanel menuEquipePane contenant les boutons du menu 
	 */
	private JPanel menuEquipePane;
	
	/**
	 * String[] listContentEquipe liste des conteneurs
	 */
	private String[] listContentEquipe = {"AFFICHER_EQUIPE", "AJOUTER_EQUIPE", "MODIFIER_EQUIPE", "SUPPRIMER_EQUIPE"};
	
	/**
	 * JButton buttonAjouterEquipe
	 */
	private JButton buttonAjouterEquipe;
	
	/**
	 * buttonModifierEquipe
	 */
	private JButton buttonModifierEquipe;
	
	/**
	 * JButton buttonSupprimerEquipe
	 */
	private JButton buttonSupprimerEquipe;
	
	/**
	 * JButton buttonAfficherEquipe
	 */
	private JButton buttonAfficherEquipe;
	
/********** Methodes **********/
	
	/**
	 * Constructeur PanelGererEquipe
	 * @param e
	 */
	public PanelGererEquipe(final EquipeDAO e, final ParticipantDAO p){
		
		clEquipe = new CardLayout();
		this.setLayout(new BorderLayout());
		
		// Initialisation des conteneurs
		contentPaneEquipe = new JPanel();
		ajouterEquipePane = new PanelAjouterEquipe(e);
		modifierEquipePane = new PanelModifierEquipe(e);
		supprimerEquipePane = new PanelSupprimerEquipe(e);
		afficherEquipePane = new PanelAfficherEquipe(e,p);

		// Initialisation du panel contenant les boutons
		menuEquipePane = new JPanel();
		menuEquipePane.setBackground(new Color(64,128,128));

		// Initialisation du bouton d'affichage d'équipe
		buttonAfficherEquipe = new JButton("Afficher Equipes");
		buttonAfficherEquipe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				afficherEquipePane.refresh(e);
				clEquipe.show(contentPaneEquipe, listContentEquipe[0]);
			}
		});	
		// Initialisation du bouton d'ajout d'equipe
		buttonAjouterEquipe = new JButton("Ajouter Equipe");
		buttonAjouterEquipe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				clEquipe.show(contentPaneEquipe, listContentEquipe[1]);
			}
		});
		// Initialisation du bouton de modification d'equipe
		buttonModifierEquipe = new JButton("Modifier Equipe");
		buttonModifierEquipe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				clEquipe.show(contentPaneEquipe, listContentEquipe[2]);
			}
		});
		// Initialisation du bouton de suppression d'équipe
		buttonSupprimerEquipe = new JButton("Supprimer Equipe");
		buttonSupprimerEquipe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				clEquipe.show(contentPaneEquipe, listContentEquipe[3]);
			}
		});
		
		// Ajout des boutons necessaires au panel menuPane
		menuEquipePane.add(buttonAfficherEquipe);
		menuEquipePane.add(buttonAjouterEquipe);
		menuEquipePane.add(buttonModifierEquipe);
		menuEquipePane.add(buttonSupprimerEquipe);
		
		// Lien du layout au panel
		contentPaneEquipe.setLayout(clEquipe);

		// Ajout des cartes au panel "contentPanel"
		contentPaneEquipe.add(afficherEquipePane, listContentEquipe[0]);
		contentPaneEquipe.add(ajouterEquipePane, listContentEquipe[1]);
		contentPaneEquipe.add(modifierEquipePane, listContentEquipe[2]);
		contentPaneEquipe.add(supprimerEquipePane, listContentEquipe[3]);

		this.add(menuEquipePane, BorderLayout.NORTH);
		this.add(contentPaneEquipe, BorderLayout.CENTER);
	}
}