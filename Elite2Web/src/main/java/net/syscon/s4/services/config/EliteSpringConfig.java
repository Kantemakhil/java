package net.syscon.s4.services.config;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aspose.words.License;
import com.syncfusion.licensing.SyncfusionLicenseProvider;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackages = {"net.syscon.s4"})
public class EliteSpringConfig implements WebMvcConfigurer{
		
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		System.out.println("================================");
        /*Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        converters.add(new ResourceHttpMessageConverter());*/
        /*converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));*/
        
    }
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.remove(6);
		converters.remove(6);
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        //converters.add(new ResourceHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
        
	}
	
	@Bean
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(-1);
	    return multipartResolver;
	}
	
	@Bean(name = "license")
	public License license() throws Exception{
		SyncfusionLicenseProvider.registerLicense("GTIlMmhhZn1ifWBmaGBifGJhfGpqampzYWBpZmppZmpoJTogMjQyIRMgKiAwPD19PTYn");
		InputStream inputStream = getClass()
				.getClassLoader().getResourceAsStream("Aspose.Words.lic");
		License license = new License();
		license.setLicense(inputStream);
		return license;
	}
	
	@Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }
	
}
