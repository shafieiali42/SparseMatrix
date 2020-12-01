public class Question {

    private int numberOfSubject;
    private MyLinkedList<String> subjectsOfQuestion;
    private int depth;
    private MyLinkedList<String> printedPersons;


    public Question(int numberOfSubject, MyLinkedList<String> subjectsOfQuestion, int depth) {
        this.numberOfSubject = numberOfSubject;
        this.subjectsOfQuestion = subjectsOfQuestion;
        this.depth = depth;
        printedPersons=new MyLinkedList<>();
    }


    public MyLinkedList<String> getPrintedPersons() {
        return printedPersons;
    }

    public void setPrintedPersons(MyLinkedList<String> printedPersons) {
        this.printedPersons = printedPersons;
    }

    public int getNumberOfSubject() {
        return numberOfSubject;
    }

    public void setNumberOfSubject(int numberOfSubject) {
        this.numberOfSubject = numberOfSubject;
    }

    public MyLinkedList<String> getSubjectsOfQuestion() {
        return subjectsOfQuestion;
    }

    public void setSubjectsOfQuestion(MyLinkedList<String> subjectsOfQuestion) {
        this.subjectsOfQuestion = subjectsOfQuestion;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }


    public void response(MyMatrix friendShipMatrix, MyMatrix interestMatrix,int depth) {

            MyMatrix oneDepthMatrix;
            MyMatrix twoDepthMatrix;
            MyMatrix threeDepthMatrix;





    }




}
