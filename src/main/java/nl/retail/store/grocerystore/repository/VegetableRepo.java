package nl.retail.store.grocerystore.repository;

import nl.retail.store.grocerystore.model.Vegetable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VegetableRepo extends JpaRepository<Vegetable, Integer> {
    List<Vegetable> findAllByName(String name);
}
