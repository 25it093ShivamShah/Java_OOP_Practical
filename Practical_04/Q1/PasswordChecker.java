public class PasswordChecker {
    public static boolean checkLength(String pass) {
        if (pass == null) return false;
        return (pass.length() >= 8);
    }

    public static boolean checkUppercase(String pass) {
        if (pass == null) return false;
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isUpperCase(pass.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDigit(String pass) {
        if (pass == null) return false;
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(pass.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSpecial(String pass) {
        if (pass == null) return false;
        for (int i = 0; i < pass.length(); i++) {
            char ch = pass.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    public static String strength(String pass) {
        int count = 0;
        if (checkLength(pass)) { count++; }
        if (checkDigit(pass)) { count++; }
        if (checkSpecial(pass)) { count++; }
        if (checkUppercase(pass)) { count++; }
        if (count <= 1) {
            return "Weak";
        } else if (count <= 3) {
            return "Medium";
        } else {
            return "Strong";
        }
    }
}
