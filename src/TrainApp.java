import java.util.ArrayList;
import java.util.List;

public class TrainApp {

    public static void main(String[] args) {


        System.out.println("=== Train Consist Management App ===");


        List<String> trainConsist = new ArrayList<>();


        System.out.println("Initial number of bogies: " + trainConsist.size());


        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");


        System.out.println("\nBogies after addition:");
        System.out.println(trainConsist);


        trainConsist.remove("AC Chair");


        System.out.println("\nBogies after removal:");
        System.out.println(trainConsist);


        if (trainConsist.contains("Sleeper")) {
            System.out.println("\nSleeper bogie exists in the train.");
        } else {
            System.out.println("\nSleeper bogie does NOT exist.");
        }


        System.out.println("\nFinal bogie list:");
        System.out.println(trainConsist);


    }
}