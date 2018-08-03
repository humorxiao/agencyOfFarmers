package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;



/***
 * 功能：用户表的实体Bean,用于生成和进行用户表的各项操作
 * @author 林莹莹
 *
 */

@Table(name="user")
public class Userke extends Unique {

	//private static final long serialVersionUID = -5447152968835820888L;

  @Column(name = "username")
	private String username;
  @Column(name = "userpassword")
	private String userpassword;
  @Column(name = "permission")
	private int permission=0;//权限 0表示普通用户 1表示管理员




	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword =userpassword;
	}

	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}




	/*public static String makeMD5(String password) {
		MessageDigest md;
		try {
			// 生成一个MD5加密计算摘要
			md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(password.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			String pwd = new BigInteger(1, md.digest()).toString(16);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	public static boolean checkPassword(String userpassword,String password){
		return userpassword.equals(makeMD5(password));
	}*/


}
