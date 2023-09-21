package band.zhaozhao.comment;

import band.zhaozhao.Interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zzhaozhao
 * @date 2023/5/28
 * @apiNote
 */
@Configuration
public class CrossConfig implements WebMvcConfigurer {
    private static final long MAX_AGE = 86400;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1 设置访问源地址
        corsConfiguration.addAllowedOrigin("*");
        // 2 设置访问源请求头
        corsConfiguration.addAllowedHeader("*");
        // 3 设置访问源请求方法
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setMaxAge(MAX_AGE);
        // 4 对接口配置跨域设置
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/users/login")
                .excludePathPatterns("/users/verifyToken")
                .excludePathPatterns("/users/register")
                .excludePathPatterns("/delivery/login")
                .excludePathPatterns("/delivery/register")
                .excludePathPatterns("/delivery/verifyToken")
                .excludePathPatterns("/homePageProduct/scrollingProducts")
                .excludePathPatterns("/category/getAllCategories")
                .excludePathPatterns("/homeProduct/productCategories")
                .excludePathPatterns("/product/productDetails")
                .excludePathPatterns("/product/addProducts")
                .excludePathPatterns("/product/getAllProducts")
                .excludePathPatterns("/product/modProducts")
                .excludePathPatterns("/product/deleteProducts")
                .excludePathPatterns("/product/selectProductByProductName")
                .excludePathPatterns("/category/addCategory")
                .excludePathPatterns("/category/deleteCategory")
                .excludePathPatterns("/category/modCategory")
                .excludePathPatterns("/category/getAllCategory")
                .excludePathPatterns("/category/getCategoryBycategoryName")
                .excludePathPatterns("/users/deleteUser")
                .excludePathPatterns("/delivery/deleteDeliver")
                .excludePathPatterns("/users/getAllUsers")
                .excludePathPatterns("/users/selectUsersByuserName")
                .excludePathPatterns("/delivery/getAllDeliver")
                .excludePathPatterns("/delivery/getAllDeliverByName")
                .excludePathPatterns("/homePageProduct/addHomePageProduct")
                .excludePathPatterns("/homePageProduct/deleteHomePageProduct")
                .excludePathPatterns("/homePageProduct/modHomePageProduct")
                .excludePathPatterns("/homePageProduct/getAllHomePageProduct")
                .excludePathPatterns("/address/modAddress")
                .excludePathPatterns("/address/deleteAddress")
                .excludePathPatterns("/swagger-ui.html")
        ;
    }
}
