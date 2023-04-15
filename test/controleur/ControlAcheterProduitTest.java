package controleur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	
	private Village village;
	private Chef chef;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Init...");
		village = new Village("Village des irréductibles", 20, 10);
		chef = new Chef("Vercingetorix", 10, village);
		village.setChef(chef);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
	}

	@Test
	void testControlAcheterProduit() {
		assertNotNull(new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village), "Le contructeur ne doit pas rendre null");
	}
	
	private <T> boolean primeArrayContains(T[] array, T item) {
		for (T element : array)
			if (element.equals(item))
				return true;
		return false;
	}

	@Test
	void testGetNomVendeursProduit() {
		ControlAcheterProduit controlAcheterProduit =
				new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		final String PRODUIT = "Poisson";
		final String NOM_GAULOIS = "Asterix";
		Gaulois asterix = new Gaulois(NOM_GAULOIS, 8);
		village.installerVendeur(asterix, PRODUIT, 5);
		String[] retourFonction = controlAcheterProduit.getNomVendeursProduit(PRODUIT);
		assertTrue(retourFonction[0].equals(NOM_GAULOIS), "Le retour doit contenir asterix");
		village.installerVendeur(chef, PRODUIT, 2);
		retourFonction = controlAcheterProduit.getNomVendeursProduit(PRODUIT);
		assertTrue(primeArrayContains(retourFonction, NOM_GAULOIS)
				&& primeArrayContains(retourFonction, chef.getNom()),
				"Le retour doit contenir asterix et le chef");
	}

	@Test
	void testStockeDisponible() {
		ControlAcheterProduit controlAcheterProduit =
				new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		final String PRODUIT = "Poisson";
		final String NOM_GAULOIS = "Asterix";
		Gaulois asterix = new Gaulois(NOM_GAULOIS, 8);
		village.ajouterHabitant(asterix);
		village.installerVendeur(asterix, PRODUIT, 5);
		assertEquals(controlAcheterProduit.stockeDisponible(NOM_GAULOIS, 3), 0, "le stocke est disponible");
		assertEquals(controlAcheterProduit.stockeDisponible(NOM_GAULOIS, 6), 1, "on demande 1 produit de trop pour ce vendeur");
	}

}
