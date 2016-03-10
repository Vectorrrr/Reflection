package model;

/**
 * Created by igladush on 07.03.16.
 */
public class Test {
    private Test2 test2;
    private int firstField;
    private String secondField;

    public Test() {
    }

    public Test2 getTest2() {
        return test2;
    }

    public void setTest2(Test2 test2) {
        this.test2 = test2;
    }

    public int getFirstField() {
        return firstField;
    }

    public void setFirstField(int firstField) {
        this.firstField = firstField;
    }

    public String getSecondField() {
        return secondField;
    }

    public void setSecondField(String secondField) {
        this.secondField = secondField;
    }

    public String toString() {
        return "field1 " + getFirstField() + " field2 " + getSecondField() + " test2 " + test2.getT();
    }
}
