import java.util.Scanner;

public class Input {

    public static void getInput(int numberOfSocialNetworks, MyLinkedList<String> allPeoples,
                                MyLinkedList<String> allSubjects,
                                MyLinkedList<SocialNetwork> allSocialNetworks,
                                MyLinkedList<Question> questions, Scanner myScanner) {


        for (int i = 0; i < numberOfSocialNetworks; i++) {
            int numberOfSubject = myScanner.nextInt();
            MyLinkedList<String> thisNetworksSubject = new MyLinkedList<>();
            MyLinkedList<String> thisNetworksPeople = new MyLinkedList<>();
            for (int j = 0; j < numberOfSubject; j++) {
                String subjectName = myScanner.next();
                thisNetworksSubject.addElement(subjectName);
            }
            Node<String> node = thisNetworksSubject.getNode(0);
            for (int j = 0; j < thisNetworksSubject.getSize(); j++) {
                if (!allSubjects.contains(node.getElement())) {
                    allSubjects.addElement(node.getElement());
                }
                node = node.getNext();
            }


            MyLinkedList<RowNode> rowsOfInterest = new MyLinkedList<>();
            int numberOfPeople = myScanner.nextInt();
            for (int j = 0; j < numberOfPeople; j++) {// define the j-th row of interest matrix:
                String peopleName = myScanner.next();
                if (!allPeoples.contains(peopleName)) {
                    allPeoples.addElement(peopleName);
                }
                thisNetworksPeople.addElement(peopleName);
                int fi = myScanner.nextInt();
                MyLinkedList<ColumnNode> columns = new MyLinkedList<>();
                for (int k = 0; k < fi; k++) {
                    String compound = myScanner.next();
                    int indexOfDots = compound.indexOf(':');
                    int index = Integer.parseInt(compound.substring(0, indexOfDots));
                    double valueOfInterest = Double.parseDouble(compound.substring(indexOfDots + 1));

                    ColumnNode column = new ColumnNode(index, valueOfInterest);//todo
                    columns.addElement(column);
                }
                RowNode row = new RowNode(j, columns);
                rowsOfInterest.addElement(row);
            }


            MyLinkedList<Integer> personIndexOfWhoHasFriends = new MyLinkedList<>();
            int r = myScanner.nextInt();
            MyLinkedList<RowNode> rowsOfFriendShip = new MyLinkedList<>();
            for (int j = 0; j < r; j++) {// define the j-th row of interest matrix:
                int personIndex = myScanner.nextInt();
                int di = myScanner.nextInt();
                MyLinkedList<ColumnNode> columns = new MyLinkedList<>();
                for (int k = 0; k < di; k++) {
                    String compound = myScanner.next();
                    int indexOfDots = compound.indexOf(':');
                    int index = Integer.parseInt(compound.substring(0, indexOfDots));
                    double valueOfFriendShip = Double.parseDouble(compound.substring(indexOfDots + 1));
                    ColumnNode column = new ColumnNode(index, valueOfFriendShip);
                    columns.addElement(column);
                }
                RowNode row = new RowNode(personIndex, columns);
                personIndexOfWhoHasFriends.addElement(personIndex);
                rowsOfFriendShip.addElement(row);
            }

            for (int j = 0; j < numberOfPeople; j++) {
                if (!personIndexOfWhoHasFriends.contains(j)) {
                    MyLinkedList<ColumnNode> columns = new MyLinkedList<>();
//                    columns.addElement(new ColumnNode(0, 0));
                    RowNode row = new RowNode(j, columns);
                    rowsOfFriendShip.addElement(row);
                }
            }


            MyMatrix interestMatrix = new MyMatrix(numberOfPeople, numberOfSubject, rowsOfInterest);
            MyMatrix friendShipMatrix = new MyMatrix(numberOfPeople, numberOfPeople, rowsOfFriendShip);
            SocialNetwork socialNetwork = new SocialNetwork(thisNetworksPeople, thisNetworksSubject, interestMatrix, friendShipMatrix);
            allSocialNetworks.addElement(socialNetwork);
        }

        int numberOfQuestions = myScanner.nextInt();
        for (int j = 0; j < numberOfQuestions; j++) {
            int numberOfSubjects = myScanner.nextInt();
            MyLinkedList<String> subjectsOfQuestion = new MyLinkedList<>();
            for (int k = 0; k < numberOfSubjects; k++) {
                subjectsOfQuestion.addElement(myScanner.next());
            }
            int depth = myScanner.nextInt();
            MyLinkedList<Integer> indexes = new MyLinkedList<>();
            Node<String> node = allSubjects.getNode(0);
            for (int i = 0; i < allSubjects.getSize(); i++) {
                if (subjectsOfQuestion.contains(node.getElement())) {
                    indexes.addElement(i);
                }
                node = node.getNext();
            }
            Question question = new Question(numberOfSubjects, indexes, depth, allPeoples);
            questions.addElement(question);
        }


    }
}



