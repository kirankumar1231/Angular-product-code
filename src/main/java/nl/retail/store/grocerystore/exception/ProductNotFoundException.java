package nl.retail.store.grocerystore.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final String code = "101";

    public ProductNotFoundException(){
        super();
    }

    public ProductNotFoundException(String message){
        super(message);
    }
}
