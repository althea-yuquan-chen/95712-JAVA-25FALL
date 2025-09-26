package DataPackage;

import java.util.ArrayList;
import java.util.Arrays;

// class Data has several encapsulation problems that you need to fix
public class Data {

    // What's wrong with the following declarations?
    // These data are all public, which means they could be accessed and changed by anyone
    private int iValue = 10;
    private String sValue = "a string";
    private int[] iList = {12, 15, 20};
    private ArrayList<Integer> aList = new ArrayList<>();
    private ContainedClass containedClass = new ContainedClass();


    public Data() {
        for (int i=0; i<iList.length; i++) {
            aList.add(iList[i]);
        }
        containedClass.setStr("a contained string");
    }

    public Data(Data d) {
        this.iValue = d.iValue;
        this.sValue = d.sValue;
        this.iList = d.iList;
        //this.aList = d.aList; 
        this.containedClass = d.containedClass;

        // better to use deep copy
        this.aList = new ArrayList<>(d.aList);
        
    }

    @Override
    public String toString() {
        return "DataPackage.Data{" +
                "iValue=" + iValue +
                ", sValue='" + sValue + "\'" +
                ", iList=" + Arrays.toString(iList) +
                ", aList=" + aList +
                ", containedClass = " + containedClass.str +
                "}";
    }

    public void setsValue(String sValue) {
        this.sValue = sValue;
    }

    // Should you copy a reference to an array?
    public void setiList(int[] iList) {
        this.iList = new int[iList.length];
        // Copy the elements from the input array into the new internal array
        for (int i = 0; i < iList.length; i++) {
            this.iList[i] = iList[i];
        }
    }

    // Should you copy a reference to an collection object?
    // No, should use deep copy
    public void setaList(ArrayList<Integer> aList) {
        //this.aList = aList;
        this.aList = new ArrayList<>(aList);
    }

    // Should you return a reference to a collection object?
    public ArrayList<Integer> getaList() {
        //return aList;
        return new ArrayList<>(aList);
    }

    // Should you copy a reference to an object?
    public void setContainedClass(ContainedClass containedClass) {
        this.containedClass = containedClass;
    }

    public int getiValue() {
        return iValue;
    }

    public void setiValue(int iValue) {
        this.iValue = iValue;
    }

    public String getsValue() {
        return sValue;
    }

    public int[] getiList() {
        return iList;
    }

    public ContainedClass getContainedClass() {
        return containedClass;
    }
}
