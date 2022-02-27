/*
 * Strings
 * This project enables the user to encrypt and decrypt text with the caesar cipher. The user can choose the number of rotation and has also the possibility to quit the program.
 * Author: Isabella Kainer
 * Last Change: 27/02/2022
 */

import java.util.Scanner;

public class Encryption {
    static Scanner scanner = new Scanner(System.in);

    //takes integer input from the user
    static int IntInput() {
        int number = 0;

        try {
            number = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            return number;
        }

        return number;
    }

    //takes String input from the user
    static String textInput() {
        return scanner.nextLine();
    }

    //prints the menu
    static void printMenu() {
        System.out.println("-".repeat(80));
        System.out.println("1 - Encrypt text");
        System.out.println("2 - Decrypt text");
        System.out.println("9 - Quit");
        System.out.println("-".repeat(80));
        System.out.print("> ");
    }

    //encrypts and decrypts the text that is passed to the method with the given rotation
    static void encryptAndDecrypt(int rotation, String text, String mode) {
        char character = 0;
        char newChar = 0;
        int position;
        int numberRotation = 0;

        //adapts rotation in decrypt mode in a way that you can also use the encryption algorithm for decryption (rotation number is "reversed")
        if (mode == "decrypt") {
            numberRotation = 10 - (rotation % 10); //e.g. encrypt rotation: 3 --> decrypt rotation: 7
            rotation = 26 - (rotation % 26); //e.g. encrypt rotation: 3 --> decrypt rotation: 23
        }

        //loops through the whole String
        for (int i = 0; i < text.length(); i++) {
            character = text.charAt(i); //reads the character at position i from the String

            //encryption and decryption for letters from A to Z or from a to z or from 0 to 9
            if (character >= 'A' && character <= 'Z') {
                position = character - 'A';
                newChar = (char) ('A' + (position + rotation) % 26);
            } else if (character >= 'a' && character <= 'z') {
                position = character - 'a';
                newChar = (char) ('a' + (position + rotation) % 26);

            } else if (character >= '0' && character <= '9') {
                position = character - '0';

                //in decrypt mode use numberRotation instead of rotation, because it has been calculated differently
                if (mode == "decrypt") {
                    newChar = (char) ('0' + (position + numberRotation) % 10);
                } else {
                    newChar = (char) ('0' + (position + rotation) % 10);
                }


            } else { //all other characters besides A-Z, a-z, 0-9 stay the same
                newChar = character;
            }
            System.out.print(newChar);
        }
        System.out.println();

    }


    public static void main(String[] args) {
        while (true) {
            int command = 0;

            //print the menu and let the user give input as long as the given input is not 1, 2 or 9
            while (command != 1 && command != 2 && command != 9) {
                printMenu();
                command = IntInput();
            }

            //when the user entered 1, then encrypt; 2 --> decrypt; 9 --> quit
            if (command == 1) {
                int rotation = 0;
                while (rotation <= 0) {
                    System.out.print("  Enter rotation: ");
                    rotation = IntInput();
                }
                System.out.print("  Enter text to encrypt: ");
                String text = textInput();
                System.out.print("  Encrypted text: ");
                encryptAndDecrypt(rotation, text, "encrypt");

            } else if (command == 2) {
                int rotation = 0;
                while (rotation <= 0) {
                    System.out.print("  Enter rotation: ");
                    rotation = IntInput();
                }
                System.out.print("  Enter text to decrypt: ");
                String text = textInput();
                System.out.print("  Decrypted text: ");
                encryptAndDecrypt(rotation, text, "decrypt");
            } else if (command == 9) {
                break;
            }
        }
        scanner.close();
    }
}
