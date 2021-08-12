package nl.retail.store.grocerystore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import nl.retail.store.grocerystore.constants.ProductType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private int id;
    private String datesk;
    private String name;
    @JsonIgnore
    private ProductType type;
    private double price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
