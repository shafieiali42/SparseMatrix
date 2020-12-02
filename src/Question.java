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
        zero = MyMatrix.convertMatrixToList(socialNetwork.getInterestMatrix(),indexOfSubjects);
        for (int i = 0; i < zero.getSize(); i++) { //todo need {nul} row
            double value = zero.getElement(i).getValue();
            if (value != 0) {
                OutPut outPut = new OutPut(allPeoples.getElement(i), value, 0);
                outPuts.addElement(outPut);
            }
        }
    }


    public void oneDepth(SocialNetwork socialNetwork) {
        zeroDepth(socialNetwork);
        first = MyMatrix.multiplyMatrixToColumn(socialNetwork.getFriendShipMatrix(), zero);
//        first = MyMatrix.convertMatrixToList(first, indexOfSubjects);
        for (int i = 0; i < first.getSize(); i++) {
            double value = first.getElement(i).getValue();
            if (value != 0) {
                boolean duplicated = false;
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (outPuts.getElement(j).getName().
                            equals(allPeoples.getElement(i))) {
                        duplicated = true;
                    }
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(allPeoples.getElement(i), value, 1);
                    outPuts.addElement(outPut);
                }
            }
        }
    }


    public void twoDepth(SocialNetwork socialNetwork) {
        oneDepth(socialNetwork);
        two = MyMatrix.multiplyMatrixToColumn(socialNetwork.getFriendShipMatrix(), first);
//        two = MyMatrix.calculateQuestionMatrix(two, indexOfSubjects);
        for (int i = 0; i < two.getSize(); i++) {
            double value = two.getElement(i).getValue();
            if (value != 0) {
                boolean duplicated = false;
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (outPuts.getElement(j).getName().
                            equals(allPeoples.getElement(i))) {
                        duplicated = true;
                    }
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(allPeoples.getElement(i), value, 2);
                    outPuts.addElement(outPut);
                }

            }
        }

    }

    public void threeDepth(SocialNetwork socialNetwork) {
        twoDepth(socialNetwork);
        three = MyMatrix.multiplyMatrixToColumn(socialNetwork.getFriendShipMatrix(), two);
//        three = MyMatrix.calculateQuestionMatrix(three, indexOfSubjects);
        for (int i = 0; i < three.getSize(); i++) {
            double value = three.getElement(i).getValue();
            if (value != 0) {
                boolean duplicated = false;
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (outPuts.getElement(j).getName().
                            equals(allPeoples.getElement(i))) {
                        duplicated = true;
                    }
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(allPeoples.getElement(i), value, 3);
                    outPuts.addElement(outPut);
                }
            }
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
        for (int i = 0; i < outPuts.getSize(); i++) {
            outPuts.getElement(i).print();
        }

    }


}
