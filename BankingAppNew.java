import java.util.ArrayList;
import java.util.Scanner;

public class BankingAppNew{ 
    private static final Scanner scanner = new Scanner(System.in);

    final static String CLEAR = "\033[H\033[2J";
    final static String COLOR_BLUE_BOLD = "\033[34;1m";
    final static String COLOR_GREEN_BOLD = "\033[32;1m";
    final static String COLOR_RED_BOLD = "\033[31;1m";
    final static String RESET = "\033[0m";

    final static String ERROR_MESSAGE = String.format(" %s%s%s\n",COLOR_RED_BOLD,"%s",RESET);
    final static String SUCCESS_MESSEAGE = String.format("%s%s%s\n",COLOR_GREEN_BOLD,"%s",RESET);

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
                case 3 : moneyWithdraw(); break;
                case 4 : moneyTransfer(); break;
                case 5 : checkBalance(); break;
                case 6 : deleteAccount(); break;
                case 7 : System.out.print(CLEAR);System.exit(0);
                default : dashBoard();

            }
        }while(true);        
        
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
        double intialDepo = getDeposit("Intial Deposit           : Rs.",5000);
        details.add(new ArrayList<String>());
        details.get(details.size()-1).add((details.size())+"");
        details.get(details.size()-1).add(name);
        details.get(details.size()-1).add(intialDepo + "");

        System.out.println();
        System.out.printf("SDB-%05d:%s's Account has been created succesfully\n",
                            details.size(),details.get(details.size()-1).get(1));
        if(getStringInput("Do you want to add a new Account (Y/n)? :").toUpperCase().equals("Y")){
            headLine(1);
            addAccount();
        } 
    }
    public static void moneyDeposit(){
        int acctNum = getAccNum("Enter Account Number (SDB-xxxxx)");
        double deposit;
        if(acctNum>0 && details.size()>0 && details.get(acctNum-1).size()>0){  
            do {
                System.out.printf("current account balance is      : Rs. %,.2f\n",Float.valueOf(details.get(acctNum-1).get(2)));
                deposit = getDoubleInput("Deposit Amount                  : Rs. ");
                if(deposit<500){
                    System.out.printf(ERROR_MESSAGE,"Insufficient amount ");
                    if(getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                        continue;
                    }else break;
                }
                deposit = deposit + Double.valueOf(details.get(acctNum-1).get(2));
                details.get(acctNum-1).add(2,deposit+"");
                System.out.printf("new account balance is          : Rs. %,.2f\n",Float.valueOf(details.get(acctNum-1).get(2)));
                if(getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                    continue;
                }else break;
            } while (true); 
        }
    }
    public static void moneyWithdraw(){
        int acctNum = getAccNum("Enter Account Number (SDB-xxxxx)");
        double withdraw;
        if(acctNum>0 && details.size()>0 && details.get(acctNum-1).size()>0 ){  
            do {
                System.out.printf("current account balance is      : Rs. %,.2f\n",Float.valueOf(details.get(acctNum-1).get(2)));
                withdraw = getDoubleInput("Withdraw Amount                 : Rs. ");
                if(withdraw<100){
                    System.out.printf(ERROR_MESSAGE,"Insufficient amount to Withdraw ");
                    if(getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                        continue;
                    }else break;
                }else if((Double.valueOf(details.get(acctNum-1).get(2))-withdraw)<500){
                    System.out.printf(ERROR_MESSAGE,"Exceed the Account minimum balance ");
                    if(getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                        continue;
                    }else break;
                }
                withdraw = Double.valueOf(details.get(acctNum-1).get(2))-withdraw;
                details.get(acctNum-1).add(2,withdraw+"");
                System.out.printf("new account balance is          : Rs. %,.2f\n",Float.valueOf(details.get(acctNum-1).get(2)));
                if(getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                    continue;
                }else break;
            } while (true); 
        }
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
                    System.out.printf(ERROR_MESSAGE,"Name is Invalid, it should be contained only letters & space");
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
        System.out.print(title);
        double input = scanner.nextDouble();
        scanner.skip(System.lineSeparator());
        return input;
    } 
    public static double getDeposit(String title, int limit){
        boolean valid= true;
        Double depositIn;
        do {
            depositIn = getDoubleInput(title);
            if(depositIn<limit){
                System.out.printf(ERROR_MESSAGE,String.format("Intial Deposit should be greater than Rs. %,2d.00", limit));
                valid = false;
                continue;
            }
            break;
        } while (!valid);

        return depositIn;
    }
    public static int getAccNum(String title){
        boolean valid = true;
        boolean countCheck = false;
        String index="";
        int pass=0;
        do {
            valid = true;
            if(details.size()==0 ){
                System.out.println("No accounts in memory please add an account");
                valid = false;
                break;
            }else{
                String acctNum = getStringInput(title);
                if(acctNum.isEmpty()){
                    System.out.printf(ERROR_MESSAGE,"Account number can't be empty");
                    valid= false;
                    continue;
                }else if(!acctNum.startsWith("SDB-") || acctNum.length()!=9){
                    System.out.printf(ERROR_MESSAGE,"Account number format is invalid");
                    valid = false;
                }else if(valid){
                    index="";
                    for (int i =4 ; i < acctNum.length(); i++) {
                        if(!Character.isDigit(acctNum.charAt(i))){
                            System.out.printf(ERROR_MESSAGE,"Account number format is invalid");
                            valid= false; 
                            break;                   
                        }else if(countCheck || acctNum.charAt(i)!='0'){
                            index += acctNum.charAt(i);
                            countCheck= true;
                        }
                    }   
                }
            }
            
            pass = index.equals("") ? 0:Integer.valueOf(index);
            
            // if(valid == false && getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
            //     continue;
            // }
            
            

            if(valid && pass>0 && details.get(pass-1).size()== 0){
                System.out.printf(ERROR_MESSAGE,"Account is not found");
                valid = false;
            }
            if(valid == false && getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                continue;
            }else break;
              
        } while (!valid);
        if(details.size()==0 )getStringInput("Enter any key to shift to dashboard");
        return pass;
    }
    public static void moneyTransfer(){
        int acccNumFrom;
        int acccNumTo;
        acccNumFrom = getAccNum("Enter from account number (SDB-xxxxx)");
        if(acccNumFrom==0)return;
        acccNumTo = getAccNum("Enter To account number   (SDB-xxxxx)");    
        if(acccNumFrom>0 && acccNumTo>0){
            System.out.printf("Sender current account balance  : Rs. %.2f \n",Float.valueOf(details.get(acccNumFrom-1).get(2)));
            System.out.printf("Reciver current account balance : Rs. %.2f \n",Float.valueOf(details.get(acccNumTo-1).get(2)));
            double transfer;
            do {
                transfer = getDoubleInput("Transfer Amount                 : Rs. ");
                if(transfer<100){
                    System.out.printf(ERROR_MESSAGE,"Insufficient amount to transfer ");
                    if(getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                        continue;
                    }else break;
                }else if((Double.valueOf(details.get(acccNumFrom-1).get(2))-1.02*transfer)<500){
                    System.out.printf(ERROR_MESSAGE,"Exceed the Account minimum balance ");
                    if(getStringInput("nDo you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                        continue;
                    }else break;
                }
                double currentBalSend = Double.valueOf(details.get(acccNumFrom-1).get(2))-1.02*transfer;
                double currentBalReciv = Double.valueOf(details.get(acccNumTo-1).get(2))+ transfer;
                details.get(acccNumFrom-1).set(2, currentBalSend+"");
                System.out.printf("new Sender account balance is   : Rs. %,.2f\n",Float.valueOf(details.get(acccNumFrom-1).get(2)));
                details.get(acccNumTo-1).set(2,currentBalReciv+"");
                System.out.printf("new Reciever account balance is : Rs. %,.2f\n",Float.valueOf(details.get(acccNumTo-1).get(2)));
                if(getStringInput("Do you want to try again ? (Y/n) ").toUpperCase().equals("Y")){
                    continue;
                }else break;
            } while (true);
        }
    }
    public static void checkBalance(){
        do{
            int accNum = getAccNum("Enter account number (SDB-xxxxx)  ");
            if(details.size()>0 && accNum>0 && details.get(accNum-1).size()>0){
                System.out.printf("Account holder name is       : %s\n",details.get(accNum-1).get(1));
                System.out.printf("Current Account balance is   : Rs. %.2f\n",Float.valueOf(details.get(accNum-1).get(2)));
                System.out.printf("Available Account balance is : Rs. %.2f\n",Float.valueOf(details.get(accNum-1).get(2))-500);
                if(getStringInput("Do you want to continue ? (Y/n) ").toUpperCase().equals("Y")){
                    continue;
                }else break;
            }else break;
        }while(true);
    }
    public static void deleteAccount(){
        do {
            int accNum = getAccNum("Enter account number to delete (SDB-xxxxx)  ");
            if(details.size()>0 && accNum>0  && details.get(accNum-1).size()>0){
                String name=details.get(accNum-1).get(1);
                System.out.printf("Account holder name is       : %s\n",name);
                System.out.printf("Current Account balance is   : Rs. %.2f\n",Float.valueOf(details.get(accNum-1).get(2)));
                if(getStringInput("Are you surre to delete this account ? (Y/n) ").toUpperCase().equals("Y")){
                    System.out.println(details);
                    details.get(accNum-1).clear();
                    System.out.println(details);
                    System.out.printf("SDB-%05d : %s has been deleted succesfully \n",accNum,name);               
                }else break;
                if(getStringInput("Do you want to continue ? (Y/n) ").toUpperCase().equals("Y")){
                    continue;
                }else break;
            }else break;
        } while (true);
        
    }
    
}


    

