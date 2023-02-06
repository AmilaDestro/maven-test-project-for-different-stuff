package soloviova.mila.main.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"idBook"})
@ToString(exclude = {"idBook"})
public class Author {
    private int id;
    private int idBook;
    private String firstName;
    private String lastName;
}
