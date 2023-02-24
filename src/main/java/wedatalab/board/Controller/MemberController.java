package wedatalab.board.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wedatalab.board.domain.Member;
import wedatalab.board.service.MemberService;

@Controller

@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    @GetMapping("/login")
    public String login(){
        return "/boards/login";
    }
    @PostMapping("/login")
    public String loginCheck(Member member){
        service.login(member);
        return "redirect:http://localhost:8080/board/main";
    }
    @GetMapping("/join")
    public String viewJoin(){
        return "/boards/join";
    }
    @PostMapping("/join")
    public String joinCheck(Member member){
        service.join(member);
        return "redirect:/member/login";
    }
}
