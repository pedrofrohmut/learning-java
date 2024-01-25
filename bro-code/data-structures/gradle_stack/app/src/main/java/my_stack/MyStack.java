package my_stack;

public class MyStack implements IMyStack {

    private MyStackElem top;

    @Override
	public boolean isEmpty() {
        return top == null;
	}

	@Override
	public MyStackElem peek() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'peek'");
	}

	@Override
	public MyStackElem pop() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'pop'");
	}

	@Override
	public void push(MyStackElem item) {
        item.next = top;
        top = item;
	}

    @Override
	public void push(String data) {
        var item = new MyStackElem();
        item.data = data;
        item.next = top;
        top = item;
	}

	@Override
	public int search(Object obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'search'");
	}

    public void printStack() {
        MyStackElem x = top;
        while (x != null) {
            System.out.println(x.data);
            x = x.next;
        }
    }
}
