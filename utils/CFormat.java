package utils;

import java.util.ArrayList;

/**
 * Basic C-style string formatting and scanning.
 * The format strings can contain %d, %f and %s codes.
 * @author Adam Gawne-Cain
 */
public class CFormat {
    private static boolean accept(char t, char c, int i) {
        if (t == 'd')
            return "0123456789".indexOf(c) >= 0 || i == 0 && c == '-';
        else if (t == 'f')
            return "-0123456789.+Ee".indexOf(c) >= 0;
        else if (t == 's')
            return Character.isLetterOrDigit(c);
        throw new RuntimeException("Unknown format code: " + t);
    }

    /**
     * Returns string formatted like C, or throws exception if anything wrong.
     * @param fmt format specification
     * @param args values to format
     * @return string formatted like C.
     */
    public static String printf(String fmt, Object... args) {
        int a = 0;
        StringBuilder sb = new StringBuilder();
        int n = fmt.length();
        for (int i = 0; i < n; i++) {
            char c = fmt.charAt(i);
            if (c == '%') {
                char t = fmt.charAt(++i);
                if (t == 'd')
                    sb.append(((Number) args[a++]).intValue());
                else if (t == 'f')
                    sb.append(((Number) args[a++]).doubleValue());
                else if (t == 's')
                    sb.append(args[a++]);
                else if (t == '%')
                    sb.append(t);
                else
                    throw new RuntimeException("Unknown format code: " + t);
            } else
                sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Returns scanned values, or throws exception if anything wrong.
     * @param fmt format specification
     * @param str string to scan
     * @return scanned values
     */
    public static Object[] scanf(String fmt, String str) {
        ArrayList ans = new ArrayList();
        int s = 0;
        int ns = str.length();
        int n = fmt.length();
        for (int i = 0; i < n; i++) {
            char c = fmt.charAt(i);
            if (c == '%') {
                char t = fmt.charAt(++i);
                if (t=='%')
                    c=t;
                else {
                    int s0 = s;
                    while ((s == s0 || s < ns) && accept(t, str.charAt(s), s - s0))
                        s++;
                    String sub = str.substring(s0, s);
                    if (t == 'd')
                        ans.add(Integer.parseInt(sub));
                    else if (t == 'f')
                        ans.add(Double.parseDouble(sub));
                    else
                        ans.add(sub);
                    continue;
                }
            }
            if (str.charAt(s++) != c)
                throw new RuntimeException();
        }
        if (s < ns)
            throw new RuntimeException("Unmatched characters at end of string");
        return ans.toArray();
    }
}
