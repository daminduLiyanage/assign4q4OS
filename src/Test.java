import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Damindu on 11/28/2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ReadFile file = new ReadFile();
        Inputs input = new Inputs(file);


        System.out.println("\nStatus of the system: ");
        SafetyAlgorithm banker = new SafetyAlgorithm(input);
        banker.main();


        DetectionAlgorithm banker2;
        int[][] request = input.getRequest();
        for (int i=0; i<request.length; i++) {
            System.out.println("\nStatus of the system for request "+ (i+1) + ": ");
            banker2 = new DetectionAlgorithm(input, request[i][0]);
            banker2.main(i);
        }
    }
}
