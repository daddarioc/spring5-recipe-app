package guru.springframework.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Class represents how an ingredient object is measured
 */
@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne // there is one unit of measure per ingredient
    private Ingredient ingredient;
}
