package wedatalab.board.mapper;

import org.springframework.stereotype.Repository;
import wedatalab.board.domain.Member;

@Repository
public interface MemberMapper {
    void join(Member member);
    void login(Member member);
    String checkPassword(String password);
}
