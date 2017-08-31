package company.amazon.oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Foods {

    public List<List<String>> recomend(String[][] menu, String[][] personPreferences) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> allFoods = new HashSet<>();
        for (String[] m : menu) {
            String food = m[0];
            String type = m[1];
            if (!map.containsKey(type)) {
                map.put(type, new HashSet<>());
            }
            map.get(type).add(food);
            allFoods.add(food);
        }
        for (String[] p : personPreferences) {
            String name = p[0];
            String type = p[1];
            if (type.equals("*")) {
                for (String f : allFoods) {
                    List<String> list = new ArrayList<>();
                    list.add(name);
                    list.add(f);
                    res.add(list);
                }
            } else if (map.containsKey(type)) {
                for (String f : map.get(type)) {
                    List<String> list = new ArrayList<>();
                    list.add(name);
                    list.add(f);
                    res.add(list);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Foods f = new Foods();
        String[][] menu = {{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}};
        String[][] person = {{"Peter", "Italian"}, {"Adam", "American"}};
        System.out.println(f.recomend(menu, person));
        String[][] menu1 = {{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}};
        String[][] person1 = {{"Peter", "*"}};
        System.out.println(f.recomend(menu1, person1));
    }
}
