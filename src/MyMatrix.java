public class MyMatrix {

    private int numberOfRows;
    private int numberOfColumns;

    private MyLinkedList<RowNode> rows;


    public MyMatrix(int numberOfRows, int numberOfColumns, MyLinkedList<RowNode> rows) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.rows = rows;
    }


    public static MyLinkedList<ColumnNode> multiplyMatrixToColumn(MyMatrix matrix, MyLinkedList<ColumnNode> l) {
        MyLinkedList<ColumnNode> result = new MyLinkedList<>();
        Node<RowNode> u = matrix.getRows().getHead();

        while (u != null) {
            Node<ColumnNode> v = multiplyTwoList(l, u.getElement().getColumns(), u.getIndex());
            if (v != null) {
                v.getElement().setColumnNumber(u.getElement().getRowNumber());
                result.addElement(v.getElement());
            }
            u = u.getNext();
        }
        return result;
    }


    public static MyLinkedList<ColumnNode> convertMatrixToList(MyMatrix matrix, MyLinkedList<Integer> indexes) {
        MyLinkedList<ColumnNode> result = new MyLinkedList<>();
        Node<RowNode> rowNodeNode = matrix.getRows().getNode(0);
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            double sum = 0d;
            boolean isAxis = true;
            Node<Integer> node = indexes.getNode(0);
            for (int j = 0; j < indexes.getSize(); j++) {
                sum += rowNodeNode.getElement().getTheColumnNodeOfTheIndexColumn(node.getElement()).getValue();
                if (rowNodeNode.getElement().getTheColumnNodeOfTheIndexColumn(node.getElement()).getValue() == 0) {
                    isAxis = false;
                }
                node = node.getNext();
            }
            rowNodeNode = rowNodeNode.getNext();
            if (!isAxis) { //maybe memory limit
                sum = 0;
            }
            result.addElement(new ColumnNode(i, sum));
        }
        return result;
    }

    static Node<ColumnNode> multiplyTwoList(MyLinkedList<ColumnNode> first, MyLinkedList<ColumnNode> second, int index) {
        Node<ColumnNode> columnNodeNode1 = first.getNode(0);
        Node<ColumnNode> columnNodeNode2 = second.getNode(0);
        Node<ColumnNode> resultNode = new Node<>();

        resultNode.setElement(new ColumnNode(index, 0));

        while (columnNodeNode1 != null && columnNodeNode2 != null) {
            if (columnNodeNode1.getElement().getColumnNumber() == columnNodeNode2.getElement().getColumnNumber()) {
                resultNode.getElement().setValue(resultNode.getElement().getValue() +
                        (columnNodeNode1.getElement().getValue() * columnNodeNode2.getElement().getValue()));
                columnNodeNode1 = columnNodeNode1.getNext();
            } else if (columnNodeNode1.getElement().getColumnNumber() > columnNodeNode2.getElement().getColumnNumber()) {
                columnNodeNode2 = columnNodeNode2.getNext();
            } else if (columnNodeNode1.getElement().getColumnNumber() < columnNodeNode2.getElement().getColumnNumber()) {
                columnNodeNode1 = columnNodeNode1.getNext();
            }
        }
        resultNode.getElement().setColumnNumber(index);
        if (resultNode.getElement().getValue() > 0) {
            resultNode.setIndex(index);
        }
        return resultNode;
    }

    public RowNode getTheRowNodeOfTheIndexRow(int index) {
        Node<RowNode> node = rows.getNode(0);
        for (int i = 0; i < rows.getSize(); i++) {
            if (node.getElement().getRowNumber() == index) {
                return node.getElement();
            }
            node = node.getNext();
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