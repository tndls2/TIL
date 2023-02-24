package wedatalab.board.mapper;

import org.springframework.stereotype.Repository;
import wedatalab.board.domain.Board;

import java.util.List;

@Repository
public interface BoardMapper {

    int boardCount(); // 곧 생성할 BoardMapper.xml 첫번째 sql 문의 id 와 같음.
    Board getBoard(Long boardId);
    List<Board> getList();
    void uploadBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Long boardId);
    void viewCount(Long boardId);
}
