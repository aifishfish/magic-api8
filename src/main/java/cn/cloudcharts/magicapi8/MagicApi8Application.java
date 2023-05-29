package cn.cloudcharts.magicapi8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MagicApi8Application {

    public static void main(String[] args) {
        SpringApplication.run(MagicApi8Application.class, args);
    }

}
