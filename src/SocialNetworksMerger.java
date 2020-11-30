public class SocialNetworksMerger {


    public static SocialNetwork mergeTwoSocialNetwork(SocialNetwork socialNetwork1,
                                                      SocialNetwork socialNetwork2,
                                                      MyLinkedList<String> allPeoples,
                                                      MyLinkedList<String> allSubjects) {
        MyLinkedList<RowNode> rowsOfInterestMatrix = new MyLinkedList<>();
        for (int i = 0; i < allPeoples.getSize(); i++) {
            String peopleName = allPeoples.getElement(i);
            RowNode rowNode = new RowNode();
            rowNode.setRowNumber(i);
            int index1 = findIndex(socialNetwork1.getPeoples(), peopleName);
            int index2 = findIndex(socialNetwork2.getPeoples(), peopleName);
            if (index1 < 0) {
                RowNode rowNode1 =new RowNode();
                rowNode1.setColumns(new MyLinkedList<>());
                rowNode=addToRow(socialNetwork2.getInterestMatrix().getTheRowNodeOfTheIndexRow(index2),rowNode1,
                        socialNetwork2.getSubjects(),socialNetwork1.getSubjects(),allSubjects);
//                rowNode = socialNetwork2.getInterestMatrix().getRows().getElement(index2);//todo maybe should get by Index
            } else if (index2 < 0) {
                RowNode rowNode1 =new RowNode();
                rowNode1.setColumns(new MyLinkedList<>());
                rowNode=addToRow(socialNetwork1.getInterestMatrix().getTheRowNodeOfTheIndexRow(index1),rowNode1,
                        socialNetwork1.getSubjects(),socialNetwork2.getSubjects(),allSubjects);
//                rowNode = socialNetwork1.getInterestMatrix().getRows().getElement(index1);// ''''''''''
            } else {
                rowNode = addToRow(socialNetwork1.getInterestMatrix().getRows().getElement(index1),
                        socialNetwork2.getInterestMatrix().getRows().getElement(index2),
                        socialNetwork1.getSubjects(),
                        socialNetwork2.getSubjects(),
                        allSubjects);
            }
            rowNode.setRowNumber(i);
            rowsOfInterestMatrix.addElement(rowNode);
        }
        MyMatrix interestMatrix = new MyMatrix(allPeoples.getSize(), allSubjects.getSize(), rowsOfInterestMatrix);


        MyLinkedList<RowNode> rowsOfFriendShipMatrix = new MyLinkedList<>();
        for (int i = 0; i < allPeoples.getSize(); i++) {
            String peopleName = allPeoples.getElement(i);
            RowNode rowNode = new RowNode();
            rowNode.setRowNumber(i);
            int index1 = findIndex(socialNetwork1.getPeoples(), peopleName);
            int index2 = findIndex(socialNetwork2.getPeoples(), peopleName);
            if (index1 < 0) {
                RowNode rowNode1 =new RowNode();
                rowNode1.setColumns(new MyLinkedList<>());
                rowNode=addToRow(socialNetwork2.getFriendShipMatrix().getTheRowNodeOfTheIndexRow(index2),rowNode1,
                        socialNetwork2.getPeoples(),socialNetwork1.getPeoples(),allPeoples);
//                rowNode = socialNetwork2.getFriendShipMatrix().getTheRowNodeOfTheIndexRow(index2);
            } else if (index2 < 0) {
                RowNode rowNode1 =new RowNode();
                rowNode1.setColumns(new MyLinkedList<>());
                rowNode=addToRow(socialNetwork1.getFriendShipMatrix().getTheRowNodeOfTheIndexRow(index1),rowNode1,
                        socialNetwork1.getPeoples(),socialNetwork1.getPeoples(),allPeoples);
//                rowNode = socialNetwork1.getFriendShipMatrix().getTheRowNodeOfTheIndexRow(index1);
            } else {


                rowNode = addToRow(socialNetwork1.getFriendShipMatrix().getTheRowNodeOfTheIndexRow(index1),
                        socialNetwork2.getFriendShipMatrix().getTheRowNodeOfTheIndexRow(index2),
                        socialNetwork1.getPeoples(),
                        socialNetwork2.getPeoples(),
                        allPeoples);
            }
            rowNode.setRowNumber(i);
            rowsOfFriendShipMatrix.addElement(rowNode);
        }
        MyMatrix friendShipMatrix = new MyMatrix(allPeoples.getSize(), allPeoples.getSize(), rowsOfFriendShipMatrix);

        SocialNetwork socialNetwork = new SocialNetwork(allPeoples, allSubjects, interestMatrix, friendShipMatrix);
        return socialNetwork;
    }


    public static SocialNetwork socialNetworkMerger(MyLinkedList<SocialNetwork> socialNetworks,
                                                    MyLinkedList<String> allPeoples,
                                                    MyLinkedList<String> allSubjects) {

        if (socialNetworks.getSize() == 1) {
            return socialNetworks.getElement(0);
        } else if (socialNetworks.getSize() == 2) {
            return mergeTwoSocialNetwork(socialNetworks.getElement(0),
                    socialNetworks.getElement(1), allPeoples, allSubjects);
        } else if (socialNetworks.getSize() == 3) {
            SocialNetwork socialNetwork = mergeTwoSocialNetwork(socialNetworks.getElement(0),
                    socialNetworks.getElement(1), allPeoples, allSubjects);
            return mergeTwoSocialNetwork(socialNetwork, socialNetworks.getElement(2),
                    allPeoples, allSubjects);
        }
        return null;
    }


    public static int findIndex(MyLinkedList<String> localColumns,
                                String columnName) {

        for (int j = 0; j < localColumns.getSize(); j++) {
            if (localColumns.getElement(j).equals(columnName)) {
                return j;
            }
        }
        return -1;
    }

    public static RowNode addToRow(RowNode rowNode1, RowNode rowNode2,
                                   MyLinkedList<String> columns1,
                                   MyLinkedList<String> columns2,
                                   MyLinkedList<String> columns) {


        MyLinkedList<ColumnNode> resultColumn = new MyLinkedList<>();

        for (int i = 0; i < columns.getSize(); i++) {
            ColumnNode columnNode = new ColumnNode(i, 0d);
            int index = findIndex(columns1, columns.getElement(i));
            if (index != -1) {
                columnNode.setValue(columnNode.getValue() + rowNode1.getTheColumnNodeOfTheIndexColumn(index).getValue());
            }

            int index2 = findIndex(columns2, columns.getElement(i));
            if (index2 != -1) {
                columnNode.setValue(columnNode.getValue() + rowNode2.getTheColumnNodeOfTheIndexColumn(index2).getValue());
            }

            if (columnNode.getValue() != 0) {
                resultColumn.addElement(columnNode);
            }
        }

        RowNode rowNode = new RowNode();
        rowNode.setColumns(resultColumn);
        return rowNode;
    }


}
