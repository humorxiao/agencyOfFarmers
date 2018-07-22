package scau.zxck.web.test;

public class UserInfoTest {
  private String isAdmin;
  private String admin_Password;
  private String admin_Cell;
  private String admin_Name;
  private String admin_Email;

  public UserInfoTest(String isAdmin, String admin_Password, String admin_Cell, String admin_Name,
      String admin_Email) {
    this.isAdmin = isAdmin;
    this.admin_Password = admin_Password;
    this.admin_Cell = admin_Cell;
    this.admin_Name = admin_Name;
    this.admin_Email = admin_Email;
  }

  public String getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(String isAdmin) {
    this.isAdmin = isAdmin;
  }

  public String getAdmin_Password() {
    return admin_Password;
  }

  public void setAdmin_Password(String admin_Password) {
    this.admin_Password = admin_Password;
  }

  public String getAdmin_Cell() {
    return admin_Cell;
  }

  public void setAdmin_Cell(String admin_Cell) {
    this.admin_Cell = admin_Cell;
  }

  public String getAdmin_Name() {
    return admin_Name;
  }

  public void setAdmin_Name(String admin_Name) {
    this.admin_Name = admin_Name;
  }

  public String getAdmin_Email() {
    return admin_Email;
  }

  public void setAdmin_Email(String admin_Email) {
    this.admin_Email = admin_Email;
  }
}
