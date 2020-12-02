public class OutPut {


    private String name;
    private double value;
    private int depth;


    public OutPut(String name, double value, int depth) {
        this.name = name;
        this.value = value;
        this.depth = depth;
    }


    public static boolean compare(OutPut outPut1, OutPut outPut2) {
        if (outPut1.depth < outPut2.depth) {
            return true;
        } else if (outPut1.depth > outPut2.depth) {
            return false;
        } else if (outPut1.value > outPut2.value) {
            return true;
        } else if (outPut1.value < outPut2.value) {
            return false;
        } else if (outPut1.name.compareTo(outPut2.name) < 0) {
            return true;
        } else if (outPut1.name.compareTo(outPut2.name) > 0) {
            return false;
        }
        return true;
    }


    public void print() {
        String plus = "";
        for (int i = 0; i < depth; i++) {
            plus = plus + "+";
        }
        System.out.print(name + " ");
        System.out.printf("%6f", value);
        System.out.println(" " + plus);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}