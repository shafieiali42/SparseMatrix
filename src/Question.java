public class Question {

    private int numberOfSubject;
    private MyLinkedList<Integer> indexOfSubjects;
    private int depth;


    private MyMatrix zero;
    private MyMatrix first;
    private MyMatrix two;
    private MyMatrix three;
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
        zero = MyMatrix.calculateQuestionMatrix(socialNetwork.getInterestMatrix(), indexOfSubjects);
        for (int i = 0; i < zero.getRows().getSize(); i++) { //todo need {nul} row
            double value = zero.getRows().getElement(i).sumOfElements();
            if (value != 0) {
                OutPut outPut = new OutPut(allPeoples.getElement(zero.getRows().getElement(i).getRowNumber()), value, 0);
                outPuts.addElement(outPut);
            }
        }
    }


    public void oneDepth(SocialNetwork socialNetwork) {
        zeroDepth(socialNetwork);
        first = MyMatrix.multiplyMatrix(socialNetwork.getFriendShipMatrix(), zero);
        first = MyMatrix.calculateQuestionMatrix(first, indexOfSubjects);
        for (int i = 0; i < first.getRows().getSize(); i++) {
            double value = first.getRows().getElement(i).sumOfElements();
            if (value != 0) {
                boolean duplicated = false;
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (outPuts.getElement(j).getName().
                            equals(allPeoples.getElement(first.getRows().getElement(i).getRowNumber()))) {
                        duplicated = true;
                    }
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(allPeoples.getElement(first.getRows().getElement(i).getRowNumber()), value, 1);
                    outPuts.addElement(outPut);
                }
            }
        }
    }


    public void twoDepth(SocialNetwork socialNetwork) {
        oneDepth(socialNetwork);
        two = MyMatrix.multiplyMatrix(socialNetwork.getFriendShipMatrix(), first);
        two = MyMatrix.calculateQuestionMatrix(two, indexOfSubjects);
        for (int i = 0; i < two.getRows().getSize(); i++) {
            double value = two.getRows().getElement(i).sumOfElements();
            if (value != 0) {
                boolean duplicated = false;
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (outPuts.getElement(j).getName().
                            equals(allPeoples.getElement(two.getRows().getElement(i).getRowNumber()))) {
                        duplicated = true;
                    }
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(allPeoples.getElement(two.getRows().getElement(i).getRowNumber()), value, 2);
                    outPuts.addElement(outPut);
                }

            }
        }

    }

    public void threeDepth(SocialNetwork socialNetwork) {
        twoDepth(socialNetwork);
        three = MyMatrix.multiplyMatrix(socialNetwork.getFriendShipMatrix(), two);
        three = MyMatrix.calculateQuestionMatrix(three, indexOfSubjects);
        for (int i = 0; i < three.getRows().getSize(); i++) {
            double value = three.getRows().getElement(i).sumOfElements();
            if (value != 0) {
                boolean duplicated = false;
                for (int j = 0; j < outPuts.getSize(); j++) {
                    if (outPuts.getElement(j).getName().
                            equals(allPeoples.getElement(three.getRows().getElement(i).getRowNumber()))) {
                        duplicated = true;
                    }
                }
                if (!duplicated) {
                    OutPut outPut = new OutPut(allPeoples.getElement(three.getRows().getElement(i).getRowNumber()), value, 3);
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
