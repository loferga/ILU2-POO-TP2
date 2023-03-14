package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public boolean verifierIdentite(String acheteur) {
		return controlVerifierIdentite.verifierIdentite(acheteur);
	}
	
	// fonction donnant la liste des vendeurs vendant un produit
	public String[] getNomVendeursProduit(String produit) {
		String[] nomVendeurs = null;
		Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
		if (vendeurs != null) {
			nomVendeurs = new String[vendeurs.length];
			for (int i = 0; i<vendeurs.length; i++)
				nomVendeurs[i] = vendeurs[i].getNom();
		}
		return nomVendeurs;
	}
	
	// retourne le nombre de produit en trop, donc 0 si le stocke est disponible pour montant
	public int stockeDisponible(String nomVendeur, int montant) {
		int montantAcheter = -1;
		if (controlVerifierIdentite.verifierIdentite(nomVendeur)) {
			Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
			if (etal != null) {
				if (etal.getQuantite() < montant)
					montantAcheter = etal.getQuantite();
				else
					montantAcheter = etal.acheterProduit(montantAcheter);
			}
		}
		return montantAcheter;
	}
	
}
