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

        System.out.println(generalSocialNetwork.getInterestMatrix());
        System.out.println(generalSocialNetwork.getFriendShipMatrix());

        for (int i = 0; i < questions.getSize(); i++) {
            questions.getElement(i).response(generalSocialNetwork.getFriendShipMatrix(),
                    generalSocialNetwork.getInterestMatrix(),
                    questions.getElement(i).getDepth());
        }

    }
}
