/**
 * Classe Equipe
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class Equipe {
	
/********** Attributs **********/
	
	/**
	 * String nom contient le nom de l'equipe
	 */
	private String nom;
	
/********** Methodes **********/
	
	/**Constructeur parametre
	 * @param nom
	 */
	public Equipe(String nom)
	{
		this.nom = nom;
	}
	
	/**
	 * String getNom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setNom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
}