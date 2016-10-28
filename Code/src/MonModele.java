import javax.swing.table.AbstractTableModel;

/**
 * Classe qui permet de construire un moedele "Custom" pour remplit une JTable (surtout pour pouvoir la rafraichir)
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class MonModele extends AbstractTableModel { 
   
/********** Attributs **********/
	
	private static final long serialVersionUID=1L;
	
	/**
    * Tableau pour les donnees 
    */
	Object donnees[][];
	
	/**
	 * Tableau pour les titres des colonnes 
	 */
    String titres[];
 
/**********Methodes**********/
    
    /**
     * Constructeur
     * @param donnees
     * @param titres
     */
    public MonModele(Object donnees[][], String titres[]) { 
    	this.donnees = donnees; 
    	this.titres = titres; 
    } 
   
   /**
    * @return le nombre de colonnes
    */
   public int getColumnCount() { 
      return donnees[0].length; 
   }
   
   /**
    * Retourne une valeur precise du model
    * @param int param1
    * @param int param2
    */
   public Object getValueAt(int parm1, int parm2) { 
      return donnees[parm1][parm2]; 
   }
   
   /**
    * @return le nombre de lignes
    */
   public int getRowCount() { 
      return donnees.length; 
   }
   
   /**
    * @param indice colonne
    * @return le nom d'une colonne a partir de son indice
    */
   public String getColumnName(int col){ 
      return titres[col]; 
   } 
}