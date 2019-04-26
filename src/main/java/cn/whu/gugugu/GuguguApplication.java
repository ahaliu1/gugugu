package cn.whu.gugugu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication

@MapperScan("cn.whu.gugugu.generated.mapper")
@ServletComponentScan("cn.whu.gugugu.commons")
public class GuguguApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuguguApplication.class, args);
	}

}
