import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe permettant de gerer les panels lies aux participants
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelGererParticipant extends JPanel {
	
/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;

	/**
	 * CardLayout clParticipant
	 */
	CardLayout clParticipant;
	
	/**
	 * JPanel contentPaneParticipant sur lequel reposent les panels ajouter, modifier et supprimer
	 */
	JPanel contentPaneParticipant = new JPanel();
	
	/**
	 * PanelAjouterParticipant ajouterParticipantPane
	 */
	private PanelAjouterParticipant ajouterParticipantPane;
	
	/**
	 * PanelModifierParticipant modifierParticipantPane
	 */
	private PanelModifierParticipant modifierParticipantPane;
	
	/**
	 * PanelSupprimerParticipant supprimerParticipantPane
	 */
	private PanelSupprimerParticipant supprimerParticipantPane;
	
	/**
	 * PanelAfficherParticipant afficherParticipantPane
	 */
	private PanelAfficherParticipant afficherParticipantPane;
	
	/**
	 *  JPanel menuParticipantPane
	 */
	private JPanel menuParticipantPane;
	
	/**
	 * String[] listContentParticipant liste des conteneurs
	 */
	String[] listContentParticipant = {"AFFICHER_PARTICIPANT", "AJOUTER_PARTICIPANT", "MODIFIER_PARTICIPANT",
			"SUPPRIMER_PARTICIPANT "};
	
	/**
	 * JButton buttonAjouterParticipant
	 */
	private JButton buttonAjouterParticipant;
	
	/**
	 * JButton buttonModifierParticipant
	 */
	private JButton buttonModifierParticipant;
	
	/**
	 * JButton buttonSupprimerParticipant
	 */
	private JButton buttonSupprimerParticipant;
	
	/**
	 * JButton buttonAfficherParticipant
	 */
	private JButton buttonAfficherParticipant;
	
/********** Methodes **********/
	
	/**
	 * Constructeur PanelGererParticipant
	 * @param pDAO
	 * @param eDAO
	 */
	public PanelGererParticipant(final EquipeDAO eDAO, final ParticipantDAO pDAO){
		
		clParticipant = new CardLayout();
		this.setLayout(new BorderLayout());
		
		// Initialisation des conteneurs
		final JPanel contentPaneParticipant = new JPanel();
		afficherParticipantPane = new PanelAfficherParticipant(pDAO);
		ajouterParticipantPane = new PanelAjouterParticipant();
		modifierParticipantPane = new PanelModifierParticipant(pDAO);
		supprimerParticipantPane = new PanelSupprimerParticipant(pDAO);

		// Initialisation du panel contenant les boutons
		menuParticipantPane = new JPanel();
		menuParticipantPane.setBackground(new Color(64,128,128));

		// Initialisation du bouton d'afffichage de participant
		buttonAfficherParticipant = new JButton("Afficher Participant");
		buttonAfficherParticipant.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			afficherParticipantPane.refresh(pDAO);
			clParticipant.show(contentPaneParticipant, listContentParticipant[0]);
			}
		});
		// Initialisation du bouton d'ajout de participant
		buttonAjouterParticipant = new JButton("Ajouter Participant");
		buttonAjouterParticipant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				clParticipant.show(contentPaneParticipant, listContentParticipant[1]);
			}
		});
		// Initialisation du bouton de modification de participant
		buttonModifierParticipant = new JButton("Modifier Participant");
		buttonModifierParticipant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				clParticipant.show(contentPaneParticipant, listContentParticipant[2]);
			}
		});
		// Initialisation du bouton de suppression de participant
		buttonSupprimerParticipant = new JButton("Supprimer Participant");
		buttonSupprimerParticipant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				supprimerParticipantPane.refresh(pDAO);
				clParticipant.show(contentPaneParticipant, listContentParticipant[3]);
			}
		});

		// Ajout des boutons necessaires au panel menuPane
		menuParticipantPane.add(buttonAfficherParticipant);
		menuParticipantPane.add(buttonAjouterParticipant);
		menuParticipantPane.add(buttonModifierParticipant);
		menuParticipantPane.add(buttonSupprimerParticipant);
		
		// Lien du layout au panel
		contentPaneParticipant.setLayout(clParticipant);

		// Ajout des cartes au panel "contentPanel"
		contentPaneParticipant.add(afficherParticipantPane, listContentParticipant[0]);
		contentPaneParticipant.add(ajouterParticipantPane, listContentParticipant[1]);
		contentPaneParticipant.add(modifierParticipantPane, listContentParticipant[2]);
		contentPaneParticipant.add(supprimerParticipantPane, listContentParticipant[3]);
		
		// Ajout des sous panels au panel principal
		this.add(menuParticipantPane, BorderLayout.NORTH);
		this.add(contentPaneParticipant, BorderLayout.CENTER);
	}
}