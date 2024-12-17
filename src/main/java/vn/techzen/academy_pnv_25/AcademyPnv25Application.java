package vn.techzen.academy_pnv_25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "vn.techzen.academy_pnv_25.repository") // Thay đổi theo package của repository
@EntityScan(basePackages = "vn.techzen.academy_pnv_25.entity") // Thay đổi theo package của entity
public class AcademyPnv25Application {

	public static void main(String[] args) {
		SpringApplication.run(AcademyPnv25Application.class, args);
	}


}

