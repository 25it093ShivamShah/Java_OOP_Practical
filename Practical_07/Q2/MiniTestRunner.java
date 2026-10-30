import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MathServiceTest {

    @Run
    public void testAddition() {
        System.out.println("Running testAddition: PASS");
    }

    @Run
    public void testSubtraction() {
        System.out.println("Running testSubtraction: PASS");
    }

    public void setupFixtures() {
        System.out.println("Setting up...");
    }

    @Run
    public void testDivisionByZero() {
        System.out.println("Running testDivisionByZero: FAIL");
        throw new ArithmeticException("Cannot divide by 0");
    }
}

public class MiniTestRunner {

    public static void runTests(Class<?> testClass) {
        int passed = 0;
        int failed = 0;
        int totalRun = 0;

        try {
            Object testInstance = testClass.getDeclaredConstructor().newInstance();
            Method[] methods = testClass.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Run.class) && method.getParameterCount() == 0) {
                    method.setAccessible(true);
                    totalRun++;

                    try {
                        method.invoke(testInstance);
                        passed++;
                    } catch (Exception e) {
                        failed++;
                        System.out.println("   --> Failed with: " + e.getCause().getMessage());
                    }
                }
            }

            System.out.println("\n--- Summary ---");
            System.out.println("Total tests executed: " + totalRun);
            System.out.println("Passed: " + passed);
            System.out.println("Failed: " + failed);
        } catch (Exception e) {
            System.err.println("Failed to initialize test suite: " + e.getMessage());
            return;
        }
    }

    public static void main(String[] args) {
        runTests(MathServiceTest.class);
    }
}
