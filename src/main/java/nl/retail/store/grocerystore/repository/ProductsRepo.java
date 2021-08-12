package nl.retail.store.grocerystore.repository;

import nl.retail.store.grocerystore.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepo extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByName(String name);
}
