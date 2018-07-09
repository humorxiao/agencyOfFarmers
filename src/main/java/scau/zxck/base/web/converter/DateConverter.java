package scau.zxck.base.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        if ((text == null) || (text.equals(""))) {
            return null;
        } else {
            try {
                return (Date) dateFormat.parse(text);
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

}
