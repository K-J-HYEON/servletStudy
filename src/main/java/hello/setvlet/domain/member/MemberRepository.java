package hello.setvlet.domain.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // MemberRepository가 아무리 많아도 static을 했기 때문에 하나만 생성이 될 것이다.
    private static long sequence = 0L;

    // 싱글톤으로 만든다.
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store에 있는 모든 값들을 꺼내서 새로운 arrayList에 넣어준다.
        // arrayList 밖에 있는 것을 조작해도 store에 있는 거를 건들지 않고 싶어서 그렇다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
