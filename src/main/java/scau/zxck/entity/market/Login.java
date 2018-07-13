package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
@Table(name = "login")
public class Login extends Unique {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
=======
=======
>>>>>>> Stashed changes

/**
 * 友情链接表
 * Created by suruijia on 2016/1/29.
 */
@Table(name = "login")
public class Login extends Unique {
    /**
     * 友情链接名
     */
    @Column(name = "username")
    private String username;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes

    /**
     * 链接
     */
    @Column(name = "password")
    private String password;


<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
