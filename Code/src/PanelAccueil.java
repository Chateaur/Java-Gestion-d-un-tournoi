import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * Classe permettant l'affichage d'une image de fond
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class PanelAccueil extends JPanel {

/********** Attributs **********/
	
    private static final long serialVersionUID = 1L;
    
    /**
     * Image a afficher
     */
    private Image img;
    
/********** Methodes **********/
    
    /**
     * Constructeur PanelAccueil
     * @param img
     */
    public PanelAccueil(Image img){
        this.img = img;
    }
    
    /**
     * Permet d'afficher l'image
     */
    public void paintComponent(Graphics g) {
    	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}