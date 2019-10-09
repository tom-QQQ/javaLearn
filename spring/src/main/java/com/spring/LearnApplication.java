package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * '@SpringBootApplication'包含@EnableAutoConfiguration注解: 将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器
 *
 * '@EnableConfigurationProperties'和配置类的@ConfigurationProperties配合使用才能实现读取配置文件并封装成实体类的功能, 参见{@link ConfigExample}
 * @author Ning
 */
@SpringBootApplication
@EnableConfigurationProperties
public class LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);

		// 参数应该为配置文件类
//		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigExample.class);
//
//		AnimeBean animeBean = (AnimeBean) context.getBean("animeBean");
//		animeBean.start();
//		System.out.println(animeBean);

//		AnimeBean bean = (AnimeBean) context.getBean("animeBean");
//		bean.start();
//		System.out.println(bean);

		//System.out.println(animeBean.equals(bean));
	}
}

