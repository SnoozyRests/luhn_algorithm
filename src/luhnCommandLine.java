import java.util.Scanner;
public class luhnCommandLine {

    public static void main(String[] args) {
        //initialise variables.
        int mode;
        Scanner sc = new Scanner(System.in);

        //prompt what mode to enter.
        while(true) {
            System.out.println("Please enter either:\n'1' for card number check\n'2' for ISBN check");
             mode = sc.nextInt();
            if(mode == 1 || mode == 2){
                break;
            }
        }

        //loop will exit upon completion.
        while(true) {
            if (mode == 1) { //Card number check.
                //3379513561108795

                //initialise variables.
                int[] numArray = new int[16];
                int product = 0;

                //get card number.
                System.out.println("Please enter a sixteen digit card number.");
                String origin = sc.next();
                String cardnum = origin.replaceAll("-", "");

                //try statement catches invalid inputs.
                try {
                    //process card number.
                    for (int i = 0; i < cardnum.length(); i++) {
                        numArray[i] = Integer.parseInt(String.valueOf(cardnum.charAt(i)));
                        if (i % 2 == 0) {
                            numArray[i] = ((numArray[i] * 2) / 10) + ((numArray[i] * 2) % 10);
                        }
                        product = product + numArray[i];
                    }

                    //determine validity.
                    if (product % 10 == 0) {
                        System.out.println("Card number: " + origin + " is valid.");
                    } else {
                        System.out.println("Card Number: " + origin + " is invalid.");
                    }
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Input.");
                }
            } else if (mode == 2) { //ISBN Check
                //initialise variables.
                int[] numArray = new int[10];
                int product = 0;
                int count = 10;

                //get and format ISBN.
                System.out.println("Please enter the ISBN number:");
                String origin = sc.next();
                String isbn = origin.replaceAll("-", "");

                //try catches invalid inputs.
                try {
                    //process ISBN
                    for (int i = 0; i < isbn.length(); i++) {
                        String temp = String.valueOf(isbn.charAt(i)).toLowerCase();

                        if ("x".equals(temp)) {
                            numArray[i] = 10 * count;
                        } else {
                            numArray[i] = Integer.parseInt(temp) * count;
                        }
                        count--;
                        product = product + numArray[i];
                    }

                    //determine validity.
                    if (product % 11 == 0) {
                        System.out.println("ISBN: " + origin + " is valid.");
                    } else {
                        System.out.println("ISBN: " + origin + " is invalid.");
                    }
                    break;
                }catch (NumberFormatException ex){
                    System.out.println("Invalid Input.");
                }
            } //mode 2
        } //while
    } //psvm
}//class
