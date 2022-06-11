package kr.ac.kumoh.backend.Service.discount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DiscountServiceImpl {

    private final DiscountConfig discountConfig;

    /**
     * -1 : 정액할인을 통해, 영화 값이 음수가 될 수 없음
     * -2 : 잘못된 정책 할인이 입력으로 들어옴
     */
    public int getDiscountedPrice(int price, String policy, int discountRate) {

        int discountedPrice = -2;

        if (policy.equals("fix")) {
            DiscountPolicy discountPolicy = discountConfig.fixDiscountPolicy();
            discountedPrice = discountPolicy.discount(price, discountRate);
        }
        else if (policy.equals("rate")) {
            DiscountPolicy discountPolicy = discountConfig.rateDiscountPolicy();
            discountedPrice = discountPolicy.discount(price, discountRate);
        }

        return discountedPrice;
    }
}
