package scau.zxck.web.view;

import java.io.File;
import java.util.Locale;
import org.springframework.web.servlet.view.JstlView;
/**
 * @author YHX
 * @DATE 2018/9/12 0012 23:40
 */
public class DefaultJstlViews extends JstlView {
  @Override
  public boolean checkResource(Locale locale) throws Exception {
    File file = new File(this.getServletContext().getRealPath("/") + getUrl());
    return file.exists();
  }
}
