package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlEmmenagerTest {
	
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
	void testControlEmmenager() {
		assertNotNull(new ControlEmmenager(village), "Le contructeur ne doit pas rendre null");
	}
	
	@Test
	void testIsHabitant() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Asterix", 8);
		assertTrue(controlEmmenager.isHabitant("Asterix"), "Asterix doit appartenir au village");
		assertFalse(controlEmmenager.isHabitant("inconnu"), "Inconnu ne fait pas partis du village");
		controlEmmenager.ajouterDuide("Panoramix", 7, 4, 9);
		assertTrue(controlEmmenager.isHabitant("Panoramix"), "Le controleur doit aussi reconnaitre les druides");
		assertTrue(controlEmmenager.isHabitant(chef.getNom()), "Le chef est un membre de son village");
	}
	
	@Test
	void testAjouterDuide() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterDuide("Panoramix", 7, 4, 9);
		assertTrue(controlEmmenager.isHabitant("Panoramix"), "Le controleur doit ajouter Panoramix aux habitants du village");
	}
	
	@Test
	void testAjouterGaulois() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Asterix", 8);
		assertTrue(controlEmmenager.isHabitant("Asterix"), "Asterix doit appartenir au village");
	}
	
}
