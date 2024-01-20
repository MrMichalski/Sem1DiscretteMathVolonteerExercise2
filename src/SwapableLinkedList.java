import java.util.LinkedList;

public class SwapableLinkedList<E> {
    LinkedList<E> list;
    private LinkedList<E> list2;

    void swap() {
        LinkedList<E> swap = list2;
        list2 = list;
        list = swap;
    }

    public SwapableLinkedList(LinkedList<E> list, LinkedList<E> anotherList) {
        this.list = list;
        this.list2 = anotherList;
    }
}
