package com.hw3.Server;

import com.hw3.CommonPackage.Citation;
import java.util.Comparator;

public class TypeSorter implements Comparator<Citation>{
    @Override
    public int compare(Citation c1, Citation c2){
        String tof1 = c1.getTypeOfOffense();
        String tof2 = c2.getTypeOfOffense();
        return tof1.compareTo(tof2);
    }
}
