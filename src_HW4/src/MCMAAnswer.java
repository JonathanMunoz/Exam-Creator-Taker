import java.util.Scanner;

public class MCMAAnswer extends MCAnswer{
private double baseCase;

public MCMAAnswer(String text, double creditifSelected){
        super(text,creditifSelected);
        this.label = "MCMAAnswer";
        }

public MCMAAnswer(Scanner sc){
    super(sc);
    this.label = "MCMAAnswer";

    }
}
