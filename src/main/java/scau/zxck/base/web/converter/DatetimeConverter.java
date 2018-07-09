package scau.zxck.base.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeConverter implements Converter<String, Date> {

    @Override
    public Date convert(String text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        if ((text == null) || (text.equals(""))) {
            return null;
        } else {
            try {
                return format.parse(text);
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

}
