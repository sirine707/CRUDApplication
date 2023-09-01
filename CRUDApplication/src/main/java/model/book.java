package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="books")
@NoArgsConstructor
@AllArgsConstructor
@Data // from Lombok; replaces Getter, Setter, ToString, etc.

public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
}
