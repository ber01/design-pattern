package me.minkh.proxy.v2;

import me.minkh.proxy.v3.TimingInvocationHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.*;

class MemberServiceImplTest {

    @Test
    void test() {
        MemberService memberService = new MemberServiceImpl();

        Member member1 = new Member("홍길동", 29);
        Member member2 = new Member("김영희", 13);
        Member member3 = new Member("박철수", 34);

        memberService.save(member1);
        memberService.save(member2);
        memberService.save(member3);

        assertThat(memberService.findAll().size()).isEqualTo(3);

        MemberServiceImplProxy memberServiceImplProxy = new MemberServiceImplProxy(memberService);
        memberServiceImplProxy.save(member1);
        memberServiceImplProxy.save(member2);
        memberServiceImplProxy.save(member3);

        assertThat(memberService.findAll().size()).isEqualTo(6);
    }

    @Test
    void dynamicProxyTest() {
        MemberService memberService = new MemberServiceImpl(); // 실제 서비스 객체
        MemberService proxy = (MemberService) Proxy.newProxyInstance(
                MemberService.class.getClassLoader(),
                new Class<?>[]{MemberService.class},
                new TimingInvocationHandler(memberService));

        Member member = new Member("홍길동", 29);
        proxy.save(member);
        proxy.findById(1L);
        proxy.findAll();
    }

}