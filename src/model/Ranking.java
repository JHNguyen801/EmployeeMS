package model;

import java.util.ArrayList;
import java.util.Collections;

/* This generic class has a generic method that takes ArrayList as a
 * parameter and sort the ArrayList in descending order
 */
public class Ranking<T extends Comparable<? super T>>{
    public <T extends Comparable<? super T>> void rank(ArrayList<T> arr) {
        Collections.sort(arr, Collections.reverseOrder());
    }// Method end
}
