package kr.ac.kumoh.backend.domain;

public enum ResponseStatus {
    Success, Fail,
    Duplicated,
    NonExistUser, NonExistMovie, CantSaveSeat,
    AlreadyGiveGrade,
    AlreadyExist,
    PriceIsNegative, WrongDiscountPolicy
}
