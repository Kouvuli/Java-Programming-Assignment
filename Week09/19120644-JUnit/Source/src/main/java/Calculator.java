import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculation {
    public int priority(String a){
        if (a.equals("+")||a.equals("-")){
            return 1;
        }
        else if (a.equals("/")||a.equals("%")||a.equals("*")){
            return 2;
        }
        else if (a.equals("^")){
            return 3;
        }
        return 0;
    }
    public boolean isOperand(String a){
        if (StringUtils.isNumeric(a)){
            return true;
        }
        return false;
    }
    public boolean isOperation(String a){
        if (a.equals("+")||a.equals("-")||a.equals("*")||a.equals("/")||a.equals("^")){
            return true;
        }
        else
            return false;
    }

    public String infixToPostfix(String a){
        Stack<String> stack=new Stack<>();
        Queue<String> queue = new LinkedList<>();
        for (int i=0;i<a.length();i++){
            String currChar=String.valueOf(a.charAt(i));
            if (currChar.equals(" ")){
                continue;
            }
            if (isOperand(currChar)){
                queue.add(currChar);
            }else if(isOperation(currChar)){
                if (stack.size()==0){
                    stack.push(currChar);

                }
                else{
                    if (stack.peek().equals("(")){
                        stack.push(currChar);

                    }
                    else if (priority(currChar)>priority(stack.peek())){
                        stack.push(currChar);
                    }
                    else if (priority(currChar)<=priority(stack.peek())){
                        queue.add(stack.peek());
                        stack.pop();
                        i--;
                    }
                }
            }
            else if (currChar.equals("(")){
                stack.push(currChar);
            } else if (currChar.equals(")")) {
                while (!stack.peek().equals("(")) {
                    queue.add(stack.peek());
                    stack.pop();
                }
                stack.pop();
            }

        }
        while (stack.size()!=0){
            queue.add(stack.peek());
            stack.pop();
        }
        String result="";
        while (queue.size()!=0) {
            result+=queue.peek()+" ";
            queue.remove();

        }
        return result;
    }
    public int evaluate(String postfix){
        Stack<String> stack=new Stack<>();

        for(int i=0;i<postfix.length();i++){
            String currChar=String.valueOf(postfix.charAt(i));
            if (currChar.equals(" ")){
                continue;
            }
            if (isOperand(currChar)){
                stack.push(currChar);
            }
            else if (isOperation(currChar)){

                String b=stack.peek();
                stack.pop();
                String a=stack.peek();
                stack.pop();
                if (currChar.equals("+")){
                    int temp=Integer.parseInt(a)+Integer.parseInt(b);
                    stack.push(String.valueOf(temp));
                }
                else if (currChar.equals("-")){
                    int temp=Integer.parseInt(a)-Integer.parseInt(b);
                    stack.push(String.valueOf(temp));
                }
                else if (currChar.equals("*")){
                    int temp=Integer.parseInt(a)*Integer.parseInt(b);
                    stack.push(String.valueOf(temp));
                }
                else if (currChar.equals("/")){
                    int temp=Integer.parseInt(a)/Integer.parseInt(b);
                    stack.push(String.valueOf(temp));
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
