package com.likelion.pbl.config;

import com.likelion.pbl.repository.MemoryMemberRepository;
import com.likelion.pbl.repository.MemberRepository;
import com.likelion.pbl.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ✅ Step 2: 수동 주입으로 Bean 등록
// ✅ Step 3(자동 주입)으로 전환 시, 이 클래스의 @Configuration을 주석 처리하세요.
// @Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}
