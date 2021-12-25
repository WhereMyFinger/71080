package entity;

import java.util.Random;

import entity.order.Order;

public interface ShippingFeeCaculator {
	/**
     * This method calculates the shipping fees of order
     * @param order
     */
    public abstract int calculateShippingFee(Order order);
}
