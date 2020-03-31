package apbdoo.onlineLib.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Favourites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Book bookFav;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private User userFav;

    @Override
    public String toString() {
        return "Favourites{" +
                "id=" + id +
                ", date=" + date +
                ", bookFav=" + bookFav +
                ", userFav=" + userFav +
                '}';
    }
}
