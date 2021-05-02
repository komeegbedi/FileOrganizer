/*
    Author: Oghenekome Egbedi
    This class holds a date (the year , the month and a day)
    For the purpose of this program this class will hold the last date modified for each file
 */
public class FileDate {

    private int year;
    private int month;
    private int day;

    //the caller has to pass in either just the year or both the year and the month or all three paramters
    public FileDate(int y){
        year = y;
        month = day = 0;
    }
    public FileDate(int y , int m){
        year = y;
        month = m;
        day = 0;
    }

    public FileDate(int y , int m , int d){
        year = y;
        month = m;
        day = d;
    }//FileDate()

    //getters
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // This method compares two dates
    // The method returns 0 if both dates are the same
    // the method returns 1 for x.compare(y) if x comes before y
    // the method returns -1 for x.compare(y) if y compares before x
    public int compareTo(FileDate other){
        int retVal = 0; // value to return.... assume the dates are the same

        /*
          We start by checking if the year are the same,
          if we have the same year we check the month,
          if we have the same month, we then compare the day and if the day is the same then we have the same dates
         */
        if(year == other.year){

            if(month == other.month){
                // at this point all we have to do is check which day comes first
                if(day < other.day){
                    retVal = 1;
                }
                else  if(day > other.day){
                    retVal = -1;
                }//if-else
            }
            else {
                retVal = ((month < other.month) ? 1 : -1); // if month > other.month{ retVal = 1} else{ retVal = -1}
            }//if-else
        }
        else {
            retVal = ((year < other.year) ? 1 : -1); // if year > other.year{ retVal = 1} else{ retVal = -1}
        }//if-else

        return  retVal;
    }//compareTo()

    //toString method .. helpful for debugging
    public String toString() {
        return "Date{" + "year=" + year + ", month=" + month + ", day=" + day + '}';
    }
}