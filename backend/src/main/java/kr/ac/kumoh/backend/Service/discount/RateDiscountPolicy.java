package kr.ac.kumoh.backend.Service.discount;


public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(int price, int rateOfDiscount) {

        int discountedPrice = price * (1 - rateOfDiscount / 100);
        return discountedPrice;
    }
}
