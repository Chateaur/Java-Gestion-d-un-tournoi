/**
 * Methode main du projet
 * @author Romain
 * @author Florent
 * @version 1.0
 */
public class ClassMain {

	public static void main(String[] args) {
		
		EquipeDAO equipeDAO = new EquipeDAO();
		ParticipantDAO participantDAO = new ParticipantDAO();

		Fenetre teamManagement = new Fenetre(equipeDAO,participantDAO);
		teamManagement.setVisible(true);
	}
}