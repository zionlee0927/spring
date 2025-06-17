package com.ruah.springexample.framework.exception

import com.ruah.springexample.global.CodeValue


enum class ErrorCode(private val description: String) : CodeValue {

    // BAD_REQUEST
    INVALID_ACCOUNT("계정 형식이 잘못됨"),
    DUPLICATE_NAME("중복된 이름"),
    DUPLICATE_ACCOUNT("중복된 계정"),
    EXISTS_MASTER("마스터 계정 존재함"),
    DUPLICATE_EMAIL("중복된 이메일"),
    DUPLICATE_PHONE("중복된 연락처"),
    INVALID_PASSWORD("비밀번호 형식이 잘못됨"),
    NOT_MATCHED_TWO_PASSWORD("비밀번호와 비밀번호 확인 정보가 다름"),
    INVALID_NAME("이름 형식이 잘못됨"),
    NOT_REQUESTED("요청되지 않음"),
    NOT_AVAILABLE_CHARGE_CREDIT("크레딧 부족"),
    NOT_AVAILABLE_CHARGE_PERIOD("이용기간 만료"),
    // BAD_REQUEST - QUIZ
    INVALID_ANSWER("답변 형식이 잘못됨"),
    ALREADY_SOLVED("이미 푼 퀴즈"),

    // NOT_FOUND
    NOT_FOUND_MEMBER("회원 계정을 찾을 수 없음"),
    NOT_FOUND_ENTITY("엔티티를 찾을 수 없음"),

    // Common
    INTERNAL_SERVER_ERROR("서버 내부 에러"),
    BEAUBRAIN_EXCEPTION("정의되지 않은 에러"),
    BAD_REQUEST("잘못된 요청"),
    UNAUTHORIZED("인증 안됨"),
    NOT_FOUND("찾을 수 없는 요청"),
    URL_NOT_FOUND("해당 URL은 지원하지 않습니다."),
    FORBIDDEN("권한 없음"),
    DUPLICATE_VERSION("중복된 버전 정보");

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}