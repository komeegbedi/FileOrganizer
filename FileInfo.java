/*
    Author: Oghenekome Egbedi
    This class represents one file which would hold the name of the file and the date it was created
 */
public class FileInfo {

    private String fileName;
    private FileDate lastDateModified;

    //the file name and the creation date must be passed in at the time of creation
    public FileInfo(String name , FileDate modifiedDate){
        fileName = name;
        lastDateModified = modifiedDate;
    }//FileInfo

    //this method compares two files
    public int compareTo(FileInfo other){
        return lastDateModified.compareTo(other.lastDateModified);
    }

    //getters
    public String getFileName() {
        return fileName;
    }

    public FileDate getModifiedDate(){
        return lastDateModified;
    }
    //helpful for debugging
    public String toString() {
        return "FileInfo{" + "fileName='" + fileName + '\'' + ", creation date=" + lastDateModified + '}';
    }//toString
}
