public class RowNode {

    private int rowNumber;
    private MyLinkedList<ColumnNode> columns;


    public RowNode(int rowNumber, MyLinkedList<ColumnNode> columns) {
        this.rowNumber = rowNumber;
        this.columns = columns;
    }


    public RowNode(){

    }

    public ColumnNode getTheColumnNodeOfTheIndexColumn(int index){
        for (int i = 0; i < columns.getSize(); i++) {
            if (columns.getElement(i).getColumnNumber()==index){
                return columns.getElement(i);
            }
        }
        return new ColumnNode(index,0d);
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
