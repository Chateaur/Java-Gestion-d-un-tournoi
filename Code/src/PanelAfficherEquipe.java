import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * class PanelAfficherEquipe permettant d'afficher les equipes
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelAfficherEquipe extends JPanel implements ActionListener{

/********** Attributs **********/
	
	private static final long serialVersionUID = 1L;

	/**
	 * Noeud principal pour le TreeView
	 */
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Equipes");
	private DefaultTreeModel modelTree = new DefaultTreeModel(root);
	
	/**
	 * Jtree pour l'affichage des equipes et leur membres
	 */
	private JTree equipeTree = new JTree(modelTree);
	
	/**
	 * labelInfo pour afficher des informations a l'utilisateur
	 */
	private JLabel labelInfo;
	
	/**
	 * bouton upprimer pour supprimer un participant d'une equipe
	 */
	private JButton boutonSupprimer;
	
	/**
	 * ParticipantDAO pour la connexion avec la base de donnees
	 */
	private ParticipantDAO p;
	
	/**
	 * EquipeDAO pour la connexion avec la base de donnees
	 */
	private EquipeDAO e;
	
	/**
	 * Strings pour garder en memoire le nom et prenom d'un participant
	 * pour supprimer son affectation a une equipe
	 */
	private String nom_participant;
	private String prenom_participant;
	
	/**
	 * JPanel panel1
	 */
	private JPanel panel1;
	
	/**
	 * JPanel panel2
	 */
	private JPanel panel2;

/********** Methodes **********/
	
	/**
	 * Constructeur PanelAfficherEquipe
	 * @param e
	 */
	public PanelAfficherEquipe(EquipeDAO e, ParticipantDAO p){
		
		// Instanciation de e et p
		this.p = p;
		this.e = e;
		
		//parametre general du panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.lightGray);
		
		// Instanciation des sous pannels
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		//Instanciation du bouton supprimer
		boutonSupprimer = new JButton ("Confirmer la suppression");
		boutonSupprimer.addActionListener(this);
		
		//Instanciation du labelInfo
		labelInfo = new JLabel("Double cliquer sur un participant vous donnera la possibilite de le supprimer de son equipe");
		
		//Liste d'équipe recuperee depuis la BDD
		List<Equipe> listeEquipe = e.getListeEquipe();
		
		for(Equipe equi : listeEquipe){
			//On crée les noeuds
			DefaultMutableTreeNode noeudSecondaire = new DefaultMutableTreeNode(equi.getNom());
			root.add(noeudSecondaire);
			
			//On recherche les participants pour une équipe donnée
			for (String nom_participant_dans_equipe : e.getListeParticipantsEquipe(equi.getNom())){
				DefaultMutableTreeNode noeudTertiaire = new DefaultMutableTreeNode(nom_participant_dans_equipe);
				noeudSecondaire.add(noeudTertiaire);
			}
		}
		
		//On crée l'arbre et on l'ajoute au panel
		equipeTree = new JTree(root);
		// On ouvre tous les noeuds
		for(int i = 0 ; i < equipeTree.getRowCount() ; i++)
		{
			equipeTree.expandRow(i);
		}
		
		JScrollPane scrollPaneEquipes = new JScrollPane(equipeTree);
		
		//Creation d'un listener pour detecter le double clic pour supprimer un participant d'une equipe
		MouseListener ml = new MouseAdapter(){
		    public void mousePressed(MouseEvent e) {
		        int selRow = equipeTree.getRowForLocation(e.getX(), e.getY());
		        TreePath selPath = equipeTree.getPathForLocation(e.getX(), e.getY());
		        if(selRow != -1) 
		        {
		            if(e.getClickCount() == 2 && selPath.getPathCount() == 2)
		            {
                        //Si selPath.getPathCount() = 2 on est sur une equipe et non un participant
		       			labelInfo.setText("Double cliquer sur un participant vous "
		            					+ "donnera la possibilite de le supprimer de son equipe");
		            }
		            else if (e.getClickCount() == 2 && selPath.getPathCount()==3)
		            {
		            	 supprimer_participant_equipe(selRow, selPath);
		       		}
		        }
		    }
		};
		
		equipeTree.addMouseListener(ml);
		
		//On ajoute les composants au panel
		panel1.add(labelInfo);
		panel2.add(boutonSupprimer);
		panel2.setVisible(false);
		this.add(scrollPaneEquipes);
		this.add(panel1);
		this.add(panel2);
	}

	/**
	 * Methode permettant de rafraichir l'affichage
	 * @param e
	 */
	public void refresh(EquipeDAO e)
	{
		this.root.removeAllChildren();
		//Liste d'équipe recuperee depuis la BDD
		List<Equipe> listeEquipe = e.getListeEquipe();
			
		for(Equipe equi : listeEquipe){
			//On crée les noeuds
			DefaultMutableTreeNode noeudSecondaire = new DefaultMutableTreeNode(equi.getNom());
			root.add(noeudSecondaire);

			//On recherche les participants pour une équipe donnée
			for (String nom_participant_dans_equipe : e.getListeParticipantsEquipe(equi.getNom())){
				DefaultMutableTreeNode noeudTertiaire = new DefaultMutableTreeNode(nom_participant_dans_equipe);
				noeudSecondaire.add(noeudTertiaire);
			}
		}
		this.equipeTree.updateUI();
	}
	   
	/**
	 * Methode qui permet de reperer quel element a ete double clique
	 * en vue d'une eventuelle suppression
	 * @param selRow
	 * @param selPath
	 */
	public void supprimer_participant_equipe (int selRow, TreePath selPath)
	{
		//On affiche le panel avec le bouton supprimer
		panel2.setVisible(true);
		   
		//MAJ du label
		labelInfo.setText("Voulez vous supprimer le participant : " + 
				selPath.getLastPathComponent() + " de l'équipe " +
				selPath.getParentPath().getLastPathComponent() + " ?");
		   
		//On met a jour les attributs de la classe concernant le participant
		TraitementDonnees data = new TraitementDonnees();
		this.nom_participant = data.getNom(selPath.getLastPathComponent().toString(), 1);
		this.prenom_participant = data.getNom(selPath.getLastPathComponent().toString(), 2);   
	}
	   
	/**
	 * Methode actionPerformed
	 * Permet de mettre un participant sans equipe
	 */
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == boutonSupprimer)
		{
			//On met à jour la BDD, on met le participant dans l'equipe void ... 
			//Cause : cle etrangere, on ne peut pas mettre a void
			p.Lier_Equipe_Participant("void", this.nom_participant, this.prenom_participant);
			
			//MAJ du label
			this.labelInfo.setText("Le participant " + this.nom_participant + " " + this.prenom_participant + " est maintenant sans equipe.");
			
			//On rafraichit le treeview
			this.refresh(e);
		}	
	}	
}