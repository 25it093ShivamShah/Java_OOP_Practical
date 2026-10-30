import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
    boolean required() default false;
}

class UserProfile {
    @Column(name = "user_id", required = true)
    private int id;

    @Column(name = "full_name", required = true)
    private String name;

    @Column(name = "email_address")
    private String email;

    @Column(name = "user_age")
    private Integer age;

    public UserProfile() {
    }

    @Override
    public String toString() {
        return "UserProfile{id=" + id + ", name='" + name + "\'', email='" + email + "\'', age=" + age + "}";
    }
}

public class RowMapper {

    public static <T> T mapRow(String[] headers, String[] data, Class<T> targetClass) throws Exception {
        T instance = targetClass.getDeclaredConstructor().newInstance();

        Map<String, Integer> headerIndexMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            headerIndexMap.put(headers[i].trim().toLowerCase(), i);
        }

        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            Column col = field.getAnnotation(Column.class);
            String targetColName = col.name().trim().toLowerCase();
            Integer colIndex = headerIndexMap.get(targetColName);

            if (colIndex == null || colIndex >= data.length || data[colIndex] == null || data[colIndex].trim().isEmpty()) {
                if (col.required()) {
                    throw new IllegalArgumentException("Missing required column: " + col.name());
                }
                continue;
            }

            String rawValue = data[colIndex].trim();
            field.setAccessible(true);

            Object convertedValue = convertValue(rawValue, field.getType());
            field.set(instance, convertedValue);
        }

        return instance;
    }

    private static Object convertValue(String val, Class<?> targetType) {
        if (targetType == String.class) {
            return val;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(val);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(val);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(val);
        }
        return val;
    }

    public static void main(String[] args) {
        String[] headers = {"USER_ID", "FULL_NAME", "EMAIL_ADDRESS"};
        String[] dataRow = {"101", "Alex Smith", "alex@example.com"};

        try {
            UserProfile profile = mapRow(headers, dataRow, UserProfile.class);
            System.out.println("Mapped successfully:");
            System.out.println(profile);
        } catch (Exception e) {
            System.err.println("Mapping error: " + e.getMessage());
        }
    }
}
