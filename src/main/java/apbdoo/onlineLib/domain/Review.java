package apbdoo.onlineLib.domain;

import apbdoo.onlineLib.utilities.AuditableObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Getter
@Setter
public class Review extends AuditableObject{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Book book;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
