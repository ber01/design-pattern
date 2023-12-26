package me.minkh.proxy.v2;

import java.util.*;

public class MemberServiceImpl implements MemberService {

    private final Map<Long, Member> repository = new HashMap<>();
    private Long id = 0L;

    @Override
    public void save(Member member) {
        member.setId(++id);
        repository.put(id, member);
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Member findById(Long id) {
        return repository.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(repository.values());
    }

}
