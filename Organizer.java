/*
    Author: Oghenekome Egbedi
    This class takes in the path in which all the files are contained , gets all the files from the path and organize them into folders
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Organizer{

    private File folder;
    private String sortMethod;
    private List allFiles;

    // the path to the files must be passed in when an object is created
    public Organizer(File pathToFolder, String sortPattern) throws Exception {

        folder = pathToFolder;
        if(!folder.isDirectory())  //ensure a directory was passed in
            throw new Exception("A Directory path must be passed in");

        sortMethod = sortPattern;
        allFiles = new List();
    }

    //this method gets all the files in the path passed in and adds it to the list
    private void getFiles(){

        File[] listOfFiles = folder.listFiles(); // get all the files in the path

        if(listOfFiles != null){

            long lastModified;
            Date date;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); // format of the date wanted
            String[] strDate;

            for (File file : listOfFiles) {  // loop through all the files in the directory

                if(file.isFile()) { // for now, to be updated later we are going to just look at only files in the directory
                                   // and ignore other directories in the folder

                    //get the last date modified
                    // my default lastModified will return a long of the date
                    // we need to format the date into what our FileDate class will accept
                    lastModified = file.lastModified();
                    date = new Date(lastModified);
                    strDate = formatter.format(date).split("/");

                    //adds it to the list
                    //check for the method we are using to sort the files
                    if(sortMethod.equalsIgnoreCase("year")){ // if we are sorting by year  we pass just the year
                        allFiles.add(new FileInfo(file.getName(),
                                new FileDate(Integer.parseInt(strDate[0]))));
                    }
                    else if(sortMethod.equalsIgnoreCase("month")){ // if we are sorting by month pass in the year and month
                        allFiles.add(new FileInfo(file.getName(),
                                new FileDate(Integer.parseInt(strDate[0]), Integer.parseInt(strDate[1]))));
                    }
                    else { // else we are sorting by day, we need to pass in the three
                        allFiles.add(new FileInfo(file.getName(),
                                new FileDate(Integer.parseInt(strDate[0]), Integer.parseInt(strDate[1]), Integer.parseInt(strDate[2]))));
                    }
                }//if
            }//for
        }//if

    }//getFiles()

    //this method arranges all the files that have the same date in array and move them in a folder
    public void arrangeFiles() throws IOException {
        getFiles();

        ArrayList <FileInfo> folderGroup = new ArrayList<>(); // holds all the files with the same date
        FileInfo currFile = allFiles.iterate(); //starts the iteration in the list
        FileInfo nextFile;

        //loop till the end of the file
        while (currFile != null){

            folderGroup.add(currFile);
            nextFile = allFiles.iterate();

            //if the next file is not the same date with the current file or we have reached the last file
            // then it's time to create a folder for that particular folder group and move them there
            if(nextFile == null || currFile.compareTo(nextFile) != 0){

                int n = folderGroup.size();

                // we need to create the directory that we want to move the files to
                String movePath = folder.getPath() + "\\" + currFile.getModifiedDate().getYear();

                //add extra fields based on the sorting method
                if(sortMethod.equalsIgnoreCase("month")){
                    movePath += "-" +  currFile.getModifiedDate().getMonth();
                }
                else if(sortMethod.equalsIgnoreCase("day")){
                    movePath += "-" +  currFile.getModifiedDate().getMonth() + "-" + currFile.getModifiedDate().getDay();
                }//else if

                File newDirectory = new File(movePath);
                boolean created = newDirectory.mkdirs();

                //if the directory was not created, print error message
                if(!created)
                    System.out.println("Error: "+ movePath +" directory was not created");

                //move all the files from the folderGroup
                for (int index = 0; index < n && created; index++){

                    Files.move(Paths.get(folder.getPath() + "\\"+folderGroup.get(index).getFileName()) ,
                            Paths.get(movePath+ "\\"+folderGroup.get(index).getFileName()) , StandardCopyOption.REPLACE_EXISTING);
                }//for
                folderGroup.clear();//once done we need to clear the array for the next set
            }//if

            currFile = nextFile;
        }//while

    }//arrangeFiles
}
