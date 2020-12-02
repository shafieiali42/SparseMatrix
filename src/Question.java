public class Question {

    private int numberOfSubject;
    private MyLinkedList<Integer> indexOfSubjects;
    private int depth;


    private MyLinkedList<ColumnNode> zero;
    private MyLinkedList<ColumnNode> first;
    private MyLinkedList<ColumnNode> two;
    private MyLinkedList<ColumnNode> three;
    MyLinkedList<OutPut> outPuts = new MyLinkedList<>();
    MyLinkedList<String> allPeoples;

    public Question(int numberOfSubject, MyLinkedList<Integer> indexOfSubjects, int depth, MyLinkedList<String> allPeoples) {
        this.numberOfSubject = numberOfSubject;
        this.indexOfSubjects = indexOfSubjects;
        this.depth = depth;
        this.allPeoples = allPeoples;
    }


    public int getNumberOfSubject() {
        return numberOfSubject;
    }

    public void setNumberOfSubject(int numberOfSubject) {
        this.numberOfSubject = numberOfSubject;
    }

    public MyLinkedList<Integer> getIndexOfSubjects() {
        return indexOfSubjects;
    }

    public void setIndexOfSubjects(MyLinkedList<Integer> indexOfSubjects) {
        this.indexOfSubjects = indexOfSubjects;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }


    public void zeroDepth(SocialNetwork socialNetwork) {
        zero = MyMatrix.convertMatrixToList(socialNetwork.getInterestMatrix(), indexOfSubjects);
        Node<ColumnNode> node = zero.getNode(0);
        Node<String> node1 = allPeoples.getNode(0);
        for (int i = 0; i < zero.getSize(); i++) { //todo need {nul} row
            double value = node.getElement().getValue();
            if (value != 0) {
                OutPut outPut = new OutPut(node1.getElement(), value, 0);
                outPuts.addElement(outPut);
            }
            node = node.getNext();
            node1 = node1.getNext();
        }
    }


    public void oneDepth(SocialNetwork socialNetwork) {
        zeroDepth(socialNetwork);
        first = MyMatrix.multiplyMatrixToColumn(socialNetwork.getFriendShipMatrix(), zero);
//        first = MyMatrix.convertMatrixToList(first, indexOfSubjects);
        Node<ColumnNode> node = first.getNode(0);
        Node<String> node1 = allPeoples.getNode(0);
        for (int i = 0; i < first.getSize(); i++) {
            double value = node.getElement().getValue();
            if (value != 0) {
                boolean duplicated = false;
                Node<OutPut> node2 = outPuts.getNode(0);
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (node2.getElement().getName().
                            equals(node1.getElement())) {
                        duplicated = true;
                    }
                    node2 = node2.getNext();
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(node1.getElement(), value, 1);
                    outPuts.addElement(outPut);
                }
            }
            node = node.getNext();
            node1 = node1.getNext();
        }
    }


    public void twoDepth(SocialNetwork socialNetwork) {
        oneDepth(socialNetwork);
        two = MyMatrix.multiplyMatrixToColumn(socialNetwork.getFriendShipMatrix(), first);
//        two = MyMatrix.calculateQuestionMatrix(two, indexOfSubjects);
        Node<ColumnNode> node = two.getNode(0);
        Node<String> stringNode = allPeoples.getNode(0);
        for (int i = 0; i < two.getSize(); i++) {
            double value = node.getElement().getValue();
            if (value != 0) {
                boolean duplicated = false;
                Node<OutPut> node2 = outPuts.getNode(0);
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (node2.getElement().getName().
                            equals(stringNode.getElement())) {
                        duplicated = true;
                    }
                    node2 = node2.getNext();
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(stringNode.getElement(), value, 2);
                    outPuts.addElement(outPut);
                }
            }
            node = node.getNext();
            stringNode = stringNode.getNext();
        }

    }

    public void threeDepth(SocialNetwork socialNetwork) {
        twoDepth(socialNetwork);
        three = MyMatrix.multiplyMatrixToColumn(socialNetwork.getFriendShipMatrix(), two);
//        three = MyMatrix.calculateQuestionMatrix(three, indexOfSubjects);
        Node<ColumnNode> node = three.getNode(0);
        Node<String> stringNode = allPeoples.getNode(0);
        for (int i = 0; i < three.getSize(); i++) {
            double value = node.getElement().getValue();
            if (value != 0) {
                boolean duplicated = false;
                Node<OutPut> node2 = outPuts.getNode(0);
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (node2.getElement().getName().
                            equals(stringNode.getElement())) {
                        duplicated = true;
                    }
                    node2 = node2.getNext();
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(stringNode.getElement(), value, 3);
                    outPuts.addElement(outPut);
                }
            }
            node = node.getNext();
            stringNode = stringNode.getNext();
        }


    }


    public void response(SocialNetwork socialNetwork, int depth) {
        if (depth == 0) {
            zeroDepth(socialNetwork);
        } else if (depth == 1) {
            oneDepth(socialNetwork);
        } else if (depth == 2) {
            twoDepth(socialNetwork);
        } else if (depth == 3) {
            threeDepth(socialNetwork);
        }

        MyLinkedList.sort(outPuts);
        Node<OutPut> node = outPuts.getNode(0);
        for (int i = 0; i < outPuts.getSize(); i++) {
            node.getElement().print();
            node = node.getNext();
        }

    }


}
