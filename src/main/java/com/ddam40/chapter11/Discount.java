package com.ddam40.chapter11;

import static com.ddam40.chapter11.Chpater11.*;

public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        public final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code discountCode) {
        delay();
        return (price * (100 - discountCode.percentage) / 100);
    }
}
