package com.ehi.xml;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 实例化xml文件中定义的bean
 **/
@Configuration
@EnableRabbit
@ImportResource({ "classpath:config/applicationContext-*.xml" })
public class XmlConfig {

}

