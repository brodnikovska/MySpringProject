package example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DuplicateException extends Exception {

    private final String duplicate;

    @Override
    public String getMessage() {
        return "Item with "+ duplicate +" is already present.";
    }
}
