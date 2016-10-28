import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
/**
 * Classe permettant l'affichage du panneau ajouter participant
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelAjouterParticipant extends JPanel implements ActionListener{
 
/********** Attributs **********/
	
 	private static final long serialVersionUID = 1L;

 	/**
 	 * JPanel paneNom
 	 */
	private JPanel paneNom;
	
 	/**
 	 * JPanel panePrenom
 	 */
	private JPanel panePrenom;
 	
	/**
 	 * JPanel paneSexe
 	 */
	private JPanel paneSexe;
	
 	/**
 	 * JPanel paneDateNaissance
 	 */
	private JPanel paneDateNaissance;
	
 	/**
 	 * JPanel paneNationalite
 	 */
	private JPanel paneNationalite;
	
 	/**
 	 * JPanel paneValider
 	 */
	private JPanel paneValider;
	
	/**
	 * JPanel paneConfirmer
	 */
	private JPanel paneConfirmer;
 
	/**
	 * JLabel labelNomParticipant
	 */
	private JLabel labelNomParticipant;
	
	/**
	 * JLabel labelPrenomParticipant
	 */
	private JLabel labelPrenomParticipant;
	
	/**
	 * JLabel labelSexeParticipant
	 */
	private JLabel labelSexeParticipant;
	
	/**
	 * JLabel labelDateNaissanceParticipant
	 */
	private JLabel labelDateNaissanceParticipant;
	
	/**
	 * JLabel labelNationaliteParticipant
	 */
	private JLabel labelNationaliteParticipant;
	
	/**
	 * JLabel labelInfo
	 */
	private JLabel labelInfo;
	
	/**
	 * JLabel labelConfirmer
	 */
	private JLabel labelConfirmer;
 
	/**
	 * JTextField textFieldNomParticipant
	 */
	private JTextField textFieldNomParticipant;
	
	/**
	 * JTextField textFieldPrenomParticipant
	 */
	private JTextField textFieldPrenomParticipant;
	
	/**
	 * JTextField textFieldSexeParticipant
	 */
	private JTextField textFieldSexeParticipant;
	
	/**
	 * JTextField textFieldDateNaissanceParticipant
	 */
	private JTextField textFieldDateNaissanceParticipant;
	
	/**
	 * JTextField textFieldNationaliteParticipant
	 */
	private JTextField textFieldNationaliteParticipant;
 
 	/**
 	 * Font police
 	 */
 	private Font police = new Font("Arial", Font.PLAIN, 14);
 
 	/**
 	 * Dimension tailleTextField
 	 */
 	private Dimension tailleTextField = new Dimension(150,30);
	
	/**
	 * JButton boutonEnvoi
	 */
	private JButton boutonEnvoi;
	
/********** Methodes **********/
	
	/**
	 * Constructeur PanelAjouterParticipant
	 */
 	public PanelAjouterParticipant(){
 		
 		// Caracteristiques principales du panel
 		this.setBackground(Color.lightGray);
 		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Instanciation des Panels
		paneNom = new JPanel();
		panePrenom = new JPanel();
		paneSexe = new JPanel();
		paneDateNaissance = new JPanel();
		paneNationalite = new JPanel();
		paneValider = new JPanel();
		paneConfirmer = new JPanel();
				
		// Instanciation des Labels
		labelNomParticipant = new JLabel("Entrez le nom du participant a ajouter : ");
		labelPrenomParticipant = new JLabel("Entrez le prenom du participant a ajouter : ");
		labelSexeParticipant = new JLabel("Entrez le sexe du participant a ajouter (m/f) : ");
		labelDateNaissanceParticipant = new JLabel("Entrez la date de naissance du participant a ajouter : ");
		labelNationaliteParticipant = new JLabel("Entrez la nationalite du participant a ajouter : ");
		labelInfo = new JLabel("Veuillez saisir les informarions requises : ");
		labelConfirmer = new JLabel("");
		
		// Instanciation des JTextFields
		textFieldNomParticipant = new JTextField();
		textFieldPrenomParticipant = new JTextField();
		textFieldSexeParticipant = new JTextField();
		textFieldDateNaissanceParticipant = new JTextField();
		textFieldNationaliteParticipant = new JTextField();
		
		// Caracteristiques des JTextFields
		this.attributionFont(police);
		this.definitionTailleTextField(tailleTextField);
		
		// Instanciation des bouttons et ajout des actions listener
		boutonEnvoi = new JButton("Envoyer");
		boutonEnvoi.addActionListener(this);
 
 		// Lien des labels et text fields a leur panel
 		this.linkLabelTextFieldToPanel(paneNom, labelNomParticipant, textFieldNomParticipant);
 		this.linkLabelTextFieldToPanel(paneSexe, labelSexeParticipant, textFieldSexeParticipant);
 		this.linkLabelTextFieldToPanel(panePrenom, labelPrenomParticipant, textFieldPrenomParticipant);
 		this.linkLabelTextFieldToPanel(paneDateNaissance, labelDateNaissanceParticipant, textFieldDateNaissanceParticipant);
 		this.linkLabelTextFieldToPanel(paneNationalite, labelNationaliteParticipant, textFieldNationaliteParticipant);

		// Lien des boutons et du Label a leur panel
		this.linkButtonLabelToPanel(paneValider, labelInfo, boutonEnvoi);
		
		// Lien du label au panel paneConfirmer
		paneConfirmer.add(labelConfirmer);
		
 		//Lien des panels sur le panelAjouterParticipant
 		this.add(paneNom);
 		this.add(panePrenom);
 		this.add(paneSexe);
 		this.add(paneDateNaissance);
 		this.add(paneNationalite);
		this.add(paneValider);
 		this.add(paneConfirmer);
 	}
	
	/**
	 * Permet de lier un JLabel et un JButton a un JPanel
	 * @param pane
	 * @param label
	 * @param button
	 */
 	private void linkButtonLabelToPanel(JPanel pane, JLabel label, JButton button){
		pane.add(label);
		pane.add(button);
	}
	
 	/**
 	 *  Permet de lier un JLabel et un JTextField a un JPanel
 	 * @param pane
 	 * @param label
 	 * @param textField
 	 */
 	private void linkLabelTextFieldToPanel(JPanel pane, JLabel label, JTextField textField){
 		pane.add(label);
 		pane.add(textField);
 	}
 	
 	/**
 	 * Permet d'attribuer une police au JTextFields
 	 * @param f : la police
 	 */
 	private void attributionFont(Font f){
 		textFieldNomParticipant.setFont(f);
 		textFieldPrenomParticipant.setFont(f);
 		textFieldSexeParticipant.setFont(f);
 		textFieldDateNaissanceParticipant.setFont(f);
 		textFieldNationaliteParticipant.setFont(f);
 	}
 	
 	/**
 	 * Permet de definir la taille des JTextFields
 	 * @param taille
 	 */
 	 private void definitionTailleTextField(Dimension taille){
 	 	textFieldNomParticipant.setPreferredSize(taille);
 	 	textFieldPrenomParticipant.setPreferredSize(taille);
 	 	textFieldSexeParticipant.setPreferredSize(taille);
 	 	textFieldDateNaissanceParticipant.setPreferredSize(taille);
 	 	textFieldNationaliteParticipant.setPreferredSize(taille);
 	 }
 	
 	/**
 	 * Methode actionPerformed
 	 * Enregistre le participant dans la BDD apres un clic sur le bouton valider
 	 */
	public void actionPerformed(ActionEvent ae)
	{
		int retour; // code de retour de la classe ArticleDAO

		try {
			if(ae.getSource()==boutonEnvoi){
				//on cree l'objet message
				Participant p = new Participant(this.textFieldNomParticipant.getText(), this.textFieldPrenomParticipant.getText(),
				this.textFieldSexeParticipant.getText(), this.textFieldDateNaissanceParticipant.getText(),
				this.textFieldNationaliteParticipant.getText());
				//on demande a la classe de communication d'envoyer le participant dans la base de données
				retour = ParticipantDAO.ajouter(p);
				// affichage du nombre de lignes ajoutees dans la bdd pour verification
				labelConfirmer.setText(" " + retour + " participant ajoute avec succes dans la base de donnees ");
				// Reinitialisation des JTextFields
				textFieldNomParticipant.setText("");
		 		textFieldPrenomParticipant.setText("");
		 		textFieldSexeParticipant.setText("");
		 		textFieldDateNaissanceParticipant.setText("");
		 		textFieldNationaliteParticipant.setText("");
			}
		}catch (Exception e) {
			labelConfirmer.setText(" Un probleme est survenu, veuillez contacter l'administrateur ");
			System.out.println(e.getMessage());
		}
	}
}