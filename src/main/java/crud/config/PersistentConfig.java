package crud.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("crud")
@PropertySource("classpath:config.properties")
public class PersistentConfig {

    @Resource
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setUrl(environment.getRequiredProperty("db.url"));
        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("db.username"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("db.password"));

        return driverManagerDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPackagesToScan(environment.getProperty("scan"));

        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class); //см.
        entityManagerFactoryBean.setJpaProperties(getProperties());
        return entityManagerFactoryBean;
    }


    private Properties getProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        return properties;
    }

 /*
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&serverTimezone=UTC");
        properties.put("hibernate.connection.password", "root");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(UserService.class);
        factoryBean.setAnnotatedClasses(User.class);

        return factoryBean;
    }
  */
}
