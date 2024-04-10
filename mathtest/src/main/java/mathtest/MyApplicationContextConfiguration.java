package mathtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class MyApplicationContextConfiguration {

    @Bean
    @Scope("singleton")
    public StoreData dataSource() {
    	StoreData dataSource = new StoreData();
        return dataSource;
    }
}