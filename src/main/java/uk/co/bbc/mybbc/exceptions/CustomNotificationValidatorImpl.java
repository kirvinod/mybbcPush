package uk.co.bbc.mybbc.exceptions;


import org.springframework.stereotype.Component;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomNotificationValidatorImpl implements CustomNotificationValidator {

    ValidatorFactory valdiatorFactory = null;

    public CustomNotificationValidatorImpl() {
        valdiatorFactory = Validation.buildDefaultValidatorFactory();
    }

    @Override
    public <T> void validateFields(T object) throws InvalidNotificationInputException {

        Validator validator = valdiatorFactory.getValidator();

        Set<ConstraintViolation<T>> failedValidations = validator.validate(object);

        if (!failedValidations.isEmpty()) {
            List<String> errors = failedValidations.stream().map(failure -> failure.getPropertyPath () +": "+ failure.getMessage())
                    .collect(Collectors.toList());

            throw new InvalidNotificationInputException("Missing or Invalid Parameter(s).", errors);
        }
    }
}

