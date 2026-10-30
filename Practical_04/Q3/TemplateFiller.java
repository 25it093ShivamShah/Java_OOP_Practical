import java.util.regex.*;

public class TemplateFiller {
    public static String fill(String template, String[] names, String[] values) {
        Pattern p = Pattern.compile("\\{(\\w+)\\}");
        Matcher m = p.matcher(template);
        StringBuffer result = new StringBuffer();

        while (m.find()) {
            String placeholder = m.group(1);
            String replacement = null;

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(placeholder)) {
                    replacement = values[i];
                    break;
                }
            }

            if (replacement == null) {
                replacement = "{" + placeholder + "}";
            }

            m.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(result);

        return result.toString();
    }
}
