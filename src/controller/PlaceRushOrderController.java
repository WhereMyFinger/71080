package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

public class PlaceRushOrderController extends BaseController {
	/**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeRushOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createRushOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlistOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processRushDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Rush Delivery Info");
        LOGGER.info(info.toString());
        validateRushDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateRushDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	
    }
    
    /**
     * This method validates phone number
     * @param phoneNumber
     * @return
     */
    public boolean validatePhoneNumber(String phoneNumber) {
    	//check the phoneNumber has 10 digits
    	if(phoneNumber.length() != 10) return false;
    	
    	//check the phoneNumber starts with 0
    	if(!phoneNumber.startsWith("0")) return false;
    	
    	//check the phoneNumber contans only number
    	try {
    		Integer.parseInt(phoneNumber);
    	} catch (NumberFormatException e) {
    		return false;
    	}
    	
    	return true;
    }
    
    /**
     * This method validates name
     * @param name
     * @return
     */
    public boolean validateName(String name) {
    	//check if name is nul or has special characters
    	String specialCharacters = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~0123456789";
        String[] strlCharactersArray = new String[name.length()];
        for (int i = 0; i < name.length(); i++) {
             strlCharactersArray[i] = Character
                .toString(name.charAt(i));
        }
        
        int count = 0;
        for (int i = 0; i <  strlCharactersArray.length; i++) {
            if (specialCharacters.contains( strlCharactersArray[i])) {
                count++;
            }

        }

        if (name != null && count == 0) {
            return true;
        } else {
            return false;
        }

    }
    
    /**
     * This method validates address
     * @param address
     * @return
     */
    public boolean validateAddress(String address) {
    	//check if address is null or has special characters
    	String specialCharacters = "!#$%&'()*+,-.:;<=>?@[]^_`{|}~";
        String[] strlCharactersArray = new String[address.length()];
        for (int i = 0; i < address.length(); i++) {
             strlCharactersArray[i] = Character
                .toString(address.charAt(i));
        }
        
        int count = 0;
        for (int i = 0; i <  strlCharactersArray.length; i++) {
            if (specialCharacters.contains( strlCharactersArray[i])) {
                count++;
            }

        }

        if (address != null && count == 0) {
            return true;
        } else {
            return false;
        }
    }
    

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }

}
