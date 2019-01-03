package guru.springframework.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Class represents an ingredient in a recipe
 */
@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne // there may be many ingredients in a given recipe
    private Recipe recipe;

    //TODO UnitOfMesaure one-to-one String
}
