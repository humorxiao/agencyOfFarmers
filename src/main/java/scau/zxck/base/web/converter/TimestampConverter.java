package scau.zxck.base.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimestampConverter implements Converter<String, Timestamp> {

    @Override
    public Timestamp convert(String text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        if ((text == null) || (text.equals(""))) {
            return null;
        } else {
            try {
                return new Timestamp(format.parse(text).getTime());
            } catch (ParseException ex) {
                Calendar cal = Calendar.getInstance();
                cal.set(1970, 0, 1, 0, 0, 0);
                return new Timestamp(cal.getTime().getTime());
            }
        }
    }

}
