public class Constants
{
    
    public class ErrorCodes{
        public static final double NULL_MONEY_AMOUNT = Integer.MIN_VALUE;
        public static final String NULL_FILE = "ERROR: FILE NOT FOUND";
        public static final String CLEAR_FILE_ERROR = "FAILED TO CLEAR FILE";
        public static final String SAVE_FILE_ERROR = "FAILED TO SAVE FILE";
        public static final String NULL_USER = "ERROR: USER DOES NOT EXIST";
    }
    
    public class TXTRead {
        public static final char DELIMITER = ',';
        public static final String nameFilePath = "C:\\Users\\aaron/OneDrive\\Desktop\\nameFile.txt";
        public static final String moneyFilePath = "C:\\Users\\aaron/OneDrive\\Desktop\\moneyFile.txt";
    }
    
    public class CSVRead {
        public static final char DELIMITER = ',';
        public static final String FilePath ="CSV\\MoneyFile.csv";//"none";//"C:\\Users\\811589\\Downloads\\bruh .csv";
        //public static final String moneyFilePath = "C:\\Users\\aaron/OneDrive\\Desktop\\moneyFile.txt";
    }
}