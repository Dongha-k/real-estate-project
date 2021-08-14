package com.project.real_estate_1.entity;

public enum OfferState {
    REJECTED, // 0 : 매물 올리기 거절됨
    UNRELIABLE, // 1 : 직원으로부터 허위매물인지 확인안됨
    RELIABLE, // 2 : 허위매물아닌걸로 판명
    PROVISIONAL, // 3 : 가계약금 입금 완료
    DOWN_PAY, // 4 : 계약금 입금 완료
    INTER_PAY, // 5 : 중도금 입금 완료
    SOLD_OUT // 7 : 판매 완료 (잔금 입금 완료)
}