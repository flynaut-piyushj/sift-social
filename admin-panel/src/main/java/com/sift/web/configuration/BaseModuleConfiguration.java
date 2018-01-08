package com.sift.web.configuration;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@ComponentScan(basePackages = "com.sift.web.*")
@PropertySources({ @PropertySource("classpath:config.properties"), @PropertySource("classpath:db.properties") })
public class BaseModuleConfiguration extends WebMvcConfigurerAdapter implements SchedulingConfigurer {

	@Autowired
	private Environment environment;

	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(false);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowCredentials(false).allowedOrigins("*")
				.allowedMethods("PUT", "POST", "GET", "OPTIONS", "DELETE")
				.exposedHeaders("Authorization", "Content-Type");
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(Integer.parseInt(environment.getProperty("threadpool.size")));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/bootstrap/**").addResourceLocations("/bootstrap/");
		registry.addResourceHandler("/dist/**").addResourceLocations("/dist/");
		registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource(), true);
		return jdbcTemplate;
	}

	@Bean(name = "sqlProperties")
	public Properties getSqlPropertiesFile() throws IOException {
		Resource resource = new ClassPathResource("sqlQueries.properties");
		return PropertiesLoaderUtils.loadProperties(resource);
	}

	@Bean(name = "responseMessageProperties")
	public Properties getResponseMessagePropertiesFile() throws IOException {
		Resource resource = new ClassPathResource("responseMessages.properties");
		return PropertiesLoaderUtils.loadProperties(resource);
	}

	@Bean(name = "configProperties")
	public Properties getConfigPropertiesFile() throws IOException {
		Resource resource = new ClassPathResource("config.properties");
		return PropertiesLoaderUtils.loadProperties(resource);
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);

		return viewResolver;
	}

	@Bean
	public DriverManagerDataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(environment.getProperty("db.driver"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.userName"));
		dataSource.setPassword(environment.getProperty("db.password"));

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.repleteinc.motherspromise.beans.insta.entity" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.jdbc.batch_versioned_data", true);
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(Integer.parseInt(environment.getRequiredProperty("max.uploadsize")));
		return multipartResolver;
	}

	@Bean
	public StandardPasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder("ThisIsASecretSoChangeMe");
	}

	
	/*@Bean
	public UserDetailsService userDetailsService() {
		return new JpaUserDao();
	}
	
	@Bean
	public UserDetailsService ptntUserDetailsService(){
		return new PatientUserDaoImpl();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new UnauthorizedEntryPoint();
	}*/

	/*@Bean
	public AuthenticationManager authenticationManager(ObjectPostProcessor<Object> objectPostProcessor)
			throws Exception {
		return new AuthenticationManagerBuilder(objectPostProcessor).userDetailsService(userDetailsService())
				.passwordEncoder(passwordEncoder()).and().build();
	}*/
	
	/*@Bean
	public AuthenticationManager patientAuthenticationManager(ObjectPostProcessor<Object> objectPostProcessor)
			throws Exception {
		return new AuthenticationManagerBuilder(objectPostProcessor).userDetailsService(ptntUserDetailsService())
				.passwordEncoder(passwordEncoder()).and().build();
	}*/
	
	@Bean
	public RestTemplate doGetRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		final ClientHttpRequestFactory clientHttpRequestFactory = createRequestFactory(true, false);
		//set custom error hnadler
		restTemplate.setErrorHandler(new CustomResponseErrorHandler());
		 restTemplate.setRequestFactory(clientHttpRequestFactory);
		 return restTemplate;
	}
	
	private ClientHttpRequestFactory createRequestFactory(boolean trustSelfSignedCerts, boolean disableRedirectHandling) {
		HttpClientBuilder httpClientBuilder = HttpClients.custom().useSystemProperties();

		if (trustSelfSignedCerts) {
			//TODO : Remove Deprecated Method Usage
			httpClientBuilder.setSslcontext(initSSLContext());
			httpClientBuilder.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		}
		
		if (disableRedirectHandling) {
			httpClientBuilder.disableRedirectHandling();
		}

		HttpClient httpClient = httpClientBuilder.build();
		CookieStore cookieStore = new BasicCookieStore();
		HttpContext httpContext = new BasicHttpContext();
		httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		// ...

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		return requestFactory;
	}
	
	private SSLContext initSSLContext() {
	    try {
	    	 TrustManager[] trustAllCerts = new TrustManager[] { new MyTrustManager() };

	 	    // Install the all-trusting trust manager
	 	    SSLContext sc = SSLContext.getInstance("SSL");
	 	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
	 	    HostnameVerifier allHostsValid = new HostnameVerifier() {
	 	        public boolean verify(String hostname, SSLSession session) {
	 	            return true;
	 	        }
	 	    };
	 	    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	 	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        return sc;
	    } catch (final Exception ex) {
	        return null;
	    }
	}
	
	class MyTrustManager implements X509TrustManager 
	{
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		   return null;
		}
		
		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}
		
		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}
		
		@Override
		 public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
		
		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
	}
	
	// for rest template custom error handler
		class CustomResponseErrorHandler implements ResponseErrorHandler {
		    private  final Logger log=LoggerFactory.getLogger(CustomResponseErrorHandler.class);

		    @Override
		    public void handleError(ClientHttpResponse response) throws IOException {
		        log.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());
		    }

		    @Override
		    public boolean hasError(ClientHttpResponse response) throws IOException {
		        return  isError(response.getStatusCode());
		    }
		    
		 // check status 
		   private  boolean isError(HttpStatus status) {
		        HttpStatus.Series series = status.series();
		        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
		                || HttpStatus.Series.SERVER_ERROR.equals(series));
		    }
	}

}