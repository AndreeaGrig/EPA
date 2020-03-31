package apbdoo.onlineLib.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Book book;

    @Lob
    @NotEmpty
    private String info;

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
