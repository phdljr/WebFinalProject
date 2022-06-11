package kr.ac.kumoh.backend.Service.discount;

public interface DiscountPolicy {

    int discount(int price, int rateOfDiscount);
}
