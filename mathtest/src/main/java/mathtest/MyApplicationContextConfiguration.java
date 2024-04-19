package mathtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class MyApplicationContextConfiguration {

    @Bean
    @Scope("singleton")
    public DBsrc dataSource() {
    	DBsrc dataSource = new DBsrc(
    			"hibernate.cfg.xml",
    			Paths.get(System.getenv("APPDATA"),
				    "postgresql",
				    "pgpass.conf"));
        return dataSource;
    }
}