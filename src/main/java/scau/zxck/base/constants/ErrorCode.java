package scau.zxck.base.constants;

/**
 * Created by lishunpeng on 2015/11/12.
 */
public enum ErrorCode {
    ENTITY_NULL_POINTER(100, "实体对象为空"),
    ENTITY_ID_NULL_POINTER(101, "实体对象主键为空"),
    PARAMETER_NULL_POINTER(102, "参数为空"),
    DB_STORE_ERROR(200, "数据库存储错误"),
    ENTITY_FIELD_ERROR(300, "实体类字段获取错误"),
    SERVICE_METHOD_NOT_VALIDATED(400, "Service方法不合法");

    private String errMsg;
    private int errCode;

    private ErrorCode(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
