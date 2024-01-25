package my_stack;

public class App {

    public static void main(String[] args) {
        MyStack stack = new MyStack();

        boolean isEmpty = stack.isEmpty();
        System.out.println("Is empty: " + isEmpty);

        stack.printStack();

        // New raw
        var a = new MyStackElem();
        a.data = "Foo";
        stack.push(a);

        // New constructor
        var b = new MyStackElem("Bar");
        stack.push(b);

        // Just passing data
        stack.push("Baz");

        stack.printStack();
    }
}
