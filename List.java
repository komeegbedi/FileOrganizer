/*
    Author: Oghenekome Egbedi
    This class holds all the fileInfo in a list
 */

import java.io.File;
import java.util.ArrayList;
public class List {

    private ArrayList<FileInfo> allFiles;
    private int iterator;

    public List(){
        allFiles = new ArrayList<>();
        iterator = 0;
    }

    //this method adds fileInfo in a sorted order
    public void add(FileInfo newFile) {

        int addIndex = -1; // the index the newFile is meant to be in
        int n = allFiles.size();

        //we want to loop through the list and find the right position for the newFile
        for (int index = 0; index < n && addIndex == -1; index++) {

            if (allFiles.get(index).compareTo(newFile) == -1) //the newFile is meant to come before the current Index
                addIndex = index;
        }//for

        if(addIndex == -1){ // addIndex is -1 either when the list is meant or
                            // the new file is to be in the last index (all files in the list comes before the newFile)
            allFiles.add(newFile);
        }
        else{ // we have a location to add the newFile
            allFiles.add(addIndex , newFile);
        }//if-else

    }//add()

    //this method helps the caller iterate over the list
    // returns null if we have reached the end of the list
    public FileInfo iterate(){
        FileInfo retVal = null;

        if (iterator >= 0 && iterator < allFiles.size()){
            retVal = allFiles.get(iterator);
            iterator++;
        }

        return retVal;
    }

    //helpful for debugging
    public String toString() {
        return "List{" + "allFiles=" + allFiles + '}';
    }//toString
}
