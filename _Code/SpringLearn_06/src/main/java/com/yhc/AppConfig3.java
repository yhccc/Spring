package com.yhc;

import com.yhc.bean.User;
import com.yhc.injection.Category;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan(basePackages = "com.yhc.injection")
@ImportResource("applicationContext.xml")
public class AppConfig3 {

    @Bean
    public Category category(){
        Category c = new Category();
        c.setName("yy");
        c.setId(1);
        return c;
    }
}
