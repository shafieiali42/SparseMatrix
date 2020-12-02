public class MyMatrix {

    private int numberOfRows;
    private int numberOfColumns;

    private MyLinkedList<RowNode> rows;


    public MyMatrix(int numberOfRows, int numberOfColumns, MyLinkedList<RowNode> rows) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.rows = rows;
    }


    public static MyMatrix calculateQuestionMatrix(MyMatrix myMatrix, MyLinkedList<Integer> indexes) {

        MyMatrix copy = myMatrix.copy();
        for (int i = 0; i < copy.getRows().getSize(); i++) {
            boolean isAxis = true;
            for (int k = 0; k < indexes.getSize(); k++) {
                if (copy.getTheRowNodeOfTheIndexRow(i).getTheColumnNodeOfTheIndexColumn(indexes.getElement(k)).getValue() == 0) {
                    isAxis = false;
                    break;
                }
            }
            if (!isAxis) {
                MyLinkedList<ColumnNode> columns = new MyLinkedList<>();
                copy.getRows().setElement(new RowNode(i, columns), i);
            }
            if (isAxis) {
                for (int j = 0; j < copy.getNumberOfColumns(); j++) {
                    if (!indexes.contains(j)) {
                        if (copy.getTheRowNodeOfTheIndexRow(i).existedIndexOfColumn(j)) {
                            copy.getTheRowNodeOfTheIndexRow(i).getTheColumnNodeOfTheIndexColumn(j).setValue(0);
                        }
                    }
                }
            }
        }
        return copy;
    }


    public static MyLinkedList<ColumnNode> multiplyMatrixToColumn(MyMatrix first, MyLinkedList<ColumnNode> second) {
        MyLinkedList<ColumnNode> result=new MyLinkedList<>();
        for (int i = 0; i < first.getRows().getSize(); i++) {
            ColumnNode columnNode=multiplyTwoList(first.getTheRowNodeOfTheIndexRow(i).getColumns(),second,i);
            columnNode.setColumnNumber(i);
            result.addElement(columnNode);
        }

        return result;
    }


    public static MyLinkedList<ColumnNode> convertMatrixToList(MyMatrix matrix,MyLinkedList<Integer> indexes) {
        MyLinkedList<ColumnNode> result = new MyLinkedList<>();
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            double sum = 0d;
            boolean isAxis =true;
            for (int j = 0; j < indexes.getSize(); j++) {
                sum+=matrix.getRows().getElement(i).getTheColumnNodeOfTheIndexColumn(indexes.getElement(j)).getValue();
                if (matrix.getRows().getElement(i).getTheColumnNodeOfTheIndexColumn(indexes.getElement(j)).getValue()==0){
                    isAxis=false;
                }
            }
            if (!isAxis){ //maybe memory limit
                sum=0;
            }
            result.addElement(new ColumnNode(i,sum));
        }
        return result;
    }

    static ColumnNode multiplyTwoList(MyLinkedList<ColumnNode> l1, MyLinkedList<ColumnNode> l2, int index) {
        Node<ColumnNode> v1 = l1.getNode(0), v2 = l2.getNode(0), result = new Node<>();

        result.setElement(new ColumnNode(index,0));

        while (v1 != null && v2 != null) {
            if (v1.getElement().getColumnNumber() == v2.getElement().getColumnNumber()) {
                result.getElement().setValue(result.getElement().getValue() +
                        (v1.getElement().getValue() * v2.getElement().getValue()));
                v1 = v1.getNext();
            } else if (v1.getElement().getColumnNumber() > v2.getElement().getColumnNumber()) {
                v2 = v2.getNext();
            } else if (v1.getElement().getColumnNumber() < v2.getElement().getColumnNumber()) {
                v1 = v1.getNext();
            }
        }
        result.getElement().setColumnNumber(index);
        if (result.getElement().getValue() > 0) {
            result.setIndex(index);
            return result.getElement();
        } else {
            return result.getElement();
        }
    }





//    public static double multiplyMatrixToRow(MyMatrix matrix,MyLinkedList<Double>list){
//        MyLinkedList<Double> result =new MyLinkedList<>();
//        for (int i = 0; i < matrix.getRows().getSize(); i++) {
//            double element =
//        }
//
//
//
//    }






    public static MyMatrix multiplyMatrix(MyMatrix first, MyMatrix second) {
        if (first.numberOfColumns != second.numberOfRows) {
            System.err.println("Cant multiply");
            return null;
        }
        MyLinkedList<RowNode> rows = new MyLinkedList<>();
        for (int i = 0; i < first.numberOfRows; i++) {
            RowNode rowNode = null;
//            if (first.existedRowIndex(i)) {
            for (int j = 0; j < second.getNumberOfRows(); j++) {
//                    if (second.existedRowIndex(j)) {
                if (first.getTheRowNodeOfTheIndexRow(i).existedIndexOfColumn(j)) {
                    if (rowNode == null) {
                        rowNode = RowNode.multiplyNumberToRow(
                                first.getTheRowNodeOfTheIndexRow(i).getTheColumnNodeOfTheIndexColumn(j).getValue(),
                                second.getTheRowNodeOfTheIndexRow(j), second.numberOfColumns);
                    } else {
                        rowNode = RowNode.addTwoRowNode(rowNode, RowNode.multiplyNumberToRow(
                                first.getTheRowNodeOfTheIndexRow(i).getTheColumnNodeOfTheIndexColumn(j).getValue(),
                                second.getTheRowNodeOfTheIndexRow(j), second.numberOfColumns), second.numberOfColumns);

                    }
                }
            }
//                }
//            }
            if (rowNode != null) {
                rowNode.setRowNumber(i);
                rows.addElement(rowNode);
            } else if (rowNode == null) {
                MyLinkedList<ColumnNode> columnNodeMyLinkedList = new MyLinkedList<>();
                rowNode = new RowNode(i, columnNodeMyLinkedList);
                rows.addElement(rowNode);
            }
        }

        MyMatrix result = new MyMatrix(first.numberOfRows, second.numberOfColumns, rows);
        return result;
    }


    public MyMatrix copy() {
        MyLinkedList<RowNode> rows = new MyLinkedList<>();
        for (int i = 0; i < this.getRows().getSize(); i++) {
            rows.addElement(this.getRows().getElement(i).copy());
        }
        MyMatrix copy = new MyMatrix(numberOfRows, numberOfColumns, rows);
        return copy;
    }


    public RowNode getTheRowNodeOfTheIndexRow(int index) {
        for (int i = 0; i < rows.getSize(); i++) {
            if (rows.getElement(i).getRowNumber() == index) {
                return rows.getElement(i);
            }
        }
        return null;//todo
    }


    public boolean existedRowIndex(int index) {
        for (int i = 0; i < rows.getSize(); i++) {
            if (rows.getElement(i).getRowNumber() == index) {
                if (rows.getElement(i).getColumns().getSize() != 0) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "{ " + rows + " }";
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public MyLinkedList<RowNode> getRows() {
        return rows;
    }

    public void setRows(MyLinkedList<RowNode> rows) {
        this.rows = rows;
    }
}
