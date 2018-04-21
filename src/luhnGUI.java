import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class luhnGUI {
    private JPanel luhnPanel;
    private JButton btnCardCheck;
    private JButton btnISBNCheck;
    private JTextArea txtOutput;
    private JTextField txtCard;
    private JTextField txtISBN;

    public luhnGUI() {
        btnCardCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardnum = txtCard.getText();
                txtOutput.setText(cardnum + "\n");
                cardnum = cardnum.replaceAll("-", "");

                //empty submit catcher.
                if(cardnum.length() == 16){
                    //initialise inputs, get the number.
                    int[] numArray = new int[16];
                    int product = 0;

                    //utilising try to catch invalid inputs
                    try{
                        //process number and calculate product.
                        for(int i = 0; i < cardnum.length(); i++){
                            numArray[i] = Integer.parseInt(String.valueOf(cardnum.charAt(i)));
                            if(i % 2 == 0){
                                numArray[i] = ((numArray[i] * 2) / 10) + ((numArray[i] * 2) % 10);
                            }
                            product = product + numArray[i];
                        }
                        //display product
                        txtOutput.append(product + "\n");

                        //determine validity.
                        if(product % 10 == 0){
                            txtOutput.append("Card Number Valid");
                        }else{
                            txtOutput.append("Card Number Invalid");
                        }
                    }catch (NumberFormatException ex){
                        txtOutput.setText("Invalid Input.");
                    }
                }else{
                    txtOutput.setText("No Input.");
                }
            }
        });

        btnISBNCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //initialise variables
                int[] numArray = new int[10];
                int product = 0;
                int count = 10;

                //get input, display initial input.
                String isbnNum = txtISBN.getText();
                txtOutput.setText(isbnNum + "\n");
                isbnNum = isbnNum.replaceAll("-", "");

                if(isbnNum.length() == 10){
                    try{
                        for(int i = 0; i < isbnNum.length(); i++){
                            String temp = String.valueOf(isbnNum.charAt(i)).toLowerCase();

                            if("x".equals(temp)){
                                numArray[i] = 10 * count;
                            }else{
                                numArray[i] = Integer.parseInt(temp) * count;
                            }
                            count--;
                            product = product + numArray[i];
                        }
                        txtOutput.append(product + "\n");
                        if(product % 11 == 0){
                            txtOutput.append("Valid");
                        }else {
                            txtOutput.append("Invalid.");
                        }
                    }catch(NumberFormatException ex){

                    }
                }else{
                    txtOutput.setText("Invalid Input.");
                }

            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("luhnGUI");
        frame.setContentPane(new luhnGUI().luhnPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
