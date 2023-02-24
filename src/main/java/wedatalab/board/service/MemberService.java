package wedatalab.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wedatalab.board.domain.Member;
import wedatalab.board.mapper.MemberMapper;

@Service // 서비스 역할을 하는 것임을 명시
@RequiredArgsConstructor // Mapper 생성자를 사용할 수 있게 함
@Transactional(readOnly = true)
public class MemberService {
    private final MemberMapper memberMapper;
    @Transactional
    public void login(Member member) {
        memberMapper.login(member);

    }
    @Transactional
    public void join(Member member) {
        memberMapper.join(member);
    }

    public String checkPassword(String password){
        return memberMapper.checkPassword(password);
    }

}
