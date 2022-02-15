package com.jinwon.bookmanager.exception_handler.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    SEARCH_TYPE_INVALID(400, "SEARCH-TYPE-INVALID-ERROR", "검색하고자 하는 Type 정보가 유효하지 않습니다."),
    NOT_FOUND_BOOK(404, "NOT-FOUND-ERROR", "해당 책 정보가 존재하지 않습니다.");

    private int status;
    private String errorCode;
    private String message;
}