package com.grc.web.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.grc.core.repository")
@EnableTransactionManagement
@ComponentScan("com.grc.core")
public class PersistenceConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) throws Exception {
		var em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(ds);
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setPackagesToScan("com.grc.core.model.entity");
		em.setJpaProperties(properties());
		return em;
	}
	
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		var tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(emf);
		return tx;
	}
	
	
	private Properties properties() throws Exception {
		var pp = new Properties();
		pp.setProperty("hibernate.hbm2ddl.auto", "create");
		pp.setProperty("hibernate.show_sql", "true");
		pp.setProperty("hibernate.format_sql", "true");
		pp.load(getClass().getResourceAsStream("/jpa.properties"));
		return pp;	
	}
}
