import java.util.ArrayList;
import java.util.Scanner;

public class BankingAppNew{ 
    private static final Scanner scanner = new Scanner(System.in);

    final static String CLEAR = "\033[H\033[2J";
    final static String COLOR_BLUE_BOLD = "\033[34;1m";
    final static String COLOR_GREEN_BOLD = "\033[32;1m";
    final static String COLOR_RED_BOLD = "\033[31;1m";
    final static String RESET = "\033[0m";

    final static String ERROR_MESSAGE = String.format(" %s%s%s",COLOR_RED_BOLD,"%s",RESET);
    final static String SUCCESS_MESSEAGE = String.format("%s%s%s",COLOR_GREEN_BOLD,"%s",RESET);

    static ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();


    public static void main(String[] args) {

        
        
        do{
            System.out.print(CLEAR);
            headLine(0);
            int option = dashBoard();
            headLine(option);
            switch(option){
                case 1 : addAccount(); break;
                case 2 : moneyDeposit(); break;
                case 7 : System.out.print(CLEAR);System.exit(0);
                default : dashBoard();

            }
        }while(false);        
        
    }
    public static int dashBoard(){

        String[] display = {"Create New Account","Diposit Money","Withdraw Money","Transfer Money",
                            "Check Account Balance","Delete Account","Exit"};
        
        for (int i = 0; i < display.length; i++) {
            System.out.printf("\b %s[%d]%s %s %s\n",COLOR_RED_BOLD,(i+1),COLOR_BLUE_BOLD,display[i],RESET);
        }
        int option;
        System.out.print("Enter an Option to continue : ");
        option = scanner.nextInt();
        scanner.skip(System.lineSeparator());


        return option;
    }
    public static void headLine(int opt){
        String screen="";
        
        switch(opt){
        
            case 1: screen = "ADD ACCOUNT"; break;
            case 2: screen = "MONEY DIPOSIT"; break;
            case 3: screen = "MONEY WITHDRAW";break;
            case 4: screen = "MONEY TRASFER"; break;
            case 5: screen = "CHECK BALANCE"; break;
            case 6: screen = "DELETE ACCOUNT"; break; 
            default: screen = "Welcome to Smart Banking App";
        }  
        final String APP_TITLE = String.format("%s%s%s",
                                COLOR_BLUE_BOLD, screen, RESET);
        System.out.println(CLEAR);
        System.out.println(COLOR_GREEN_BOLD + "-".repeat(45)+RESET);
        System.out.println("\t" + APP_TITLE );
        System.out.println(COLOR_GREEN_BOLD + "-".repeat(45)+RESET);
        
    }
    public static void addAccount(){
        
        System.out.printf("%-25s: SDB-%05d \n","New Account ID",(details.size() + 1));
        String name = getName();
        double intialDepo = getDeposit("Intial Deposit");
        details.add(new ArrayList<String>());
        details.get(details.size()-1).add((details.size()+1)+"");
        details.get(details.size()-1).add(name);
        details.get(details.size()-1).add(intialDepo+"");

        System.out.println();
        System.out.printf("SDB-%05d:%s's Account has been created succesfully\n",
                            details.size(),details.get(details.size()-1).get(1));
        if(getStringInput("Do you want to an add new Account (Y/n)? :").toUpperCase()=="Y"){
            addAccount();
        } 
        dashBoard();        

    }
    public static void moneyDeposit(){

    }
    public static String getName(){
        boolean valid= true;
        String nameIn;
		
        validationA:
        do{        
            nameIn = getStringInput("Enter Account Holder Name"); 
            if(nameIn.isBlank()){
                System.out.printf(ERROR_MESSAGE,"Name cannot be empty..\n");
                valid = false;
                continue;
            }
            for(int i=0; i<nameIn.length(); i++){
                if(!(Character.isLetter(nameIn.charAt(i)) || Character.isSpaceChar(nameIn.charAt(i)))){
                    System.out.printf(ERROR_MESSAGE,"Name is Invalid it should be contained only letters & space\n");
                    valid = false;
                    continue validationA;
                }      
            }
            break;

        }while(!valid);
        return nameIn;
    }
    public static String getStringInput(String title){
        System.out.printf("%-25s: ",title);
        String input = scanner.nextLine().strip();
        return input;
    }
    public static double getDoubleInput(String title){
        System.out.printf("%-25s: ",title);
        double input = scanner.nextDouble();
        scanner.skip(System.lineSeparator());
        return input;
    } 
    public static double getDeposit(String title){
        boolean valid= true;
        Double depositIn;
        do {
            depositIn = getDoubleInput(title);
            if(depositIn<5000){
                System.out.printf(ERROR_MESSAGE,"Intial Deposit should be greater than Rs.5000.00\n");
                valid = false;
                continue;
            }
            break;
        } while (!valid);

        return depositIn;
    }

}


    

