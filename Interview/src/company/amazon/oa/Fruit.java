
package company.amazon.oa;

import java.util.ArrayList;
import java.util.List;

public class Fruit {
    public static int checkWinner(List<List<String>> codeList, List<String> shoppingCart) {
        if (codeList == null || shoppingCart == null) {
            return 0;
        }
        int index1 = 0;
        int index3 = 0;
        while(index3 < shoppingCart.size() && index1 < codeList.size()) {
       
            List<String> list = codeList.get(index1);
            boolean found = true;
            int i = 0;
            System.out.println(index1);
            System.out.println("index3 " + index3);
            while(i < list.size() && index3 + i < shoppingCart.size()) {
                if (!list.get(i).equals("anything") && !list.get(i).equals(shoppingCart.get(index3 + i))) {
                    if (index3 == 6) {
                        System.out.println("here " + i);
                    }
                    found = false;
                    break;
                }
                i++;
            }
            if (found && i == list.size()) {
                index1++;
                index3 = index3 + i;
            } else {
                index3++;
            }
        }
        if (index1 == codeList.size()) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        
        list.get(0).add("apple");
        list.get(0).add("apple");
        
        list.get(1).add("orange");
        list.get(1).add("banana");
        list.get(1).add("orange");
        
        list.get(2).add("pear");
        list.get(2).add("orange");
        list.get(2).add("anything");
        
        List<String> list2 = new ArrayList<>();
        list2.add("orange");
        list2.add("apple");
        list2.add("apple");
        list2.add("orange");
        list2.add("banana");
        list2.add("orange");
        list2.add("pear");
        list2.add("orange");
        list2.add("grape");
        
        
        int res = Fruit.checkWinner(list, list2);
        System.out.println(res);
        
    }
}
