package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


/**
 * @author LeXuanNguyen_20173291
 *
 */
class ValidatePhoneTest {
	
	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"0123456789, true",
		"01234, false",
		"abc123, false",
		"1234567890, false"
	})

	void test(String phone, boolean expected ) {
		//when
		boolean isValided = placeRushOrderController.validatePhoneNumber(phone);
		//then 
		assertEquals(expected, isValided);
	}

}
