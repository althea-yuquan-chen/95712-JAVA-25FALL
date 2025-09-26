package DataPackage;

import java.util.ArrayList;

public class DataSnooper {
    Data d = new Data();

    public void snoop() {
        System.out.println("Beginning DataPackage.Data");
        // 
        System.out.println(d.toString());

        // Problem 1: is direct read access okay?
        // Because the values in Data are public, so this access would directly return the value from the stack.
        // This could be dangerous.
        // Need to add access control to the data's member data, like, private, and use getters and setters to visit and change them
        System.out.println("Direct access");
        System.out.println("iValue = " + d.getiValue());
        System.out.println("sValue = " + d.getsValue());
        System.out.println("iList = ");
        for (int i=0; i < d.getiList().length; i++) {
            System.out.println(d.getiList()[i]);
        }
        System.out.println("contained String = " + d.getContainedClass().str);

        // More Problem 1: is direct write access okay?
        // This would directly change the value in the memory, but not the copy of it, break the encapsulation of class Data
        System.out.println();
        System.out.println("Change the simple data");
        d.setiValue(-1);
        d.setsValue("Dog");

        int[] newiList = d.getiList();
        newiList[0] = 0;
        newiList[1] = 0;
        newiList[2] = 0;
        d.setiList(newiList);

        ContainedClass newContainedClass = new ContainedClass();
        newContainedClass.setStr("Elephant");
        d.setContainedClass(newContainedClass);

        // What's in d now?
        System.out.println(d.toString());

        // Problem 2: are arrays different than regular data?
        System.out.println();
        System.out.println("Change the array");
        int[] mylist = {-1, -2, -3, -4, -5};
        d.setiList(mylist);
        // What's in d's int array named ilist?
        System.out.println(d.toString());
        // Change the local array mylist
        mylist[1] = 1000;
        // Now what's in d's int array named ilist?
        // Remains the same, because we only change the local mylist, not the iList in Data class.
        System.out.println(d.toString());

        // Problem 3: is a Collection different than regular data?
        System.out.println();
        System.out.println("Change the ArrayList");
        // What's in d's ArrayList?
        System.out.println(d.toString());
        
        // directly get the reference of the arraylist, which could break the encapsulation
        ArrayList<Integer> yourlist = d.getaList();
        yourlist.add(1000);
        // What's in d's ArrayList now?
        System.out.println(d.toString());

        // instead, we could use a deep copy
        ArrayList<Integer> copyArrayList = new ArrayList<>(d.getaList());
        copyArrayList.add(1000);
        System.out.println(d.toString());

        // Problem 4: is a contained class different than regular data?
        System.out.println();
        System.out.println("Change the contained class");
        // What's in d's ContainedClass?
        System.out.println(d.toString());
        DataPackage.ContainedClass x = new DataPackage.ContainedClass();
        x.setStr("Gopher");
        d.setContainedClass(x);
        // What's in d's ContainedClass?
        System.out.println(d.toString());
    }
}
