package it.jpa.course.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({ "it.jpa.course.repositories" })
@EnableTransactionManagement
@ComponentScan({ "it.jpa.course.repositories" })
public class HibernateConfiguration {

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("hibernateSpring");
		return factoryBean;
	}
	
	@Bean 
	public DataSource getDataSource() { 
	  JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup(); 
	  return jndiDataSourceLookup.getDataSource("java:/PostgresDS"); 
	}
	 
	@Bean(name = "transactionManager")
	public JpaTransactionManager JpaTransactionManagerBean() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

}