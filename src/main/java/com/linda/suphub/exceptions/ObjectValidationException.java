package com.linda.suphub.exceptions;




// runtimexception pour idre que ecst une exception qui va se lever lors de lexecution de l'app

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {
    @Getter
    private final Set<String> violations;
    @Getter
    private final String violationSource;

}
