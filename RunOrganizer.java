/*
    Author: Oghenekome Egbedi
    This class runs the Organizer
 */
import java.io.File;
import java.util.Scanner;

public class RunOrganizer {

    public static void main(String[] args) {

        //asks user for file path
        System.out.println("Enter the path to the directory you will like to sort:");
        Scanner userInput = new Scanner(System.in);
        String filePath = userInput.nextLine();

        //ask the use how they want to sort their files. Either by day, or by month or year
        System.out.println("How will you like to sort your files ? (by day, month or year)");
        userInput = new Scanner(System.in);
        String sortPattern;
        boolean valid;

        //ensure the sort patter is valid , else ask the user to enter the input again
        do{
            sortPattern = userInput.nextLine();
            valid = sortPattern.equalsIgnoreCase("day")
                    || sortPattern.equalsIgnoreCase("month")
                    ||sortPattern.equalsIgnoreCase("year");

            if(!valid)
                System.out.println("Try again. Please enter a valid input");

        }while (!valid);

        //run program
        System.out.println("Starting program...");
        try {
            Organizer x = new Organizer(new File(filePath) , sortPattern);
            x.arrangeFiles();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }//try-catch

        System.out.println("End of Program...");
    }//main()
}
