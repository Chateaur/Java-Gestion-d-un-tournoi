import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe pemettant l'affichage du panneau modifier equipe
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelModifierEquipe extends JPanel implements ActionListener{
	
/********** Attributs **********/	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * JLabel labelNom 
	 */
	private JLabel labelNom;
	
	/**
	 * JLabel labelInfoConfirmation
	 */
	private JLabel labelInfoConfirmation;
	
	/**
	 * JLabel labelInfoEntrer
	 */
	private JLabel labelInfoEntrer;
	
	/**
	 * JLabel labelInfoConfirmationFinal
	 */
	private JLabel labelInfoConfirmationFinal;
	
	/**
	 * JTextField textFieldNomEquipe
	 */
	private JTextField textFieldNomEquipe;
	
	/**
	 * JTextField textNouvelleEquipe
	 */
	private JTextField textNouvelleEquipe;
	
	/**
	 * Police utilisee dans les JTextFields
	 */
	private Font police;
	
	/**
	 * JButton boutonGetequipe
	 */
	private JButton boutonGetEquipe;
	
	/**
	 * JButton boutonValiderNouvelleEquipe
	 */
	private JButton boutonValiderNouvelleEquipe;

	/**
	 * EquipeDAO permettant la connexion a la BDD
	 */
	private EquipeDAO e;
	
	/**
	 * JPanel panel permettant de stocker la ligne 1
	 */
	private JPanel panel1;
	
	/**
	 * JPanel panel2 permettant de stocker la ligne 2
	 */
	private JPanel panel2;
	
	/**
	 * JPanel panel3 permettant de stocker la ligne1 3
	 */
	private JPanel panel3;
	
	/**
	 * JPanel panel4 permettant de stocker la ligne 4
	 */
	private JPanel panel4;
	
/********** Methodes **********/
	
	/**
	 * Constructeur PanelModifierEquipe
	 * @param equipe
	 */
	public PanelModifierEquipe(EquipeDAO equipe){
		
		this.e = equipe;
		
		// Definition des caracteristiques du panel
		this.setBackground(Color.lightGray);
 		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// Instanciation des JPanels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		//Instanciation des JLabels
		labelNom = new JLabel("Entrez le nom de l'equipe a modifier");
		labelInfoConfirmation = new JLabel("Equipe trouvée dans la base de donnees.");
		labelInfoEntrer = new JLabel("Merci de rentrer le nouveau nom de l'équipe : ");
		labelInfoConfirmationFinal = new JLabel ("L'equipe a bien ete modifiee, les participants "
				+ "de l'ancienne equipe sont maintenant dans la nouvelle");
		labelInfoConfirmation.setVisible(false);		
		labelInfoEntrer.setVisible(false);
		labelInfoConfirmationFinal.setVisible(false);
		
		//Instanciation du bouton boutonGetEquipe
		boutonGetEquipe = new JButton("Chercher");
		boutonGetEquipe.addActionListener(this);
		
		//Instanciation du JButton permettant de modifier le nom de l'équipe
		boutonValiderNouvelleEquipe = new JButton("Enregistrer le nom de la nouvelle équipe");
		boutonValiderNouvelleEquipe.addActionListener(this);
		boutonValiderNouvelleEquipe.setVisible(false);
		
		// Instanciation de la police
		police = new Font("Arial", Font.PLAIN, 14);
		
		// Instanciation du JTextfield textFieldNomEquipe
		textFieldNomEquipe = new JTextField();
		textFieldNomEquipe.setFont(police);
		textFieldNomEquipe.setPreferredSize(new Dimension(150, 30));
		
		//Instanciation du JTextField permettant de modifier le nom de l'équipe
		textNouvelleEquipe = new JTextField("");
		textNouvelleEquipe.setFont(police);
		textNouvelleEquipe.setPreferredSize(new Dimension(150, 30));
		textNouvelleEquipe.setVisible(false);
		
		// Ajout des composants sur le panel 1
		panel1.add(labelNom);
		panel1.add(textFieldNomEquipe);
		panel1.add(boutonGetEquipe);
		
		// Ajout des composants sur le panel 2
		panel2.add(labelInfoConfirmation);
		
		// Ajout des composants sur le panel 3
		panel3.add(labelInfoEntrer);
		panel3.add(textNouvelleEquipe);
		panel3.add(boutonValiderNouvelleEquipe);
		
		// Ajout des composants sur le panel 4
		panel4.add(labelInfoConfirmationFinal);
		
		// Ajout des panels sur le panel principal
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
	}
	
	/**
	 * Methode actionPerformed
	 * Modifie l'equipe dans la BDD apres un clic sur le bouton valider
	 */
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == boutonGetEquipe){

			// Declaration d'une string nom_equipe
			String nom_equipe;
		
			//On recupere le nom de l'équipe saisi
			nom_equipe = textFieldNomEquipe.getText();
		
			//Verification si le champ n'est pas vide
			if (!nom_equipe.equals("")){
				//On cherche si l'équipe existe
				if (e.check_equipe(new Equipe(nom_equipe)) && !nom_equipe.equals("void")){
					// Affichage des JLabels
					labelInfoConfirmation.setText("L'equipe a bien ete trouvee dans la base de donnees");
					labelInfoConfirmation.setVisible(true);
					labelInfoEntrer.setVisible(true);
					
					// On cache les composants inutiles
					labelInfoConfirmationFinal.setVisible(false);
					
					// On rend visible les composants necessaires
					textNouvelleEquipe.setVisible(true);
					boutonValiderNouvelleEquipe.setVisible(true);
					
					//On verouille la modification du JTextfield du nom equipe
					textFieldNomEquipe.setEditable(false);
				}
				else{
					// On rend visible des composants necessaires
					labelInfoConfirmation.setVisible(true);
					labelInfoConfirmation.setText("Aucune equipe de ce nom est presente dans la base de donnees");
					
					// On cache le composants inutiles
					
					labelInfoConfirmationFinal.setVisible(false);
				}
			}
			else{
				// On rend visible des composants necessaires
				labelInfoConfirmation.setVisible(true);
				labelInfoConfirmation.setText("Veuillez entrer un nom d'equipe");
				
				// On cache les composants inutiles
				labelInfoConfirmationFinal.setVisible(false);
			}
		}
		else if (ae.getSource() == boutonValiderNouvelleEquipe){
			// Declaration d'une string contenant le nom de l'equipe a modifier
			String nom_equipe;
			// Declaration d'une string contenant le nom de la nouvelle equipe
			String nouveau_nom;
			
			// Initialisation de la string contenant le nom de l'equipe a modifier avec le contenu du JTextField
			nom_equipe = textFieldNomEquipe.getText();
			// Initialisation de la string contenant le nom de la nouvelle equipe avec le contenu du JTextField
			nouveau_nom = textNouvelleEquipe.getText();
			
			// On modifie le nom de l'equipe dans la BDD
			e.modifier_nom_equipe(nom_equipe, nouveau_nom);
			
			// On affiche le label de confirmation
			labelInfoConfirmationFinal.setVisible(true);
			
			// On reinitialise le JTextField et on cache les composants de confirmation
			textFieldNomEquipe.setEditable(true);
			textFieldNomEquipe.setText("");
			textNouvelleEquipe.setVisible(false);
			boutonValiderNouvelleEquipe.setVisible(false);
			labelInfoConfirmation.setVisible(false);
			labelInfoEntrer.setVisible(false);
			textNouvelleEquipe.setText("");
		}
	}
}