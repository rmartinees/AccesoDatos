package tercero;

import java.util.Set;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BeanValidation {
    public static void main(String[] args) {
        validateElemento();
    }

    public static void validateElemento() {
        ValidatorFactory factory = Validation.byDefaultProvider()
			    .configure()
			    .messageInterpolator(new ParameterMessageInterpolator())
			    .buildValidatorFactory();
        Validator validator = factory.getValidator();
        
        Elemento elemento = new Elemento();//"E", LocalDateTime.now(), null,345);

        // Validamos el objeto en elemento
        Set<ConstraintViolation<Elemento>> errores = validator.validate(elemento);

        if (errores.isEmpty()) {
            System.out.println("Elemento es válido");
        } else {
            for (ConstraintViolation<Elemento> unerror : errores) {
                System.out.println(unerror.getPropertyPath() + ": " + unerror.getMessage());
            }
        }
    }
}
