package cn.whu.gugugu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("cn.whu.gugugu.generated.mapper")
public class GuguguApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuguguApplication.class, args);
	}

}
