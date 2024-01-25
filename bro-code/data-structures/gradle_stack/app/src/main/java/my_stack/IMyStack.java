package my_stack;

public interface IMyStack {
    // Tests if this stack is empty.
    boolean isEmpty();

    // Looks at the object at the top of this stack without removing it from the stack.
    MyStackElem peek();

    // Removes the object at the top of this stack and returns that object as the value of this function.
    MyStackElem pop();

    // Pushes an item onto the top of this stack.
    void push(MyStackElem item);

    // Creates and Pushes an item onto the top of this stack.
    void push(String data);

    // Returns the 1-based position where an object is on this stack.
    int search(Object obj);

    void printStack();
}
