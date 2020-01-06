package com.itycu.server.config;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.itycu.server.page.table.PageTableArgumentResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 跨域支持
	 * 
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedMethods("*");
				//设置允许跨域的路径
				registry.addMapping("/**")
						//设置允许跨域请求的域名
						.allowedOrigins("*")
						//是否允许证书 不再默认开启
						.allowCredentials(true)
						//设置允许的方法
						.allowedMethods("*")
						//跨域允许时间
						.maxAge(3600);

			}
		};
	}

	/**
	 * datatable分页解析
	 * 
	 * @return
	 */
	@Bean
	public PageTableArgumentResolver tableHandlerMethodArgumentResolver() {
		return new PageTableArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(tableHandlerMethodArgumentResolver());
	}

	/**
	 * 上传文件根路径
	 */
	@Value("${files.path}")
	private String filesPath;

	/**
	 * 外部文件访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/statics/**")
				.addResourceLocations(ResourceUtils.FILE_URL_PREFIX + filesPath + File.separator);
	}

}
