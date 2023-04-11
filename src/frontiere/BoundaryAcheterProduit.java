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
			System.out.println("D�sol�, seul les habitants du village peuvent acheter au march� !");
			return;
		}
		
		System.out.println("Quel produit voulez-vous acheter ?");
		String produit = scan.next();
		String[] nomVendeurs = controlAcheterProduit.getNomVendeursProduit(produit);
		while (nomVendeurs == null) {
			System.out.println("D�sol� mais personne ne vend des " + produit + " sur le march� en ce moment !");
			System.out.println("Quel produit voulez-vous acheter ?");
			produit = scan.next();
			nomVendeurs = controlAcheterProduit.getNomVendeursProduit(produit);
		}
		
		System.out.println("Chez quel commer�ant voulez-vous acheter des" + produit + " ?");
		afficherVendeurs(nomVendeurs);
		
		// s�lectionne un vendeur valide
		int selection = Clavier.entrerEntier("");
		while (0 >= selection || selection > nomVendeurs.length) {
			System.out.println("D�sol� mais ce vendeur n'existe pas.");
			afficherVendeurs(nomVendeurs);
			selection = Clavier.entrerEntier("Entrez le marchand � qui vous voulez acheter");
		}
		String vendeur = nomVendeurs[selection-1];
		
		System.out.println(nomAcheteur + " se d�place jusqu'� l'�tal du vendeur " + vendeur);
		System.out.println("Bonjour " + nomAcheteur);
		System.out.println("Combien de fleurs voulez-vous acheter ?");
		
		// demande un entier jusqu'� que le montant soit satisfaisant (que l'acheteur puisse acheter son montant de produit)
		int montant = Clavier.entrerEntier("");
		int montantAcheter;
		// TODO edit this part
		while ((montantAcheter = controlAcheterProduit.stockeDisponible(vendeur, montant)) != 0) {
			System.out.println("D�sol� mais je n'ai pas " + montant + " " + produit + " je n'en ai que " + (montant-montantAcheter) + ".");
			montant = Clavier.entrerEntier("Combien de fleurs voulez-vous acheter ?");
		}
		// to this
		System.out.println(nomAcheteur + " a achet� " + montant + " de fleurs � " + vendeur);
	}
	
	private void afficherVendeurs(String[] nomVendeurs) {
		for (int i = 0; i<nomVendeurs.length; i++)
			System.out.println((i+1) + " - " + nomVendeurs[i]);
	}
	
}
