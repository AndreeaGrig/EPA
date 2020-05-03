package apbdoo.onlineLib.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityNoFoundException extends RuntimeException {

    public EntityNoFoundException(String message) {
        super(message);
    }
}
