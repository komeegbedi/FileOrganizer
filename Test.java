import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Test {

        public static void main(String[] args) {

           // File file = new File("C:\\Users\\oghen\\Downloads\\COMP-2080-A01-A02 - Analysis of Algorithms - 4232021 - 135 PM\\algAnalysisB4.mp4");
//            long lastModified = file.lastModified();
//            Date date = new Date(lastModified);
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            String strDate= formatter.format(date);
//            System.out.println(strDate);


            File folder = new File("C:\\Users\\oghen\\Downloads\\COMP-2080-A01-A02 - Analysis of Algorithms - 4232021 - 135 PM");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.print(file.getName() + " ");
                    long lastModified = file.lastModified();
                    Date date = new Date(lastModified);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate= formatter.format(date);
                    System.out.println(strDate);
                }
            }
        }
}
