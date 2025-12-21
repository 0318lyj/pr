package com.board.pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //생성/수정 시간 자동 기록 활성화
@SpringBootApplication
public class PrApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrApplication.class, args);
	}
}
