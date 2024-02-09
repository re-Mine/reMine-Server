package com.gdsc.remine.global.api_payload.code.status;

import com.gdsc.remine.global.api_payload.code.BaseCode;
import com.gdsc.remine.global.api_payload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    // common
    _OK(OK, "COMMON200", "성공입니다."),

    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
