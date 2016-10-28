import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe permettant l'affichage du panneau modifier participant
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelModifierParticipant extends JPanel implements ActionListener {

/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;

	/**
	 * Bouton pour lancer la recherche d'un participant
	 */
	private JButton boutonRechercher;
	
	/**
	 * Bouton pour enregistrer les modifications
	 */
	private JButton boutonEnregistrer;
	
	/**
	 * JTextField pour inserer un nom
	 */
	private JTextField textFieldNomRecherche;
	
	/**
	 * JTextField pour inserer un prenom
	 */
	private JTextField textFieldPrenomRecherche;
	
	/**
	 * Label pour afficher des inforations
	 */
	private JLabel labelInfo;
	
	/**
	 * ParticipantDAO pour la connexion a la BDD
	 */
	private ParticipantDAO p;
	
	/**
	 * JPanel panel1
	 */
	private JPanel panel1;
	
	/**
 	 * JPanel panel2
 	 */
	private JPanel panel2;
	
 	/**
 	 * JPanel panel3
 	 */
	private JPanel panel3;
 	
	/**
 	 * JPanel panel4
 	 */
	private JPanel panel4;
	
 	/**
 	 * JPanel panel5
 	 */
	private JPanel panel5;
	
 	/**
 	 * JPanel panel6
 	 */
	private JPanel panel6;
	
 	/**
 	 * JPanel panel7
 	 */
	private JPanel panel7;
	
	/**
	 * JPanel panel8
	 */
	private JPanel panel8;
	
	/**
	 * JPanel panel9
	 */
	private JPanel panel9;
	
	/**
	 * JPanel panel10
	 */
	private JPanel panel10;
	
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
	 * JLabel labelConfirmer
	 */
	private JLabel labelConfirmer;
 
	/**
	 * JLabel labelValider
	 */
	private JLabel labelValider;
	
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
 	 * int representant l'id du participant a mettre a jour
 	 */
 	int id;
 	
/********** Methodes **********/
 	
	/**
	 * Constructeur PanelModifierParticipant
	 */
	public PanelModifierParticipant(ParticipantDAO pDAO)  {
		
		// Caracteristiques principales du panneau
		this.setBackground(Color.lightGray);
		
		this.p = pDAO;
		
 		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//Instanciation du bouton recherche participants
		boutonRechercher = new JButton ("Rechercher");
		boutonRechercher.addActionListener(this);
		
		//Instanciation du bouton enregistrer modifs
		boutonEnregistrer = new JButton ("Enregistrer les modifications");
		boutonEnregistrer.addActionListener(this);
	
		// Instanciation des sous Panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();
		panel9 = new JPanel();
		panel10 = new JPanel();
		
		// Instanciation des Labels
		labelInfo = new JLabel ("Entrez un nom et un prenom pour la recherche : ");
		labelValider = new JLabel ("");
		labelNomParticipant = new JLabel("Nom : ");
		labelPrenomParticipant = new JLabel("Prenom : ");
		labelSexeParticipant = new JLabel("Sexe (m/f) : ");
		labelDateNaissanceParticipant = new JLabel("Date de naissance : ");
		labelNationaliteParticipant = new JLabel("Nationalite : ");
		labelConfirmer = new JLabel("");
		
		// Instanciation des JTextFields
		textFieldNomParticipant = new JTextField();
		textFieldPrenomParticipant = new JTextField();
		textFieldSexeParticipant = new JTextField();
		textFieldDateNaissanceParticipant = new JTextField();
		textFieldNationaliteParticipant = new JTextField();
		textFieldNomRecherche = new JTextField ("Nom");
		textFieldPrenomRecherche = new JTextField("Prenom");
		
		//Ajout d'actionsListeners pour les JTextField pour effacer le contenu lors du clic
		textFieldNomRecherche.addMouseListener( new  MouseAdapter(){	
			public void mousePressed(MouseEvent e) {
				vide_TextField(textFieldNomRecherche);
			}
		});
		textFieldPrenomRecherche.addMouseListener( new  MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				vide_TextField(textFieldPrenomRecherche);
			}
		});
		
		// Caracteristiques des JTextFields
		this.attributionFont(police);
		this.definitionTailleTextField(tailleTextField);
		
		// ajout des composants sur le panel 1
		panel1.add(labelInfo);
		panel1.add(textFieldNomRecherche);
		panel1.add(textFieldPrenomRecherche);
		
		// ajout des composants sur le panel 2
		panel2.add(boutonRechercher);
		
		// ajout des composants sur le panel 3
		panel3.add(labelValider);

		// Lien des labels et text fields a leur panel (de 4 a 8)
		this.linkLabelTextFieldToPanel(panel4, labelNomParticipant, textFieldNomParticipant);
 		this.linkLabelTextFieldToPanel(panel5, labelPrenomParticipant, textFieldPrenomParticipant);
 		this.linkLabelTextFieldToPanel(panel6, labelSexeParticipant, textFieldSexeParticipant);
 		this.linkLabelTextFieldToPanel(panel7, labelDateNaissanceParticipant, textFieldDateNaissanceParticipant);
 		this.linkLabelTextFieldToPanel(panel8, labelNationaliteParticipant, textFieldNationaliteParticipant);
		
		// Lien des labels et text fields a leur panel (de 4 a 8)
 		this.linkLabelTextFieldToPanel(panel4, labelNomParticipant, textFieldNomParticipant);
 		this.linkLabelTextFieldToPanel(panel5, labelPrenomParticipant, textFieldPrenomParticipant);
 		this.linkLabelTextFieldToPanel(panel6, labelSexeParticipant, textFieldSexeParticipant);
 		this.linkLabelTextFieldToPanel(panel7, labelDateNaissanceParticipant, textFieldDateNaissanceParticipant);
 		this.linkLabelTextFieldToPanel(panel8, labelNationaliteParticipant, textFieldNationaliteParticipant);
 		
 		// Lien du JTextFields au panel9
 		panel9.add(boutonEnregistrer);
 		
		// Lien du label au panel panel10
		panel10.add(labelConfirmer);
		
 		//Lien des panels sur le panelModifierEquipe
 		this.add(panel1);
		this.add(panel2);
 		this.add(panel3);
 		this.add(panel4);
 		this.add(panel5);
 		this.add(panel6);
		this.add(panel7);
 		this.add(panel8);
 		this.add(panel9);
 		this.add(panel10);
 		
 		// On cache des composants
 		labelNomParticipant.setVisible(false);
		labelPrenomParticipant.setVisible(false);
		labelSexeParticipant.setVisible(false);
		labelDateNaissanceParticipant.setVisible(false);
		labelNationaliteParticipant.setVisible(false);
		textFieldNomParticipant.setVisible(false);
		textFieldPrenomParticipant.setVisible(false);
		textFieldSexeParticipant.setVisible(false);
		textFieldDateNaissanceParticipant.setVisible(false);
		textFieldNationaliteParticipant.setVisible(false);
		boutonEnregistrer.setVisible(false);
	}
	
	/**
 	 * Methode actionPerformed
 	 * Permet de gerer les actions des boutons
 	 */
	public void actionPerformed(ActionEvent ae)
	{
		try {
			if(ae.getSource()==boutonRechercher){
				
				String nomRecherche;
				String prenomRecherche;
				
				//On met les valeurs des JTextFields dans les string
				nomRecherche = textFieldNomRecherche.getText();
				prenomRecherche = textFieldPrenomRecherche.getText();
				
				if (nomRecherche.equals("") || prenomRecherche.equals(""))
				{
					labelValider.setVisible(true);
					labelValider.setText("Merci de rentrer des valeurs pour les deux champs");
				}
				else
				{
					if (p.Check_participant(nomRecherche, prenomRecherche))
					{
						labelValider.setVisible(true);
						labelValider.setText("Le participant a ete trouve !");
						
						//On lance la recherche du participant
						Participant part;
						this.id = p.get_id_participant(nomRecherche, prenomRecherche);
						part = p.getParticipant(this.id);
						
						
						//On affecte les valeurs aux Jtextfields
						textFieldNomParticipant.setText(part.getNom());
						textFieldPrenomParticipant.setText(part.getPrenom());
						textFieldSexeParticipant.setText(part.getSexe());
						textFieldDateNaissanceParticipant.setText(part.getDateDeNaissance());
						textFieldNationaliteParticipant.setText(part.getNationalite());
						
						// On affiche les composants
						labelNomParticipant.setText("Nom : ");
						labelPrenomParticipant.setText("Prenom : ");
						labelNomParticipant.setVisible(true);
						labelPrenomParticipant.setVisible(true);
						labelSexeParticipant.setVisible(true);
						labelDateNaissanceParticipant.setVisible(true);
						labelNationaliteParticipant.setVisible(true);
						textFieldNomParticipant.setVisible(true);
						textFieldPrenomParticipant.setVisible(true);
						textFieldSexeParticipant.setVisible(true);
						textFieldDateNaissanceParticipant.setVisible(true);
						textFieldNationaliteParticipant.setVisible(true);
						boutonEnregistrer.setVisible(true);
					}
					else{
						labelValider.setVisible(true);
						labelValider.setText("Participant non trouve dans la base de donnees !");
					}
			   }
			}
			else if (ae.getSource() == boutonEnregistrer)
			{
				Participant part_a_envoyer = new Participant(textFieldNomParticipant.getText(),
				textFieldPrenomParticipant.getText(), textFieldSexeParticipant.getText(),
				textFieldDateNaissanceParticipant.getText(), textFieldNationaliteParticipant.getText());
	
				p.mise_a_jour_participant(part_a_envoyer, this.id);
				
				labelConfirmer.setVisible(true);
				
				// On affiche le label Confirmer
				this.labelConfirmer.setText("Mise à jour du participant enregistree");
				
				// On cache les composants inutiles
				labelValider.setVisible(false);
				labelValider.setVisible(false);
				labelNomParticipant.setVisible(false);
				labelPrenomParticipant.setVisible(false);
				labelSexeParticipant.setVisible(false);
				labelDateNaissanceParticipant.setVisible(false);
				labelNationaliteParticipant.setVisible(false);
				textFieldNomParticipant.setVisible(false);
				textFieldPrenomParticipant.setVisible(false);
				textFieldSexeParticipant.setVisible(false);
				textFieldDateNaissanceParticipant.setVisible(false);
				textFieldNationaliteParticipant.setVisible(false);
				boutonEnregistrer.setVisible(false);
			}
		}catch (Exception e) {
			labelInfo.setText(" Un probleme est survenu, veuillez contacter l'administrateur ");
			System.out.println(e.getMessage());
		}
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
 		textFieldNomRecherche.setFont(f);
 		textFieldPrenomRecherche.setFont(f);
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
 	 	textFieldNomRecherche.setPreferredSize(taille);
 		textFieldPrenomRecherche.setPreferredSize(taille);
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
  	 * Permet de vider une JtextField lors d'un clic
  	 * @param JTextField
  	 */
  	private void vide_TextField (JTextField champ)
  	{
  		champ.setText("");
  	}
}