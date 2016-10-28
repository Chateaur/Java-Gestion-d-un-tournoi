/**
 * Classe Participant
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class Participant {

/********** Attributs **********/
	
	private String nom;
	private String prenom;
	private String sexe;
	private String dateDeNaissance;
	private String nationalite;
	private String equipe;

/********** Methodes **********/
	
	/**
	 * Constructeur Participant sans equipe
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param dateDeNaissance
	 * @param nationalite
	 */
	public Participant (String nom, String prenom, String sexe, String dateDeNaissance, String nationalite) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateDeNaissance = dateDeNaissance;
		this.nationalite = nationalite;
	}
	
	/**
	 * Constructeur Paricipant avec equipe
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param dateDeNaissance
	 * @param nationalite
	 * @param equipe
	 */
	public Participant (String nom, String prenom, String sexe, String dateDeNaissance, String nationalite, String equipe) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateDeNaissance = dateDeNaissance;
		this.nationalite = nationalite;
		this.equipe=equipe;
	}

	/**
	 * getNom
	 * @return nom
	 */
	public String getNom () {
		return this.nom;
	}
	
	/**
	 * setNom
	 * @param nom
	 */
	public void setNom (String nom) {
		this.nom = nom;
	}
	
	/**
	 * getPrenom
	 * @return nom
	 */
	public String getPrenom () {
		return this.prenom;
	}	

	/**
	 * setPrenom
	 * @param prenom
	 */
	public void setPrenom (String prenom) {
		this.prenom = prenom;
	}

	/**
	 * getSexe
	 * @return sexe
	 */
	public String getSexe () {
		return this.sexe;
	}

	/**
	 * setSexe
	 * @param sexe
	 */
	public void setSexe (String sexe) {
		this.sexe = sexe;
	}

	/**
	 * getDateDeNaissance
	 * @return dateDeNaissance
	 */
	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * setDateDeNaissance
	 * @param dateDeNaissance
	 */
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * getNationalite
	 * @return nationalite
	 */
	public String getNationalite() {
		return nationalite;
	}
	
	/**
	 * setNationalite
	 * @param nationalite
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	/**
	 * getEquipe
	 * @return equipe
	 */
	public String getEquipe() {
		return equipe;
	}

	/**
	 * setEquipe
	 * @param equipe
	 */
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
}