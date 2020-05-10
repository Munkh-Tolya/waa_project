package edu.miu.cs545.waa_project;

import edu.miu.cs545.waa_project.domain.Admin;
import edu.miu.cs545.waa_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WaaProjectApplication implements CommandLineRunner {
    @Autowired
    UserRepository uRepo;

    public static void main(String[] args) {
        SpringApplication.run(WaaProjectApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception
    {}
}
