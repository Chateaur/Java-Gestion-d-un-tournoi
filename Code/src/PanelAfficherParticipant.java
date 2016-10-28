import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Classe permettant l'affichage du panneau afficher participant
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelAfficherParticipant extends JPanel {
	
/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * JScrollPane scrollPaneParticipant
	 */
	private JScrollPane scrollPaneParticipant;
	
	/**
	 * JTable pour un affichage structuré des participants
	 */
	private JTable tableauParticipants;
	
	/**
	 * Model pour remplir la Jtable
	 */
	private MonModele model;
	
/********** Methodes **********/
	
	/**
	 * Constructeur PanelAfficherParticipant
	 * @param pDAO
	 * @param eDAO
	 */
	PanelAfficherParticipant (ParticipantDAO pDAO){
		//Caracteristiques principales du panel
		this.setBackground(Color.lightGray);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//Tableau a 6 colonnes. Le nombre de lignes est determine par la methode pDAO.get_nombre_participants
		Object[][] data = new Object[pDAO.get_nombre_participants()][6];
		
		//Declaration et instanciation du tableau contenant les titres de la JTable
		String title []= {"Nom","Prénom","Sexe","Date de naissance", "Nationalite", "Equipe"};
		
		//Variables pour remplir le tableau data
		int i=0;
		int j=0;
		
		//On recherche les participants dans la BDD
		List<Participant> listeParticipants = pDAO.getListeParticipant();
		
		//On remplit le tableau data
		for(Participant part : listeParticipants){
			j=0;
			
			data [i][j] = part.getNom();
			data [i][j+1] = part.getPrenom();
			data [i][j+2] = part.getSexe();
			data [i][j+3] = part.getDateDeNaissance();
			data [i][j+4] = part.getNationalite();
			data [i][j+5] = part.getEquipe();
		
			i++;
		}
		
		//On instancie un modele
		model = new MonModele(data, title);
		
		//On instancie la JTable
		this.tableauParticipants = new JTable ();
		tableauParticipants.setModel(model);
		//On desactive la possibilite d emodifier la JTable
		this.tableauParticipants.setEnabled(false);

		//On ajoute un scrollPane
		scrollPaneParticipant = new JScrollPane(this.tableauParticipants);
		//Ajout du composant au panel
		this.add(scrollPaneParticipant, BorderLayout.CENTER);
	}
	
	/**
	 * Methode permettant de rafraichir l'affichage
	 * @param pDAO
	 * @param eDAO
	 */
	public void refresh (ParticipantDAO pDAO)
	{
		
		//Tableau a 6 colonnes. Le nombre de lignes est determine par la methode pDAO.get_nombre_participants
		Object[][] data = new Object[pDAO.get_nombre_participants()][6];
		
		//Declaration et instanciation du tableau contenant les titres de la JTable
		String title []= {"Nom","Prénom","Sexe","Date de naissance", "Nationalite", "Equipe"};
		
		//Variables pour remplir le tableau data
		int i=0;
		int j=0;
		
		//On recherche les participants dans la BDD
		List<Participant> listeParticipants = pDAO.getListeParticipant();
		
		//On remplit le tableau data
		for(Participant part : listeParticipants)
		{	
			j=0;
			
			data [i][j] = part.getNom();
			data [i][j+1] = part.getPrenom();
			data [i][j+2] = part.getSexe();
			data [i][j+3] = part.getDateDeNaissance();
			data [i][j+4] = part.getNationalite();
			data [i][j+5] = part.getEquipe();
		
			i++;
		}
		
		//On vide le model precedent
		model = null;
		
		//On le re instancie avec les nouvelles donnees
		model = new MonModele(data, title);
		
		//On affecte le modele a la JTable
		tableauParticipants.setModel(model);
	}	
}