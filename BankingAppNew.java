import java.util.Scanner;

public class BankingAppNew {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0m";

        final String ERROR_MESSAGE = String.format(" %s%s%s",COLOR_RED_BOLD,"%s",RESET);
        final String SUCCESS_MESSEAGE = String.format("%s%s%s",COLOR_GREEN_BOLD,"%s",RESET);

        int x = DASHBOARD();



        
        
    }
    public static int DASHBOARD(){

        


        return 2;
    }
}
