package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		System.out.println("Quel produit voulez-vous acheter ?");
		String produit = scan.next();
		System.out.println("Chez quel commerçant voulez-vous acheter des fleurs ?");
		controlAcheterProduit.afficherVendeurs();
	}
	
	private void afficherVendeurs() {
		// ne pas retirer l'acheteur de la liste si c'est lui même si il achète son produit
	}
}
