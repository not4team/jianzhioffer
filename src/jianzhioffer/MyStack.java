package jianzhioffer;

import java.util.Iterator;
import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型
 * 
 * <分析>： 入队：将元素进栈A 出队：判断栈B是否为空，如果为空，则将栈A中所有元素pop，并push进栈B，栈B出栈； 如果不为空，栈B直接出栈。
 * 
 * @author shixq
 *
 */
public class MyStack {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            Iterator<Integer> it = stack1.iterator();
            while (it.hasNext()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek() {
        if (stack2.empty()) {
            Iterator<Integer> it = stack1.iterator();
            while (it.hasNext()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
