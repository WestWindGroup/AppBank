package ua.artemenko.bankapp.service;


import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;


public class InputScreen {

    private static Scanner scanner;

    public InputScreen(){
    }
    public InputScreen(Scanner scanner){
        this.scanner = scanner;
    }

    public long inputLong() {
        long result = scanner.nextLong();
        return result;
    }

    public int inputInt() {
        int result = scanner.nextInt();
        return result;
    }

    public BigDecimal inputBigDecimal() {
        BigDecimal result = scanner.nextBigDecimal();
        return result;
    }
    public BigDecimal inputString() {
        int countPoint = 0;
        String str = scanner.nextLine();
        char[] chArray = str.toCharArray();
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < chArray.length; i++) {
            if(Character.isDigit(chArray[i])){
                strBuild.append(chArray[i]);
            }else if ((chArray[i] == ',') || (chArray[i] == '.')){
                if(countPoint > 0){
                    throw new InputMismatchException();
                }else{
                    chArray[i] = '.';
                    strBuild.append(chArray[i]);
                    countPoint ++;
                }
            }else{
                throw new InputMismatchException();
            }
        }
        BigDecimal result = new BigDecimal(strBuild.toString());
        return result;
    }

    public void next(){
        scanner.nextLine();
    }

    private void initInputScreen() {
        scanner = new Scanner(System.in);
    }
    public static Scanner getScanner() {
        return scanner;
    }
    public static void setScanner(Scanner scanner) {
        InputScreen.scanner = scanner;
    }

}
