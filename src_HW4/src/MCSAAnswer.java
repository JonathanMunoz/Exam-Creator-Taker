import java.util.Scanner;

public class MCSAAnswer extends MCAnswer {

    public MCSAAnswer(String text, double creditifSelected){
        super(text,creditifSelected);
        this.label = "MCSAAnswer";
    }

    public MCSAAnswer(Scanner sc){
        super(sc);
        this.label = "MCMAAnswer";

    }

}
