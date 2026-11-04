package com.seemacollection.seemacollection.config;

import jakarta.persistence.EntityManagerFactory;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.seemacollection.seemacollection.h2dao",
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager"
)
@Configuration
public class H2Config {

    @Bean
    public DataSource h2DataSource (){
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:seemacollection")
                .password("")
                .username("sa")
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("h2DataSource") DataSource dataSource) {

        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.cache.use_second_level_cache", "true");
        jpaProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
        jpaProperties.put("javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
        jpaProperties.put("hibernate.generate_statistics", "false");


        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.seemacollection.seemacollection.h2Entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(jpaProperties);

        return em;
    }

    @Bean(name = {"h2TransactionManager"})
    public PlatformTransactionManager transactionManagerH2(
            @Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

//    @Bean
//    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
//        OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
//
//        // Yahan apne primary EntityManagerFactory ka EXACT bean name dalein
//        // Jo aapne apni MysqlConfig ya H2Config class mein rakha hai
//        osivFilter.setEntityManagerFactoryBeanName("h2EntityManagerFactory");
//
//        return osivFilter;
//    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource(h2DataSource())
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();
    }
}
