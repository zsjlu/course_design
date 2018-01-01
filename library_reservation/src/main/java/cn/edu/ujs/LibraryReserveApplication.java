package cn.edu.ujs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by DELL on 2017/12/26.
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class LibraryReserveApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryReserveApplication.class, args);
    }
}
