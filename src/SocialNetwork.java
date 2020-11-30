public class SocialNetwork {

    private MyLinkedList<String> peoples;
    private MyLinkedList<String> subjects;
    private MyMatrix interestMatrix;
    private MyMatrix friendShipMatrix;


    public SocialNetwork(MyLinkedList<String> peoples, MyLinkedList<String> subjects,
                         MyMatrix interestMatrix, MyMatrix friendShipMatrix) {
        this.peoples = peoples;
        this.subjects = subjects;
        this.interestMatrix = interestMatrix;
        this.friendShipMatrix = friendShipMatrix;
    }


    public MyLinkedList<String> getPeoples() {
        return peoples;
    }

    public void setPeoples(MyLinkedList<String> peoples) {
        this.peoples = peoples;
    }

    public MyLinkedList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(MyLinkedList<String> subjects) {
        this.subjects = subjects;
    }

    public MyMatrix getInterestMatrix() {
        return interestMatrix;
    }

    public void setInterestMatrix(MyMatrix interestMatrix) {
        this.interestMatrix = interestMatrix;
    }

    public MyMatrix getFriendShipMatrix() {
        return friendShipMatrix;
    }

    public void setFriendShipMatrix(MyMatrix friendShipMatrix) {
        this.friendShipMatrix = friendShipMatrix;
    }
}
