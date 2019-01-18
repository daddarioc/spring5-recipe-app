package guru.springframework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class represents how an ingredient object is measured
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne // there is one unit of measure per ingredient
    private Ingredient ingredient;

}
