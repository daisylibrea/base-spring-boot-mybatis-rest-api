package dhl.pers.student.info.api.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@ConfigurationProperties(prefix="spring.datasource")
@Configuration
public class DBConfig {
private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);
	
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private int minPoolSize;
	private int minimumIdle;
	private int maxPoolSize;
	private int idleTimeout;
	private int maxLifetime;
	private String mapperLocations;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public int getMinPoolSize() {
		return minPoolSize;
	}
	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}
	public int getMinimumIdle() {
		return minimumIdle;
	}
	public void setMinimumIdle(int minimumIdle) {
		this.minimumIdle = minimumIdle;
	}
	public int getMaxPoolSize() {
		return maxPoolSize;
	}
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}
	public int getIdleTimeout() {
		return idleTimeout;
	}
	public void setIdleTimeout(int idleTimeout) {
		this.idleTimeout = idleTimeout;
	}
	public int getMaxLifetime() {
		return maxLifetime;
	}
	public void setMaxLifetime(int maxLifetime) {
		this.maxLifetime = maxLifetime;
	}
	public String getMapperLocations() {
		return mapperLocations;
	}
	public void setMapperLocations(String mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
	
	@Primary
	@Bean(name="dbDataSource")
	public DataSource dbDataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setPoolName("dbDataSource");
		hikariConfig.setMinimumIdle(minimumIdle);
		hikariConfig.setMaximumPoolSize(maxPoolSize);
		hikariConfig.setIdleTimeout(idleTimeout);
		hikariConfig.setMaxLifetime(maxLifetime);
		
		logger.info("minimumIdle: " + minimumIdle );
		logger.info("maxPoolSize: " + maxPoolSize );
		logger.info("idleTimeout: " + getIdleTimeout() );
		logger.info("maxLifetime: " + maxLifetime );
		
 		return new HikariDataSource(hikariConfig);
		
	}
	
	@Primary
	@Bean(name = "dbSqlSessionFactory")
	public SqlSessionFactory dssSqlSessionFactory(@Qualifier("dbDataSource") DataSource dataSource) throws Exception {
		 try {
             SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
             sessionFactoryBean.setDataSource(dataSource);
             
             Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
             sessionFactoryBean.setMapperLocations(resources);
             
             return sessionFactoryBean.getObject();
         } catch (IOException e) {
             logger.error("An error occurred while resolving DSS mapper-*.xml files.", e);
             throw e;
         } catch (Exception e) {
             logger.error("An error occurred while creating the mybatis DSS SqlSessionFactoryBean.", e);
             throw e;
         }
	}
	
	@Primary
	@Bean(name = "dbSqlSessionTemplate")
	public SqlSessionTemplate dssSqlSessionTemplate(@Qualifier("dbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
