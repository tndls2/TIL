package wedatalab.board.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wedatalab.board.domain.Board;
import wedatalab.board.service.BoardService;

@Controller

@RequestMapping("/board/**")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;
    @GetMapping("/hello")
    public String Hello() {
        return "/boards/hello";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("cnt", service.boardCount());
        model.addAttribute("test", service.boardList());

        return "/boards/hello";
    }
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("list", service.boardList());

        return "/boards/main";
    }

    @GetMapping("/view")
    public String viewBoard(Model model, Long boardId) {
        service.viewCount(boardId); // 추가
        model.addAttribute("halo", service.getBoard(boardId));

        return "/boards/view";
    }
    @GetMapping("/upload")
    public String uploadBoardForm() {
        return "/boards/upload";
    }
    @PostMapping("/upload")
    public String uploadBoard(Board board) {
        service.uploadBoard(board);
        return "redirect:/board/main";
    }

    @GetMapping("/update")
    public String updateBoardForm(Model model, Long boardId) {
        model.addAttribute("update", service.getBoard(boardId));

        return "/boards/update";
    }

    @PostMapping("/update")
    public String updateBoard(Board board) {
        service.updateBoard(board);
        return "redirect:/board/main";
    }

    @GetMapping("/delete")
    public String deleteBoard(Long boardId) {
        service.deleteBoard(boardId);
        return "redirect:/board/main";
    }

}