package company.amazon.oa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonManager {

    class Employee {

        int id;
        String name;
        List<Employee> reports;

        Employee(int id) {
            this.id = id;
            reports = new ArrayList<>();
        }
        
        public String toString() {
            return ""+id;
        }
    }
    
    public void test() {
        List<Employee> list = generate();
        Employee res = findCommonManager(list.get(0), list.get(11), list.get(7));
        System.out.println(res);
    }
    
    public Employee findCommonManager(Employee ceo,  Employee employee1, Employee employee2) {
        Set<Integer> visited = new HashSet<>();
        List<Employee> path = new ArrayList<>();
        List<Employee> res1 = new ArrayList<>();
        List<Employee> res2 = new ArrayList<>();
        dfs(ceo, employee1, path, visited, res1);
        path = new ArrayList<>();
        dfs(ceo, employee2, path, visited, res2);
        
        System.out.print("hahah" + res1);
        System.out.print("hahah" + res2);
        
        int i = 0;
        while(i < Math.min(res1.size(), res2.size()) && res1.get(i) == res2.get(i)) {
            i++;
        }
        if (i == 0) {
            return ceo;
        } else {
            return res1.get(i - 1);
        }
    }
    
    public void dfs(Employee cur, Employee employee1, List<Employee> path, Set<Integer> visited, List<Employee> res) {
        //System.out.println(visited);
        //System.out.println(path);
        if (visited.contains(cur.id)) {
            return;
        }
        visited.add(cur.id);
        path.add(cur);
        if (cur.id == employee1.id) {
            res.addAll(path);
            return;
        }
        for (Employee e : cur.reports) {
            dfs(e, employee1, path, visited, res);  
        }
        path.remove(path.size() - 1);
        visited.remove(cur.id);
    }
    
    public List<Employee> generate() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            list.add(new Employee(i));
        }
        list.get(0).reports.add(list.get(1));
        list.get(0).reports.add(list.get(2));
        list.get(0).reports.add(list.get(3));
        list.get(0).reports.add(list.get(4));
        
        list.get(1).reports.add(list.get(5));
        list.get(1).reports.add(list.get(6));
        list.get(1).reports.add(list.get(7));
        
        list.get(3).reports.add(list.get(8));
        list.get(3).reports.add(list.get(9));
        list.get(3).reports.add(list.get(10));
        
        list.get(9).reports.add(list.get(11));
        list.get(9).reports.add(list.get(12));
        
        return list;
    }
    
    public static void main(String[] args) {
        CommonManager cm = new CommonManager();
        cm.test();
    }
}
