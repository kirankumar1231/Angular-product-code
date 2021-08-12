package nl.retail.store.grocerystore.mapper;

import nl.retail.store.grocerystore.entity.ProductEntity;
import nl.retail.store.grocerystore.model.Product;
import nl.retail.store.grocerystore.util.DateUtil;

public class Mapper {
    public static Product toProduct(final ProductEntity productEntity) {
        return Product.builder().id(productEntity.getId())
                .datesk(productEntity.getDatesk())
                .name(productEntity.getName())
                .date(DateUtil.toLocalDate(productEntity.getDate()))
                .price(productEntity.getPrice())
                .build();
    }
}
