package controleur;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {
	
	private Village village;
	private Chef chef;

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Init...");
		village = new Village("Village des irréductibles", 20, 10);
		chef = new Chef("Vercingetorix", 10, village);
		village.setChef(chef);
	}

	@Test
	void testControlTrouverEtalVendeur() {
		assertNotNull(new ControlTrouverEtalVendeur(village), "Le contructeur ne doit pas rendre null");
	}

	@Test
	void testTrouverEtalVendeur() {
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		final String PRODUIT = "Poisson";
		
		final String NOM_GAULOIS1 = "Astérix";
		final Gaulois ASTERIX = new Gaulois(NOM_GAULOIS1, 9);
		village.ajouterHabitant(ASTERIX);
		village.installerVendeur(ASTERIX, PRODUIT, 7);
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(NOM_GAULOIS1);
		
		final String NOM_GAULOIS2 = "Obélix";
		assertTrue(etal.getVendeur().equals(ASTERIX));
		final Gaulois OBELIX = new Gaulois(NOM_GAULOIS2, 13);
		village.ajouterHabitant(OBELIX);
		assertNull(controlTrouverEtalVendeur.trouverEtalVendeur(NOM_GAULOIS2));
		
		assertNull(controlTrouverEtalVendeur.trouverEtalVendeur("inconnue"));
	}

}
