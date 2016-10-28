import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe permettant l'affichage du panneau supprimer participant
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelSupprimerParticipant extends JPanel implements ActionListener{

/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Bouton pour supprimer un participant de la liste
	 */
	private JButton supprimer;
	
	/**
	 * Liste pour afficer les participants
	 */
	private JList participantList;
	
	/**
	 * Modele pour remplir la liste
	 */
	DefaultListModel modelParticipant = new DefaultListModel();
	
	/**
	 * ParticipantDAO p
	 */
	private ParticipantDAO p;
	
	/**
	 * BDD bdd
	 */
	private BDD bdd;
	
	/**
	 * TraitementDonnees t
	 */
	private TraitementDonnees t;
	
/********** Methodes **********/
	
	/**
	 * Constructeur PanelSupprimerParticipant
	 * @param pDAO
	 */
	public PanelSupprimerParticipant(ParticipantDAO pDAO){
		
		// Caracteristiques du panel
		this.setBackground(Color.lightGray);
		
		this.p = pDAO;
		
		//On instancie le bouton
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(this);
		
		//On recherche les participants dans la BDD
		List <Participant> listeParticipants = p.getListeParticipant();
		
		//On remplit le modele pour la JList
		for(Participant part : listeParticipants)
		{
			modelParticipant.addElement(part.getNom() + " " + part.getPrenom());
		}
		
		participantList = new JList(modelParticipant);
		JScrollPane scrollPaneParticipant = new JScrollPane(participantList);
		
		//On ajoute la liste au Pannel
		this.add(scrollPaneParticipant);
		
		//On ajoute le bouton au panel
		this.add(supprimer);
	}
	
	/**
	 * Methode actionPerformed
	 * Supprime le participant de la BDD apres un clic sur le bouton valider
	 */
	public void actionPerformed (ActionEvent ae)
	{
		if (ae.getSource() == supprimer){
			String participant_a_lier;
			String nom_participant;
			String prenom_participant;
			String rq;
			
			t = new TraitementDonnees();
			bdd = new BDD();
			
			//On récupère le participant selectionné
			participant_a_lier = participantList.getSelectedValue().toString();
			
			if (participant_a_lier != null){
				nom_participant = t.getNom(participant_a_lier, 1);
				prenom_participant = t.getNom(participant_a_lier,2);
				rq = "DELETE FROM participant WHERE nom = '" + nom_participant + "' AND prenom ='" + prenom_participant + "'";
				
				BDD.execute_REQUETE(rq);
				
				//On supprime le participant de la liste
				modelParticipant.remove(participantList.getSelectedIndex());

				System.out.println("Traitement terminé");
			}
		}
	}
	
	/**
	 * Methode permettant de rafraichir la liste
	 * @param pDAO
	 */
	public void refresh(ParticipantDAO pDAO)
	{
		this.modelParticipant.removeAllElements();
		//On recherche les participants dans la BDD
		List <Participant> listeParticipants = p.getListeParticipant();
		
		//On remplit le modele pour la JList
		for(Participant part : listeParticipants){
					modelParticipant.addElement(part.getNom() + " " + part.getPrenom());
		}
	}
}