package ttu.ee.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FinalProjectApplication {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(FinalProjectApplication.class, args);
    }

}
