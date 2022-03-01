package ru.itis.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


@ComponentScan({"ru.itis.controllers", "ru.itis.repositories", "ru.itis.services", "ru.itis.util"})
@PropertySource("classpath:application.properties")
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.itis.repositories")
@Configuration
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter adapter,
                                                                           DataSource dataSource,
                                                                           Properties hibernateProperties){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(adapter);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties);
        entityManagerFactoryBean.setPackagesToScan("ru.itis.models");

        return entityManagerFactoryBean;
    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.valueOf(environment.getProperty("database")));

        return adapter;
    }

    @Bean
    public Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.enable_lazy_load_no_trans", environment.getProperty("hibernate.enable_lazy_load_no_trans"));

        return properties;
    }

    @Bean
    public DataSource dataSource(HikariConfig config){
        return new HikariDataSource(config);
    }

    @Bean
    public HikariConfig hikariConfig(){
        HikariConfig config = new HikariConfig();
        config.setUsername(environment.getProperty("db.user"));
        config.setPassword(environment.getProperty("db.password"));
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("db.hikari.max-pool-size"))));
        config.setDriverClassName(environment.getProperty("db.driver-class-name"));

        return config;
    }

    @Bean
    public ViewResolver viewResolver(){
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setSuffix(".ftlh");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setRequestContextAttribute("rc");

        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/views/");
        return configurer;
    }


//    @Bean
//    public ThymeleafViewResolver viewResolver(){
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setOrder(1);
//        viewResolver.setViewNames(new String[] {".html", ".xhtml"});
//        return viewResolver;
//    }

//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setPrefix("/WEB-INF/views/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setCharacterEncoding("UTF-8");
//
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//
//        return templateEngine;
//    }

}
