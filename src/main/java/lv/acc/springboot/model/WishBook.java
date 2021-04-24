package lv.acc.springboot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishBook {
    private Long id;
    private String title;
    private String author;
    private BookStatus bookStatus;
}
