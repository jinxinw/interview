
package company.amazon.oa;

import java.util.Stack;

/**
1.打棒球得分，给了一个String input，求最终score 如果是 integer， 就加给score（有负值） 如果是“x”, 将上一个值double ，加给score； 若没有上一个值，上一个值按0计算 如果是“z”, 上一个成绩作废， score剪掉上一值 如果是“+”，将上两个值相加，然后加给score 我的解法是用一个stack挨个处理。 麻烦的是，input是个string; 每一个我都是用 string.charAt(0) == ‘x’来处理的；然后string转int调用了Integer.parseInt(string); 题目我看了半天，stack的操作应该是 z直接pop； x先pop，然后再将double的值放进去； +是先pop出来两个值，加给score后，在按原样放回去，并把他俩的和也放进去。

链接: https://instant.1point3acres.com/thread/218783/post/2292415
来源: 一亩三分地
 */
public class Baseball {
    public int getScore(String[] s) {
        if (s == null || s.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (String token : s) {
            if (isOperator(token)) {
                sum = operate(token, stack, sum);
            } else {
                stack.push(Integer.parseInt(token));
                sum += stack.peek();
            }
        }
        return sum;
    }
    
    private boolean isOperator(String s) {
        return s.equals("Z") || s.equals("X") || s.equals("+");
    }
    
    private int operate(String s, Stack<Integer> stack, int sum) {
        int res = 0;
        switch(s) {
            case "X":
                if (stack.isEmpty()) {
                    return sum;
                }
                res = sum + stack.peek() * 2;
                stack.push(stack.peek() * 2);
                break;
            case "Z":
                if (stack.isEmpty()) {
                    return sum;
                }
                res = sum - stack.pop();
                break;
            case "+":
                int v1 = stack.pop();
                int v2 = stack.pop();
                int combine = v1 + v2;
                res = sum + combine;
                stack.push(v2);
                stack.push(v1);
                stack.push(combine);
                break;
        }
        return res;
    }
    
    public static void main(String[] args) {
        Baseball b = new Baseball();
        String[] s = {"5", "-2", "4", "Z", "X", "9", "+", "+"};
        System.out.println(b.getScore(s));
    }
}
