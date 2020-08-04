package erp_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/** 项目启动方法  java或者SpringBoot两种方式都可以
 * 
 * 
 *     	登录网址127.0.0.1:8080/erp/login/loginView
 *    
 *     
 * */
@SpringBootApplication
@ServletComponentScan
public class erp_test {

	public static void main(String[] args) {
		SpringApplication.run(erp_test.class, args);
	}

}
