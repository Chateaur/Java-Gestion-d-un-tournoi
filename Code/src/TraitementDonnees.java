/**
 * Classe qui va permettre de mettre les traitements des données (calculs ...)
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class TraitementDonnees {

/********** Methodes **********/
	
	//Constructeur vide
	public TraitementDonnees()
	{
	}
	
	/**
	 * Méthode qui permet de retourner le nom à partir d'un String composé de Nom Prénom
	 * @ param code 1 pour avoir le nom, 2 pour avoir le prenom
	 */
	public String getNom (String chaine, int code)
	{	
		String[] result = chaine.split("\\s");
	     
		if (code==1)
		{
			return result[0];
		}
		else
			return result[1];	
	}
}