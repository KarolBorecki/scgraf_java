package structures;

public interface IQueue {
    static final int RESIZE_FACTOR = 2;

    void push(Object o) throws IndexOutOfBoundsException;

    Object pop() throws IndexOutOfBoundsException;

    Object peek() throws IndexOutOfBoundsException;

    Object getAtIndex(int index) throws IndexOutOfBoundsException;

    boolean IsEmpty();

    void resizeQueue(int resizeFactor);

    @Override
    String toString();
}
