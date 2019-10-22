package framework.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    public static ArrayList<String> findMatcherList(String reg, String string) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        ArrayList<String> res = new ArrayList<>();
        while (matcher.find()) {
            res.add(matcher.group());
        }
        return res;
    }

    public static String findMatcher(String reg, String string) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        String res = null;
        while (matcher.find()) {
            res = matcher.group();
        }
        return res;
    }
}
