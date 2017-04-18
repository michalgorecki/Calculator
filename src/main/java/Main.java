import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * Created by emigore on 2017-03-15.
 */
public class Main {

    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();
        Scanner scanner = new Scanner(System.in);
        String currentWord = "";

        System.out.println("calculator");
        currentWord = scanner.nextLine();
        while(!currentWord.equals("x")){
            if(!currentWord.isEmpty()){
                try {
                    calculator.calculationStep(currentWord);
                    System.out.println("Top of stack: " + calculator.getStackPeek());
                }catch(EmptyStackException e){
                    System.out.println("empty.");
                }
            }
            System.out.println("Next word:");
            currentWord = scanner.nextLine();
        }

    }

}
