package com.yhc;

import com.yhc.bean.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = "com.yhc.scan")
@Import(User.class)
//@ComponentScan(basePackages = "com.yhc.scan",
//    excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, value = {Service.class}),
//    @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = {"*..User1"})}
//)
//@ComponentScan(basePackages = "com.yhc.scan", useDefaultFilters = false,
//    includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Service.class})}
//)
public class AppConfig2 {
}
