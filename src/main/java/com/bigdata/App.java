package com.bigdata;

import com.bigdata.demo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@EnableAutoConfiguration
@ComponentScan("com.bigdata")
public class App {

    @Autowired
    private DataConnection dataConnection;


    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("demo");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/list")
    public WrapData getData(Register register) {
        long in = System.currentTimeMillis();

        WrapData wrapData = new WrapData();
        List<User> list = new ArrayList<User>();
        if (StringUtils.isBlank(register.getDataType())) {
//            list.add(new User("zhang", "3"));
//            list.add(new User("zhang", "4"));
//            list.add(new User("Lishi", "5"));
        } else {
            list = dataConnection.getData(register) == null ? list : dataConnection.getData(register);
        }
        Collections.shuffle(list);
        wrapData.setList(list);
        wrapData.setTime((int) (System.currentTimeMillis() - in));
        return wrapData;
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

    }

}