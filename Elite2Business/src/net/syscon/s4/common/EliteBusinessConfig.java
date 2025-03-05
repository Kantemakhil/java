package net.syscon.s4.common;

import javax.sql.DataSource;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import net.syscon.s4.common.beans.UserRoleInfo;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableTransactionManagement
@EnableScheduling
public class EliteBusinessConfig implements TransactionManagementConfigurer {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Bean
	public CacheManager cacheManager() {
		return CacheManagerBuilder.newCacheManagerBuilder()
				.withCache("userRoleInfo", 
						CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, UserRoleInfo.class, 
							ResourcePoolsBuilder.newResourcePoolsBuilder()
			                .heap(100, EntryUnit.ENTRIES) 
			                .offheap(10, MemoryUnit.MB))
							.withExpiry(Expirations.timeToIdleExpiration(Duration.of(30, TimeUnit.MINUTES))
							)
						)
				.build(true);
	}

	@Bean
	public PlatformTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager(dataSource);
	}
}
