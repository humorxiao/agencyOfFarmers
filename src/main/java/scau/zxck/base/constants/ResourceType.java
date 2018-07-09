package scau.zxck.base.constants;

/**
 * Created by lishunpeng on 2015/11/12.
 */
public enum ResourceType {
    MENU(1),
    URL(2),
    BUTTON(3);

    private int code;
    private ResourceType(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
