import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
    String message() default "Field cannot be blank";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
    String message() default "Field exceeds maximum length";
}

class SignupForm {
    @NotBlank(message = "Username is required")
    @MaxLength(value = 10, message = "Username cannot be longer than 10 characters")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @MaxLength(value = 16, message = "Password must be at most 16 characters")
    private String password;

    public SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

public class Validator {
    public static List<String> validate(Object target) {
        List<String> errors = new ArrayList<>();
        if (target == null) {
            errors.add("Target object is null");
            return errors;
        }

        Field[] fields = target.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(target);

                if (field.isAnnotationPresent(NotBlank.class)) {
                    NotBlank annotation = field.getAnnotation(NotBlank.class);
                    if (value == null || value.toString().trim().isEmpty()) {
                        errors.add(field.getName() + ": " + annotation.message());
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength annotation = field.getAnnotation(MaxLength.class);
                    if (value != null && value.toString().length() > annotation.value()) {
                        errors.add(field.getName() + ": " + annotation.message() + " (" + annotation.value() + ")");
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Could not access field: " + field.getName());
            }
        }

        return errors;
    }

    public static void main(String[] args) {
        SignupForm form = new SignupForm("superlongusername123", "", "mysecretpasswordiswaytoolong");

        List<String> violations = validate(form);
        System.out.println("Validation errors found: " + violations.size());
        for (String err : violations) {
            System.out.println(" - " + err);
        }
    }
}
