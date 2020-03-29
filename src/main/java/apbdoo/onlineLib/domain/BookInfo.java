package apbdoo.onlineLib.domain;

import apbdoo.onlineLib.utilities.AuditableObject;
import lombok.Data;

import javax.persistence.*;
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

}
