package com.dylabo.dydev.common.exception;

import com.dylabo.core.common.exception.message.ErrorMessageIF;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(
        shape = JsonFormat.Shape.OBJECT
)
public enum DydevErrorMessage implements ErrorMessageIF {
    NOTICE_NOT_FOUND(400, "Notice Not Found"),
    WEB_SITE_NOT_FOUND(400, "Web Site Not Found");

    private int status;
    private String message;
    private String detail;
    private String uuid;

    private DydevErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getUuid() {
        return this.uuid;
    }

    private DydevErrorMessage() {
    }
}
