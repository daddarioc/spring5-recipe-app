package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a category in the database
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories") // a category can have any number of recipes
    private Set<Recipe> recipe;

}
