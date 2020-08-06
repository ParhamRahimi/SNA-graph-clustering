package process;

import java.util.ArrayList;

/**
 * Created by Parham on 19-Jan-18.
 */
public class Stack {
    ArrayList<Integer> stackMemory;
    int top;
    public Stack() {
        stackMemory = new ArrayList<>(0);
        top = 0;
    }

    public void push(int input){
        stackMemory.add(top, input);
        top++;
    }

    public int pop(){
        if (!isEmpty()) {
            top--;
            return stackMemory.get(top);
        }
        return Integer.MIN_VALUE;
    }

    public boolean isEmpty(){
        if (top > 0)
            return false;
        return true;
    }
}
