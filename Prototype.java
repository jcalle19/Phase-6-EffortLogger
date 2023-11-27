import java.util.Scanner;

//Asish Panda Group Th12//
public class Prototype {

    
    //The username must start with a letter.//
   // The username can be a minimum of 4 characters long.//
   // The username can only contain letters (both uppercase and lowercase) and numbers (alphanumeric characters)//
    
    //The password must be a minimum of 8 characters long.//
    // The password must contain at least one letter (uppercase or lowercase) and one digit.//
   // The password can also contain special characters such as !@#$%^&*()-_=+<>?.,;:/"'[]{}|.//


    public static String collectInput(String prompt, String pattern, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean validInput = false;

        do {
            System.out.print(prompt);
            input = scanner.nextLine();

            if (input.matches(pattern)) {
                validInput = true;
            } else {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return input;
    }
}
