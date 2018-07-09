package scau.zxck.base.exception;

import scau.zxck.base.constants.ErrorCode;

/**
 * Created by lishunpeng on 2015/10/22.
 */
public class BaseException extends Exception {
    private ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(Throwable cause, ErrorCode errorCode) {
        super(errorCode.getErrMsg(), cause);
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorCode.getErrMsg();
    }

    public int getErrorCode() {
        return errorCode.getErrCode();
    }
}
