package controleur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlLibererEtalTest {
	
	private Village village;
	private Chef chef;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Init...");
		village = new Village("Village des irréductibles", 20, 10);
		chef = new Chef("Vercingetorix", 10, village);
		village.setChef(chef);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
	}
	
	@Test
	void testControlLibererEtal() {
		assertNotNull(new ControlLibererEtal(controlTrouverEtalVendeur), "Le contructeur ne doit pas rendre null");
	}
	
	@Test
	void testLibererEtal() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		
		final String NOM_GAULOIS = "Asterix";
		final String PRODUIT = "Poisson";
		final Gaulois ASTERIX = new Gaulois(NOM_GAULOIS, 9);
		village.ajouterHabitant(ASTERIX);
		village.installerVendeur(ASTERIX, PRODUIT, 4);
		controlTrouverEtalVendeur.trouverEtalVendeur(NOM_GAULOIS).acheterProduit(2);
		final String[] EXPECTED_RETURN = new String[] {
				"true",
				NOM_GAULOIS,
				PRODUIT,
				"4",
				"2"
		};
		final String[] RETURN = controlLibererEtal.libererEtal(NOM_GAULOIS);
		assertEquals(RETURN[0], EXPECTED_RETURN[0], "L'étal doit être occupée");
		assertEquals(RETURN[1], EXPECTED_RETURN[1], "Le propriétaire n'est pas le bon");
		assertEquals(RETURN[2], EXPECTED_RETURN[2], "Le produit n'est pas le bon");
		assertEquals(RETURN[3], EXPECTED_RETURN[3], "La quantité initiale n'est pas la bonne");
		assertEquals(RETURN[4], EXPECTED_RETURN[4], "Le calcul des produit vendus n'est pas le bon");
	}
	
}