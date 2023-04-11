package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {
	
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
	void testControlVerifierIdentite() {
		assertNotNull(new ControlVerifierIdentite(village));
	}

	@Test
	void testVerifierIdentite() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		final String NOM_GAULOIS = "Asterix";
		Gaulois asterix = new Gaulois(NOM_GAULOIS, 8);
		village.ajouterHabitant(asterix);
		assertTrue(controlVerifierIdentite.verifierIdentite(NOM_GAULOIS), "Asterix doit être reconnu par le controleur");
		assertFalse(controlVerifierIdentite.verifierIdentite("Inconnu"), "Inconnu ne doit pas être reconnu");
		assertTrue(controlVerifierIdentite.verifierIdentite(chef.getNom()), "Le chef doit être reconnu");
	}

}
