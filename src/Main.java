import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<String> allPeoples = new MyLinkedList<>();
        MyLinkedList<String> allSubjects = new MyLinkedList<>();
        MyLinkedList<SocialNetwork> allSocialNetworks = new MyLinkedList<>();
        MyLinkedList<Question> questions = new MyLinkedList<>();

        Scanner myScanner = new Scanner(System.in);
        int numberOfSocialNetworks = myScanner.nextInt();
        Input.getInput(numberOfSocialNetworks, allPeoples, allSubjects, allSocialNetworks, questions, myScanner);

        SocialNetwork generalSocialNetwork = SocialNetworksMerger.socialNetworkMerger(allSocialNetworks,
                allPeoples, allSubjects);

        Node<Question> node = questions.getNode(0);
        for (int i = 0; i < questions.getSize(); i++) {
            node.getElement().response(generalSocialNetwork, node.getElement().getDepth());
            node = node.getNext();
        }

    }



}
