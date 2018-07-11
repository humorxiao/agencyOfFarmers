package scau.zxck.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by suruijia on 2016/2/9.
 */
public class Login implements Serializable {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
