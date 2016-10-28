import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe permettant l'affichage de PanelLierEquipeParticipant
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelLierEquipeParticipant extends JPanel implements ActionListener{

/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * JListe participantList liste des participants non affectes
	 */
	private JList<String> participantsList;
	
	/**
	 * JList equipeList liste des equipes
	 */
	private JList<String> equipeList;
	
	/**
	 * DefaultListModel modelParticipant sert a modifier les donnees de la liste de participants
	 */
	DefaultListModel<String> modelParticipant;
	
	/**
	 * DefaultListModel modelEquipe sert a modifier les donnees de la liste de participants
	 */
	DefaultListModel<String> modelEquipe;
	
	/**
	 * JButton boutonLier lie une equipe a un participant
	 */
	private JButton boutonLier;

	/**
	 * Label d'information pour les erreur utilisateurs
	 * On l'instancie ici car pose des problèmes lors de l'appel de la methode setText ... Mystere
	 */
	private JLabel labelErreur = new JLabel("Selectionner un participant et une equipe puis cliquez sur le bouton pour les lier");
	
	/**
	 * ParticipantDAO p le participant DAO pour la comm avec la BDD
	 */
	private ParticipantDAO p;
	
	/**
	 * EquipeDAO e l'equipe DAO pour la comm avec la BDD
	 */
	private EquipeDAO e;

/********** Methodes **********/
	
	/**
	 * Constructeur PanelLierEquipeParticipant
	 * @param equi
	 * @param partDAO
	 */
	public PanelLierEquipeParticipant(EquipeDAO equipe, ParticipantDAO partDAO){
		
		// Initialisation des attributs lies a la BDD
		this.p = partDAO;
		this.e = equipe;
		
		// Caracteristiques principales du panel
		this.setBackground(Color.lightGray);
		
		//Instanciation du bouton lier
		boutonLier = new JButton("Lier un participant a une equipe");
		boutonLier.setPreferredSize(new Dimension(150,30));
		boutonLier.addActionListener(this);

		//On recupère les listes depuis la base
		List<Participant> listeParticipant= p.getListeParticipant();
		List<Equipe> listeEquipe = e.getListeEquipe();
		
		// Instanciation des models
		modelParticipant = new DefaultListModel<String>();
		modelEquipe = new DefaultListModel<String>();

		//on remplit le modele des participants pour la JList
		for(Participant part : listeParticipant){
			//On affiche que les participants qui n'ont pas d'equipe
			if (part.getEquipe()== null || part.getEquipe().equals("void")) {
				modelParticipant.addElement(part.getNom() + " " + part.getPrenom());
			}
		}
		
		//on remplit le modèle des equipes
		for(Equipe equ : listeEquipe){
		}
			
		//On instancie les equipes
		participantsList = new JList<String>(modelParticipant);
		equipeList = new JList<String>(modelEquipe);
		
		//On ajoute les listes au sous pannels et on ajoute une scroll bar
		JScrollPane scrollPaneParticipants = new JScrollPane(participantsList);
		scrollPaneParticipants.setViewportView(participantsList);
		
		JScrollPane scrollPaneEquipe = new JScrollPane(equipeList);
		scrollPaneEquipe.setViewportView(equipeList);
		
		// Ajout des composants au panel
		this.add(scrollPaneParticipants);
		this.add(labelErreur);
		this.add(boutonLier);
		this.add(scrollPaneEquipe);	
	}	

	/**
	 *  Gere les actions des boutons
	 */
	public void actionPerformed(ActionEvent ae)
	{
		//Represente le participant et l'equipe slectionnes
		String participant_a_lier;
		String equipe_selectionnee;
		
		//Variables pour la mise a jour dans la base de donnees
		String nom_participant;
		String prenom_participant;

		try {
			//On recupere le participant selectionne
			participant_a_lier = participantsList.getSelectedValue().toString();
			
			//On recupere l'equipe selectionnee
			equipe_selectionnee= equipeList.getSelectedValue().toString();

			//On supprime le participant de la liste
			modelParticipant.remove(participantsList.getSelectedIndex());

			//On met a jour la DBB
			//Pour cela on instancie une classe de traitementDonnees pour recuperer le nom et le prenom
			//car la variable participant_a_lier contient le nom et le prenom
			TraitementDonnees donnees = new TraitementDonnees();

			nom_participant = donnees.getNom(participant_a_lier,1);
			prenom_participant = donnees.getNom(participant_a_lier,2);
			
			//On met a jour la BDD
			this.p.Lier_Equipe_Participant(equipe_selectionnee, nom_participant, prenom_participant);
			
			//Message de confirmation ce message n'apparait pas si une erreur est survenue via le catch de la BDD
			this.labelErreur.setText("M."+ nom_participant + " fait maintenant parti de l'equipe " + equipe_selectionnee);
		}catch (Exception e) {
			System.err.println("Veuillez controler vos saisies");
			this.labelErreur.setText("Veillez a selectionner un participant et une equipe !! Ou problème de BDD...");
		}
	}

	/**
	 * Methode de rafraichissement
	 */
	public void refresh()
	{
		modelParticipant.removeAllElements();
		
		//On recupère les listes depuis la base
		List<Participant> listeParticipant= p.getListeParticipant();

		//on remplit le modele des participants pour la JList
		for(Participant part : listeParticipant){
			//On affiche que les participants qui n'ont pas d'equipe
			if (part.getEquipe().equals("Non renseignee")|| part.getEquipe().equals("void")){
				modelParticipant.addElement(part.getNom() + " " + part.getPrenom());
			}	
		}
		
		modelEquipe.removeAllElements();
		
		List<Equipe> listeEquipe = e.getListeEquipe(); 
		
		//on remplit le modele avec les equipes
		for(Equipe equ : listeEquipe){
			modelEquipe.addElement(equ.getNom());
		}
	}
}