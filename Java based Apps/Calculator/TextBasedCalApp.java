package Calculator;

import java.util.Stack;

public class TextBasedCalApp {
    @SuppressWarnings("unused")
    public static void calculate(String text){
        char ch[] = text.toCharArray();
        Stack<String> stack = new Stack<String>();
        Stack<Character> operation = new Stack<Character>();
        double ans=0;
        int i=0; 
        int a=0;
        // 11*12/5
        while(i<ch.length){
            StringBuilder sb= new StringBuilder();
            if(ch[i]>=48 && ch[i]<=57){
                int j=i;
                while(ch[i]>=48 && ch[i]<=57){
                    sb.append(ch[i]);
                    j++;
                }
                double num2 = Double.parseDouble(sb.toString());
                if(operation.peek()=='*' || operation.peek()=='/' ){
                    double num1 = Double.parseDouble(stack.pop());
                    char c = operation.pop();
                    switch(c){
                        case '*': 
                        ans=num1*num2;
                        case '/':
                        ans=num1/num2;

                    }
                }
                i=j;
                stack.push(sb.toString());
            }
            else {
                // if(d1=0){}
                operation.push(ch[i]);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        // String str=  "3*4+6/2-7*2";
        String str3="3*5";
        calculate(str3);
    }
}
