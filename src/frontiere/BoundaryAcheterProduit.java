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
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Désolé, seul les habitants du village peuvent acheter au marché !");
			return;
		}
		
		System.out.println("Quel produit voulez-vous acheter ?");
		String produit = scan.next();
		String[] nomVendeurs = controlAcheterProduit.getNomVendeursProduit(produit);
		while (nomVendeurs == null) {
			System.out.println("Désolé mais personne ne vend des " + produit + " sur le marché en ce moment !");
			System.out.println("Quel produit voulez-vous acheter ?");
			produit = scan.next();
			nomVendeurs = controlAcheterProduit.getNomVendeursProduit(produit);
		}
		
		System.out.println("Chez quel commerçant voulez-vous acheter des" + produit + " ?");
		afficherVendeurs(nomVendeurs);
		
		// sélectionne un vendeur valide
		int selection = Clavier.entrerEntier("");
		while (0 >= selection || selection > nomVendeurs.length) {
			System.out.println("Désolé mais ce vendeur n'existe pas.");
			afficherVendeurs(nomVendeurs);
			selection = Clavier.entrerEntier("Entrez le marchand à qui vous voulez acheter");
		}
		String vendeur = nomVendeurs[selection-1];
		
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeur);
		System.out.println("Bonjour " + nomAcheteur);
		System.out.println("Combien de fleurs voulez-vous acheter ?");
		
		// demande un entier jusqu'à que le montant soit satisfaisant (que l'acheteur puisse acheter son montant de produit)
		int montant = Clavier.entrerEntier("");
		int montantAcheter;
		// TODO edit this part
		while ((montantAcheter = controlAcheterProduit.stockeDisponible(vendeur, montant)) != 0) {
			System.out.println("Désolé mais je n'ai pas " + montant + " " + produit + " je n'en ai que " + (montant-montantAcheter) + ".");
			montant = Clavier.entrerEntier("Combien de fleurs voulez-vous acheter ?");
		}
		// to this
		System.out.println(nomAcheteur + " a acheté " + montant + " de fleurs à " + vendeur);
	}
	
	private void afficherVendeurs(String[] nomVendeurs) {
		for (int i = 0; i<nomVendeurs.length; i++)
			System.out.println((i+1) + " - " + nomVendeurs[i]);
	}
	
}
