package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
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
		fail("Not yet implemented");
	}

	@Test
	void testTrouverEtalVendeur() {
		fail("Not yet implemented");
	}

}
