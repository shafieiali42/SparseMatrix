public class MyLinkedList<E> {

    private Node<E> head;
    private Node<E> last;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }


    public MyLinkedList(Node<E> head) {
        this.head = head;
        this.last = head;
        this.size = 0;
    }


    public E getElement(int index) {
        if (index<0){
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getElement();
    }

    public Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }




    public static MyLinkedList<String> union(MyLinkedList<String> first,MyLinkedList<String> second){
        MyLinkedList<String> result = new MyLinkedList<>();
        for (int i = 0; i < first.getSize(); i++) {
            result.addElement(first.getElement(i));
        }
        for (int i = 0; i < second.getSize(); i++) {
            if (!result.contains(second.getElement(i))){
                result.addElement(second.getElement(i));
            }
        }
        return result;
    }



    public void addElement(E element) {
        Node<E> node = new Node<>(element, null);
        if (size > 0) {
            this.last.setNext(node);
            this.last = node;
        } else {
            this.head = node;
            this.last = head;
        }
        this.size++;
    }



    public boolean isEmpty(){
        return size==0;
    }


    public void delete(int index) throws OutOfBoundException { //todo need to check
        if (index>= size){
            throw new OutOfBoundException("index: "+index+" is out of bound");
        }
        if (index==0){
            head=head.getNext();
        }else if (index==(size-1)){
            last=getNode(size-2);
            last.setNext(null);
        }else {
            getNode(index-1).setNext(getNode(index+1));
        }
        size--;
    }



    public boolean contains(E element){
        for (int i = 0; i < size; i++) {
            if (getElement(i).equals(element)){
                return true;
            }
        }
        return false;
    }



    @Override
    public String toString() {
        return "[ " + head + " ]";
    }


    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getLast() {
        return last;
    }

    public void setLast(Node<E> last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
