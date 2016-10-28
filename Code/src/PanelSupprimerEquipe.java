import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe permettant l'affichage du panneau supprimer equipe
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelSupprimerEquipe extends JPanel implements ActionListener{
	
/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * JLabel labelInfo
	 */
	private JLabel labelInfo;
	
	/**
	 * JLabel labelNom indiquant a l'utilisateur ce qu'il doit faire
	 */
	private JLabel labelNom;
	
	/**
	 * JLabel labelConfirmation demandant a l'utilisateur de confirmer son choix de suppression
	 */
	private JLabel labelConfirmation;
	
	/**
	 * JLabel labelValidation indiquant a l'utilisateur que la suppression est effectuee
	 */
	private JLabel labelValidation;
	
	/**
	 * JTexField textFieldNom pour la saisie d'un nom d'equipe
	 */
	private JTextField textFieldNom;
	
	/**
	 * JButton boutonEquipe servant a l'envoi de recherche d'équipe dans la BDD
	 */
	private JButton boutonEquipe;
	
	/**
	 * JButton boutonValider valide la réponse utilisateur
	 */
	private JButton boutonValider;
	
	/**
	 * JRadioButton oui pour la confirmation utilisateur de la suppresion
	 */
	private JRadioButton oui;
	
	/**
	 * JRadioButton non pour la confirmation utilisateur de la suppresion
	 */
	private JRadioButton non;
	
	/**
	 * Font utilisée dans les JTextFields
	 */
	private Font police;
	
	/**
	 * JPanel panel1 contenant la premiere ligne a afficher
	 */
	private JPanel panel1;
	
	/**
	 * JPanel panel1 contenant la deuxieme ligne a afficher
	 */
	private JPanel panel2;
	
	/**
	 * JPanel panel1 contenant la troisieme ligne a afficher
	 */
	private JPanel panel3;
	
	/**
	 * JPanel panel1 contenant la quatrieme ligne a afficher
	 */
	private JPanel panel4;
	
	/**
	 * EquipeDAO e permet la connexion a la base de donnees
	 */
	private EquipeDAO e;
	
/********** Methodes **********/

	/**
	 * Constructeur PanelSupprimerEquipe
	 * @param equipe
	 */
	public PanelSupprimerEquipe(EquipeDAO equipe){
		
		this.e = equipe;
		
		// Definition des caracteristiques principales du panel
		this.setBackground(Color.lightGray);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// Instanciation des pannels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		
		// Instanciation des composants
		labelNom = new JLabel("Entrez le nom de l'équipe à supprimer");
		labelInfo = new JLabel("");
		labelConfirmation = new JLabel("");
		labelValidation = new JLabel("");
		boutonEquipe = new JButton("Rechercher");
		boutonEquipe.addActionListener(this);
		textFieldNom = new JTextField("");
		oui = new JRadioButton("Oui");
		oui.setVisible(false);
		oui.addActionListener(this);
		non = new JRadioButton("Non");
		non.setVisible(false);
		non.addActionListener(this);
		boutonValider = new JButton("Valider");
		boutonValider.addActionListener(this);
		boutonValider.setVisible(false);
		
		police = new Font("Arial", Font.PLAIN, 14);
		textFieldNom.setFont(police);
		textFieldNom.setPreferredSize(new Dimension(150, 30));
		
		// Ajout des composants au panel1
		panel1.add(labelNom);
		panel1.add(textFieldNom);
		panel1.add(boutonEquipe);
		
		// Ajout des composants au panel2
		panel2.add(labelInfo);

		// Ajout des composants au panel3
		panel3.add(labelConfirmation);
		panel3.add(oui);
		panel3.add(non);
		panel3.add(boutonValider);
		
		// Ajout des composants au panel4
		panel4.add(labelValidation);
		
		// Ajout des pannels au panel principal
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
	}
	
	/**
	* Methode actionPerformed
	* Supprime l'equipe de la BDD apres un clic sur le bouton valider
	*/
	public void actionPerformed(ActionEvent ae){
		
		if (ae.getSource() == boutonEquipe){	
			
			String nom_equipe;
		
			//On recupere le nom de l'équipe saisi
			nom_equipe = textFieldNom.getText();
		
			//Verification si le champ n'est pas vide
			if (nom_equipe.equals("") == false ){
				
				//On cherche si l'équipe existe
				if (e.check_equipe(new Equipe(nom_equipe)) && !nom_equipe.equals("void")){
					
					// On affiche les JLabels
					labelInfo.setVisible(true);
					labelInfo.setText("Equipe trouvée dans la base de données.");
					labelConfirmation.setText("Confirmez vous la suppression ? ");
					
					// On rend les autres composants visibles
					oui.setVisible(true);
					non.setVisible(true);
					boutonValider.setVisible(true);
				}
				else
					labelInfo.setText("Equipe non trouvée dans la base de données");
			}
			else
				labelInfo.setText("Veuillez saisir un nom d'équipe");
		}
		else if (ae.getSource() == boutonValider){
			if (oui.isSelected() && !non.isSelected()){
				
				String nom_equi = textFieldNom.getText();
				
				//On lance la suppression
				e.Supprimer_Equipe(new Equipe(nom_equi));
				
				// On affiche le label de validation
				labelValidation.setText("L'equipe a bien ete suprimee");
				
				// On reinitialise le JTextField et on rent invisible les composants de confirmation
				textFieldNom.setText("");
				oui.setVisible(false);
				non.setVisible(false);
				boutonValider.setVisible(false);
				labelInfo.setVisible(false);
				labelConfirmation.setVisible(false);
			}
		}
		else if (ae.getSource() == oui)
		{
			if (non.isSelected())
			{
				non.setSelected(false);
			}
		}
		else if (ae.getSource() == non)
		{
			if (oui.isSelected())
			{
				oui.setSelected(false);
			}
		}
	}
}