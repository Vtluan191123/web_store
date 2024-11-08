package com.shop.vtluan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
        @Bean
        public ViewResolver viewResolver() {
                final InternalResourceViewResolver bean = new InternalResourceViewResolver();
                bean.setViewClass(JstlView.class);
                bean.setPrefix("/WEB-INF/views/");
                bean.setSuffix(".jsp");
                return bean;
        }

        @SuppressWarnings("null")
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
                registry.viewResolver(viewResolver());
        }

        @Override
        public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {

                // Định nghĩa các đường dẫn cho file tĩnh
                registry.addResourceHandler("/client/**")
                                .addResourceLocations("/resources/client/");
                registry.addResourceHandler("/admin/**")
                                .addResourceLocations("/resources/admin/");

                // Thêm các thư mục con cụ thể
                registry.addResourceHandler("/client/image/**")
                                .addResourceLocations("/resources/client/image/");
                registry.addResourceHandler("/client/css/**")
                                .addResourceLocations("/resources/client/css/");
                registry.addResourceHandler("/client/lib/**")
                                .addResourceLocations("/resources/client/lib/");
                registry.addResourceHandler("/client/js/**")
                                .addResourceLocations("/resources/client/js/");

                // Bạn cũng có thể thêm các handler khác nếu cần cho phần admin, hoặc các tệp
                // JS/CSS khác
                registry.addResourceHandler("/admin/image/**")
                                .addResourceLocations("/resources/admin/image/");
                registry.addResourceHandler("/admin/css/**")
                                .addResourceLocations("/resources/admin/css/");
                registry.addResourceHandler("/admin/lib/**")
                                .addResourceLocations("/resources/admin/lib/");
                registry.addResourceHandler("/admin/js/**")
                                .addResourceLocations("/resources/admin/js/");

                // item
                registry
                                .addResourceHandler("/admin/product/delete/**")
                                .addResourceLocations("/resources/admin/");
                registry
                                .addResourceHandler("/admin/product/view/**")
                                .addResourceLocations("/resources/admin/");
                registry
                                .addResourceHandler("/admin/product/edit/**")
                                .addResourceLocations("/resources/admin/");
                registry
                                .addResourceHandler("/admin/product/**")
                                .addResourceLocations("/resources/admin/");

                registry
                                .addResourceHandler("/admin/user/**")
                                .addResourceLocations("/resources/admin/");
                registry
                                .addResourceHandler("/admin/user/delete/**")
                                .addResourceLocations("/resources/admin/");
                registry
                                .addResourceHandler("/admin/user/edit/**")
                                .addResourceLocations("/resources/admin/");
                registry
                                .addResourceHandler("/admin/user/view/**")
                                .addResourceLocations("/resources/admin/");
                registry
                                .addResourceHandler("/admin/user/search/**")
                                .addResourceLocations("/resources/admin/");

                registry.addResourceHandler("/product_detail/client/**")
                                .addResourceLocations("resources/client/");

                registry.addResourceHandler("/vnpay-payment/client/**")
                                .addResourceLocations("/resources/client/");
        }
}
