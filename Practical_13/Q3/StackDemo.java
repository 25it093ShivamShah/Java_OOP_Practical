import java.util.EmptyStackException;

class ArrayStack<T> {
    private Object[] elements;
    private int top = 0;

    public ArrayStack(int capacity) {
        elements = new Object[capacity];
    }

    public void push(T item) {
        if (top == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[top++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = (T) elements[--top];
        elements[top] = null;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) elements[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }
}

public class StackDemo {
    public static void main(String[] args) {
        ArrayStack<String> nameStack = new ArrayStack<>(2);
        nameStack.push("Java");
        nameStack.push("Kotlin");
        nameStack.push("Scala");

        System.out.println("Top of nameStack: " + nameStack.peek());
        while (!nameStack.isEmpty()) {
            System.out.println("Popped: " + nameStack.pop());
        }

        ArrayStack<Double> doubleStack = new ArrayStack<>(5);
        doubleStack.push(3.14);
        doubleStack.push(2.71);
        System.out.println("Popped double: " + doubleStack.pop());

        try {
            doubleStack.pop();
            doubleStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Caught expected EmptyStackException on empty stack pop");
        }
    }
}
