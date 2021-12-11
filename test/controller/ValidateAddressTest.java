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
class ValidateAddressTest {
	
	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"Ha Noi, true",
		"20/11 Van Don, true",
		"108 Hai$ Ba Tru%g, false"
	})
	
	void test(String address, boolean expected) {
		//when
		boolean isValided = placeRushOrderController.validateAddress(address);
		//then 
		assertEquals(expected, isValided);
	}

}
