package com.yhc.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateConverter implements Converter<String, Date> {

    private String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    // convert方法作用: String -> Date
    // Date date = SimpleDateFormat.parse(s)
    // param: 配置文件中，日期字符串 <value>1997-09-30</value>
    // return: 当把转换好的Date作为convert方法的返回值后，Spring自动的为birthday属性进行注入(赋值)
    @Override
    public Date convert(String s) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
