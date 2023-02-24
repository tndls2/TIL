package wedatalab.board.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long memberId;
    private String name;
    private String password;
}
