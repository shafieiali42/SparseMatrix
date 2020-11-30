public class Node<E> {

    private E element;
    private int index;
    private Node<E> next;

    public Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }



    @Override
    public String toString() {
        if (next!=null){
            return element.toString() +" , "+ next  ;
        }else {
            return element.toString();
        }
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

}
