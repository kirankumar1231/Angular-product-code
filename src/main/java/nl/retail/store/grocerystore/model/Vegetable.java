package nl.retail.store.grocerystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vegetable {
    @Id
    @GeneratedValue
    private int id;
    private String datesk;
    private String name;
    private double price;
    private String date;
}
