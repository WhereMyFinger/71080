package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author LeXuanNguyen_20173291
 *
 */
class ValidateNameTest {
	
	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"Le Xuan Nguyen, true",
		"andrew123, false",
		"avdvjj(>:, false"
	})

	
	void test(String name, boolean expected) {
		//when
		boolean isValided = placeRushOrderController.validateName(name);
		//then 
		assertEquals(expected, isValided);
		
	}

}
