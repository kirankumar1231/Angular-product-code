package nl.retail.store.grocerystore.exception;

import lombok.Getter;

@Getter
public class InputDataInvalidException extends RuntimeException {
    private final String code = "102";

    public InputDataInvalidException(){
        super();
    }

    public InputDataInvalidException(String message){
        super(message);
    }
}
