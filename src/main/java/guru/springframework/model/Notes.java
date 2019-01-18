package guru.springframework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class that represents a note for a given recipe
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob    // indicates to Hibernate this will be larger than the standard 255 char string
    private String recipeNote;

    @OneToOne   // only one note per one recipe
    private Recipe recipe;

}
