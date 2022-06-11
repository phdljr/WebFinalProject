package kr.ac.kumoh.backend.Service.discount;


public class FixDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(int price, int rateOfDiscount) {

        int discountedPrice = price - rateOfDiscount;

        if (discountedPrice < 0)
            discountedPrice = -1;

        return discountedPrice;
    }
}
