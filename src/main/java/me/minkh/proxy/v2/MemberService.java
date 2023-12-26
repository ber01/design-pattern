package me.minkh.proxy.v2;

import java.util.List;

public interface MemberService {

    void save(Member member);

    Member findById(Long id);

    List<Member> findAll();

}
