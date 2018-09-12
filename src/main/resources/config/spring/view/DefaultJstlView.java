package scau.zxck.web.view;

/**
 * @author YHX
 * @DATE 2018/9/12 0012 11:12
 */
import org.springframework.web.servlet.view.JstlView;

import java.io.File;
import java.util.Locale;

public class DefaultJstlView extends JstlView {
  @Override
  public boolean checkResource(Locale locale) throws Exception {
    File file=new File(this.getServletContext().getRealPath("/")+getUrl());
    return file.exists();
  }
}

