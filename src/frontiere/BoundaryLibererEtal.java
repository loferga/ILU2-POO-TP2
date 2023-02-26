package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		if (!controlLibererEtal.isVendeur(nomVendeur)) {
			System.out.println("Mais vous n'êtes pas inscrit sur notre marché aujourd'hui !");
			return;
		}
		
		String[] etatEtal = controlLibererEtal.libererEtal(nomVendeur); // pas de test pour null car nomVendeur est un vendeur
		if (Boolean.valueOf(etatEtal[0])) {
			System.out.println("Vous avez vendu "
					+ etatEtal[4] + " sur "
					+ etatEtal[3] + " " + etatEtal[2] + ".");
			System.out.println("En revoir "
					+ nomVendeur
					+ ", passez une bonne journée.");
		}
	}

}
