package scau.zxck.base.web.converter;

import org.springframework.core.convert.converter.Converter;

public class IntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String text) {
        if ((text == null) || (text.equals(""))) {
            return Integer.valueOf(0);
        } else if (!isDigit(text)) {
            return Integer.valueOf(-1);
        } else {
            return Integer.valueOf(Integer.parseInt(text));
        }
    }

    public boolean isDigit(String input) {
        if (input != null) {
            return input.matches("[+-]?\\d+");
        }
        return false;
    }

}
