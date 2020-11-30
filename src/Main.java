import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MyLinkedList<String> allPeoples = new MyLinkedList<>();
        MyLinkedList<String> allSubjects = new MyLinkedList<>();
        MyLinkedList<SocialNetwork> allSocialNetworks = new MyLinkedList<>();

        Scanner myScanner = new Scanner(System.in);
        int numberOfSocialNetworks = myScanner.nextInt();
        Input.getInput(numberOfSocialNetworks, allPeoples, allSubjects, allSocialNetworks, myScanner);




        SocialNetwork generalSocialNetwork = SocialNetworksMerger.socialNetworkMerger(allSocialNetworks,
                allPeoples, allSubjects);

        System.out.println(generalSocialNetwork.getInterestMatrix());
        System.out.println(generalSocialNetwork.getFriendShipMatrix());

    }
}
