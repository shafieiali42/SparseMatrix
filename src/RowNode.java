public class RowNode {

    private int rowNumber;
    private MyLinkedList<ColumnNode> columns;


    public RowNode(int rowNumber, MyLinkedList<ColumnNode> columns) {
        this.rowNumber = rowNumber;
        this.columns = columns;
    }


    public RowNode() {

    }


//    public static RowNode multiplyNumberToRow(double a, RowNode rowNode, int numberOfGeneralColumns) { // maybe should make new Row and Column
//        RowNode rowNode1 = rowNode.copy();
//        for (int i = 0; i < numberOfGeneralColumns; i++) {
//            if (rowNode1.existedIndexOfColumn(i)) {
//                rowNode1.getTheColumnNodeOfTheIndexColumn(i).setValue(rowNode1.getTheColumnNodeOfTheIndexColumn(i).getValue() * a);
//            }
//        }
//        return rowNode1;
//    }


//    public double sumOfElements(){
//        double sum=0d;
//        for (int i = 0; i < getColumns().getSize(); i++) {
//            sum+=getColumns().getElement(i).getValue();
//        }
//        return sum;
//    }


//    public static RowNode addTwoRowNode(RowNode first, RowNode second, int numberOfGeneralColumns) {
//        RowNode result = new RowNode();
//        MyLinkedList<ColumnNode> columns = new MyLinkedList<>();
//        for (int i = 0; i < numberOfGeneralColumns; i++) {
//            ColumnNode columnNode = new ColumnNode(i, 0d);
//            if (first.existedIndexOfColumn(i)) {
//                columnNode.setValue(columnNode.getValue() + first.getTheColumnNodeOfTheIndexColumn(i).getValue());
//            }
//            if (second.existedIndexOfColumn(i)) {
//                columnNode.setValue(columnNode.getValue() + second.getTheColumnNodeOfTheIndexColumn(i).getValue());
//            }
//            if (columnNode.getValue() != 0) {
//                columns.addElement(columnNode);
//            }
//        }
//        result.setColumns(columns);
//        return result;
//    }


    public ColumnNode getTheColumnNodeOfTheIndexColumn(int index) {
        Node<ColumnNode> nodeNode = columns.getNode(0);
        for (int i = 0; i < columns.getSize(); i++) {
            if (nodeNode.getElement().getColumnNumber() == index) {
                return nodeNode.getElement();
            }
            nodeNode = nodeNode.getNext();
        }
        return new ColumnNode(index, 0d);
    }


    public boolean existedIndexOfColumn(int index) {
        Node<ColumnNode> nodeNode = columns.getNode(0);
        for (int i = 0; i < columns.getSize(); i++) {
            if (nodeNode.getElement().getColumnNumber() == index) {
                return true;
            }
            nodeNode = nodeNode.getNext();
        }
        return false;
    }


    public RowNode copy() {
        MyLinkedList<ColumnNode> columnsCopy = new MyLinkedList<>();

        Node<ColumnNode> nodeNode = columns.getNode(0);
        for (int i = 0; i < columns.getSize(); i++) {
            ColumnNode column = new ColumnNode(nodeNode.getElement().getColumnNumber(),
                    nodeNode.getElement().getValue());
            columnsCopy.addElement(column);
            nodeNode = nodeNode.getNext();
        }
        RowNode row = new RowNode(rowNumber, columnsCopy);
        return row;
    }


    @Override
    public String toString() {
        return "[  rowNumber: " + rowNumber + "   " + columns + " ]";
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public MyLinkedList<ColumnNode> getColumns() {
        return columns;
    }

    public void setColumns(MyLinkedList<ColumnNode> columns) {
        this.columns = columns;
    }
}
