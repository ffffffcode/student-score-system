package ex.aaronfae.student.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/left").setViewName("left");
		registry.addViewController("/leftSub").setViewName("leftSub");

		registry.addViewController("/xsInfo").setViewName("xsInfo");
		registry.addViewController("/xsDetails").setViewName("xsDetails");
		registry.addViewController("/kcInfo").setViewName("kcInfo");
		registry.addViewController("/xscjInfo").setViewName("xscjInfo");

		registry.addViewController("/addXs").setViewName("addXs");
		registry.addViewController("/addKc").setViewName("addKc");
		registry.addViewController("/addXscj").setViewName("addXscj");

		registry.addViewController("/toUpdateKc").setViewName("updateKc");
		registry.addViewController("/toUpdateXs").setViewName("updateXs");

		registry.addViewController("/").setViewName("xsInfo");
		registry.addViewController("/index").setViewName("xsInfo");

		super.addViewControllers(registry);
	}

	/**
	 * 使用FastJson替代Spring Boot默认的Jackson
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		SerializerFeature serializerFeatures = SerializerFeature.PrettyFormat;
		fastJsonConfig.setSerializerFeatures(serializerFeatures);

		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		// 处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);

		converters.add(fastJsonHttpMessageConverter);

		super.configureMessageConverters(converters);
	}
}
