package order;

import java.util.ArrayList;
import java.util.List;

public class OrderTestData {
    private static  List<String> emptyList = new ArrayList<>();
    private static List<String> blackOnly = new ArrayList<>();
    private static List<String> bothColor = new ArrayList<>();

    public static List<String> getEmptyList() {
        return emptyList;
    }

    public static List<String> getBlackOnly() {
        blackOnly.add("BLACK");
        return blackOnly;
    }

    public static List<String> getBothColor() {
        bothColor.add("BLACK");
        bothColor.add("GREY");
        return bothColor;
    }
}
