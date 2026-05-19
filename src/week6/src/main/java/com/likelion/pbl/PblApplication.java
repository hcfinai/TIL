package com.likelion.pbl;

import com.likelion.pbl.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PblApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PblApplication.class, args);

        // ✅ Step 2 동작 확인: ApplicationContext에서 Bean을 직접 꺼내보기
        MemberService memberService = context.getBean(MemberService.class);
        System.out.println("✅ Bean 가져오기 성공: " + memberService);
    }
}
