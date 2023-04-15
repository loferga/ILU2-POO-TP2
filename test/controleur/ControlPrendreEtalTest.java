package controleur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	
	private Village village;
	private Chef chef;
	private ControlVerifierIdentite controlVerifierIdentite;
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Init...");
		village = new Village("Village des irréductibles", 20, 2);
		chef = new Chef("Vercingetorix", 10, village);
		village.setChef(chef);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
	}
	
	@Test
	void testControlPrendreEtal() {
		assertNotNull(new ControlPrendreEtal(controlVerifierIdentite, village), "Le contructeur ne doit pas rendre null");
	}
	
	@Test
	void testResteEtals() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		
		assertTrue(controlPrendreEtal.resteEtals(), "Sans aucune étal ajouté il doit en rester");
		
		final Gaulois asterix = new Gaulois("Astérix", 9);
		village.ajouterHabitant(asterix);
		village.installerVendeur(asterix, "Poisson", 4);
		assertTrue(controlPrendreEtal.resteEtals(), "Ajouter une seule étal ne suffit pas, il reste de la place");
		
		final Gaulois obelix = new Gaulois("Obélix", 9);
		village.ajouterHabitant(obelix);
		village.installerVendeur(obelix, "Sanglier", 2);
		assertFalse(controlPrendreEtal.resteEtals(), "Le maximum d'étal doit être respecté");
	}
	
	@Test
	void testPrendreEtal() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		
		final String NOM_GAULOIS1 = "Astérix";
		final Gaulois ASTERIX = new Gaulois(NOM_GAULOIS1, 9);
		village.ajouterHabitant(ASTERIX);
		assertEquals(controlPrendreEtal.prendreEtal(NOM_GAULOIS1, "Poisson", 4), 0, "La première position devrait être celle renvoyée");
		
		final String NOM_GAULOIS2 = "Obélix";
		final Gaulois OBELIX = new Gaulois(NOM_GAULOIS2, 9);
		village.ajouterHabitant(OBELIX);
		assertEquals(controlPrendreEtal.prendreEtal(NOM_GAULOIS2, "Poisson", 4), 1, "La seconde position devrait être celle renvoyée");
		
		village.partirVendeur(ASTERIX);
		final String NOM_GAULOIS3 = "Abraracourcix";
		village.ajouterHabitant(new Gaulois(NOM_GAULOIS3, 8));
		assertEquals(controlPrendreEtal.prendreEtal(NOM_GAULOIS3, "Sanglier", 3), 0, "La suppression d'une étal doit rendre sa place elligible");
		
		village.partirVendeur(OBELIX);
		final String NOM_GAULOIS5 = "Assurancetourix";
		village.ajouterHabitant(new Gaulois(NOM_GAULOIS5, 4));
		assertEquals(controlPrendreEtal.prendreEtal(NOM_GAULOIS5, "Sanglier", 3), 1, "La suppression de la dernière étal doit libérer la place");
	}
	
}