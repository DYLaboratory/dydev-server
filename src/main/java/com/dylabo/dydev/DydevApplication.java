package com.dylabo.dydev;

import com.dylabo.core.DylaboBasePackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = { DylaboBasePackage.class, DydevBasePackage.class })
@ComponentScan(basePackageClasses = { DylaboBasePackage.class, DydevBasePackage.class })
@EntityScan(basePackageClasses = { DylaboBasePackage.class, DydevBasePackage.class })
public class DydevApplication {

    public static void main(String[] args) {
        SpringApplication.run(DydevApplication.class, args);
    }

}
