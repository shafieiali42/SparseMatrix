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
                if (copy.getRows().getElement(i).getColumns().getElement(k).getValue() == 0) {
                    isAxis = false;
                }
            }
            if (!isAxis) {
                MyLinkedList<ColumnNode> columns = new MyLinkedList<>();
                copy.getRows().setElement(new RowNode(i, columns), i);
            }
            if (isAxis) {
                for (int j = 0; j < copy.getRows().getElement(i).getColumns().getSize(); j++) {
                    if (!indexes.contains(j)) {
                        try {
                            copy.getRows().getElement(i).getColumns().delete(j);
                        } catch (OutOfBoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return copy;
    }


    public static MyMatrix multiplyMatrix(MyMatrix first, MyMatrix second) {
        MyLinkedList<RowNode> rows = new MyLinkedList<>();
        for (int i = 0; i < first.getRows().getSize(); i++) {




        }

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
