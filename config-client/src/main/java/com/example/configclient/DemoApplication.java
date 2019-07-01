package com.example.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RestController
	@RefreshScope  				 //@RefreshScope 변경된 설정파일의 설정이 애플리케이션의 재배포과정 없이 실시간으로 반영
	public static class ConfigClientController {
		@Value("${who.am.i}")
		private String config;  //Github Repository 에 만들어둔 설정파일에서 who.am.i 값을 읽어와 반환

		@Value("${what.are.you.doing}")
		private String config2;

		@GetMapping("/test")    //설정파일 변경 적응 하기 위해서는 POST요청을 하나 날려줘야한다! 이떄 actuator의 refresh 사용
		public String test() {
			return config+" // "+config2;
		}
	} 
}
