package system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(value = "system.mapper")
@ServletComponentScan
public class DoApp {
    public static void main(String[] args) {
        SpringApplication.run(DoApp.class,args);
    }
}
