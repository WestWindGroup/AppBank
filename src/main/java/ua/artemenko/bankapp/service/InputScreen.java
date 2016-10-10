package ua.artemenko.bankapp.service;


import java.math.BigDecimal;
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
