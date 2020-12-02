
public class ColumnNode {

    private int columnNumber;
    private double value;


    public ColumnNode(int columnNumber, double value) {
        this.columnNumber = columnNumber;
        this.value = value;
    }


    @Override
    public String toString() {
        return "columnNumber: " + columnNumber + "  " + value + "";
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}