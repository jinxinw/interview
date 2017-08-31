package company.amazon.oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char pc = p.charAt(i);
            if (!map.containsKey(pc)) {
                map.put(pc, 0);
            }
            map.put(pc, map.get(pc) + 1);
        }
        int left = 0;
        int right = 0;
        int count = map.size();
        while (right < s.length()) {
            char rc = s.charAt(right);
            if (map.containsKey(rc)) {
                map.put(rc, map.get(rc) - 1);
                if (map.get(rc) == 0) {
                    count--;
                }
            }
            right++;
            while (count == 0) {
                char lc = s.charAt(left);
                if (map.containsKey(lc)) {
                    map.put(lc, map.get(lc) + 1);
                    if (map.get(lc) > 0) {
                        count++;
                    }
                }
                if (right - left == p.length()) {
                    res.add(left);
                }
                left++;
            }
        }
        return res;
    }
}
