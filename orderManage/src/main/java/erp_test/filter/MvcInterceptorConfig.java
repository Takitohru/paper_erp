package erp_test.filter;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcInterceptorConfig extends WebMvcConfigurationSupport{
	private Logger log = LoggerFactory.getLogger(MvcInterceptorConfig.class);
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
    	this.log.info("InterceptorConfiguration----addInterceptors");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/erp/login/loginView","/erp/login/loginError","/erp/login/loginDeal"
        		,"/static/**");    
        super.addInterceptors(registry);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //addResourceHandler请求路径
    //addResourceLocations 在项目中的资源路径
    //setCacheControl 设置静态资源缓存时间
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/")
    .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
    super.addResourceHandlers(registry);
    }
}
