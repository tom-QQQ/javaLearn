package com.spring.config;

import com.spring.bean.AnimeBean;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * @author Ning
 * @date Create in 2018/12/23
 *
 * '@Configuration'标注当前类是配置类, 并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到spring容器中
 *
 * '@PropertySource'用于设置配置文件的路径, 如果配置文件为application.properties, 则不需要进行配置; 可以设置配置文件的编码集; 配置当资源未找到时是否进行忽略
 *
 * '@ConfigurationProperties'可以将配置文件的信息读取并封装为实体类, 需要在main方法增加@EnableConfigurationProperties注解并补充配置类名以实现功能; 会读取全部指定前缀的属性值; 可以设置是否忽略错误(通常为类型转换错误)或未知的属性(属性名错误)
 *
 * '@ComponentScan'定义扫描路径从中找出需要装配的类自动装配到spring的bean容器中, 这个只针对使用形如ApplicationContext context = new AnnotationConfigApplicationContext(ConfigExample.class);方式, 如果使用@SpringBootApplication自动扫描和SpringApplication.run(LearnApplication.class, args);启动, 则依然会将有@Component注解的全部注入到容器中, 不包括@Configuration注解类里面的bean, 都需要手动进行注入, 一般用于需要注入的类不在当前module下, 位于其他module的情况, 当前下module内的所有有@Component注解的和声明为@Bean的都会被扫描到, 可以直接使用@Autoworied进行注入
 */
@Data
@Configuration
@PropertySource(value = "classpath:customize.properties", encoding = "GBK", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "example.anime")
@ComponentScan(basePackages = "com.spring.config")
public class ConfigExample {

    private String name;
    private String time;

    public ConfigExample() {
        System.out.println("ConfigExample容器启动初始化。。。");
    }

    /**
     * "@Bean"注解注册bean,同时可以指定初始化和销毁方法
     * "@Scope"指定Bean对象相对于其他对象的作用范围, 默认为singleton单例模式, prototype原型模式(每次通过容器的getBean()方法会获取到一个新的实例), request(对于每次HTTP Session, 使用session定义的Bean都将产生一个新实例), session(每次httpSession获取到一个新的实例), globalsession(每个全局的HTTP Session，使用session定义的Bean都将产生一个新实例)等
     * @return bean
     */
    @Bean(name = "animeBean", initMethod = "start", destroyMethod = "destroy")
    @Scope("prototype")
    public AnimeBean animeBean() {
        return new AnimeBean();
    }
}
