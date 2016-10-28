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
 * Classe permettant l'affichage du panneau ajouter equipe
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelAjouterEquipe extends JPanel implements ActionListener {
	
/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Jpanel panel1
	 */
	private JPanel panel1;
	
	/**
	 * Jpanel panel2
	 */
	private JPanel panel2;
	
	/**
	 * JLabel labelNomEquipe
	 */
	private JLabel labelNomEquipe;
	
	/**
	 * JLabel labelInfo
	 */
	private JLabel labelInfo;
	
	/**
	 * JTextField textFieldNomEquipe permettant de saisir le nom de l'équipe a ajouter
	 */
	private JTextField textFieldNomEquipe;
	
	/**
	 * Police utilisee dans le JTextField
	 */
	private Font police;

	/**
	 * JButton boutonEnvoi pour la validation et l'envoi dans la BDD
	 */
	private JButton boutonEnvoi;
	
	/**
	 * EquipeDAO permettant la connexion a la BDD
	 */
	private EquipeDAO e;
	
/********** Methodes **********/
	
	/**
	 * Constructeur PanelAjouterEquipe
	 * @param equipe
	 */
	public PanelAjouterEquipe(EquipeDAO equipe){
		
		this.e = equipe;
		
		// Definition des caracteristiques du panel
		this.setBackground(Color.lightGray);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// Instanciation des sous JPanels
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		// Instanciation des text fields
		textFieldNomEquipe =  new JTextField();
		
		// Definition des caracteristiques des textFields
		police = new Font("Arial", Font.PLAIN, 14);
		textFieldNomEquipe.setFont(police);
		textFieldNomEquipe.setPreferredSize(new Dimension(150, 30));
		
		//Instanciation des labels
		labelNomEquipe = new JLabel("Entrez le nom de l'equipe à ajouter");
		labelInfo = new JLabel("");
		
		//Instanciation du bouton
		boutonEnvoi = new JButton("Valider");
		boutonEnvoi.addActionListener(this);
		
		// Ajout des composants au pannel1
		panel1.add(labelNomEquipe);
		panel1.add(textFieldNomEquipe);
		panel1.add(boutonEnvoi);
		
		// Ajout des composants au panel2
		panel2.add(labelInfo);
		
		// Ajout des sous panels au panel principal
		this.add(panel1);
		this.add(panel2);
	}
	
	/**
	 * Methode actionPerformed
	 * Enregistre l'equipe dans la BDD apres un clic sur le bouton valider
	 */
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == boutonEnvoi)
		{
			String nom_equipe;
		
			//On recupere le nom de l'équipe saisi
			nom_equipe = textFieldNomEquipe.getText();
		
			//Verification si le champ n'est pas vide
			if (!nom_equipe.equals("")){
				//Enregistrement dans la BDD et message de confirmation
				this.e.ajouterEquipe(new Equipe(nom_equipe));
				labelInfo.setText("Equipe : " + nom_equipe + " ajoutee");
				
				// Reinitialisation du JTextField
				textFieldNomEquipe.setText("");
			}
			else
			labelInfo.setText("Veuillez saisir un nom d'équipe");
		}
	}
}