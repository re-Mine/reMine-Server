package com.gdsc.remine.global.api_payload.exception.handler;

import com.gdsc.remine.global.api_payload.code.BaseErrorCode;
import com.gdsc.remine.global.api_payload.exception.GeneralException;

public class BadRequestException extends GeneralException {
    public BadRequestException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
