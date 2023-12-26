package me.minkh.proxy.v2;

import java.util.List;

public class MemberServiceImplProxy implements MemberService {

    private final MemberService memberService;

    public MemberServiceImplProxy(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void save(Member member) {
        long startTime = System.currentTimeMillis();
        memberService.save(member);
        long endTime = System.currentTimeMillis();
        System.out.println("걸린 시간 : " + (endTime - startTime) + "ms");
    }

    @Override
    public Member findById(Long id) {
        return memberService.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberService.findAll();
    }
}

