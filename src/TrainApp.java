import java.util.*;

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
        }

        System.out.println("\nFinal bogie list:");
        System.out.println(trainConsist);



        System.out.println("\n=== Bogie ID Tracking (No Duplicates Allowed) ===");


        Set<String> bogieIds = new HashSet<>();


        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");
        bogieIds.add("BG102");


        System.out.println("Unique Bogie IDs:");
        System.out.println(bogieIds);

        System.out.println("\n=== Ordered Train Consist (LinkedList) ===");


        LinkedList<String> trainOrder = new LinkedList<>();


        trainOrder.add("Engine");
        trainOrder.add("Sleeper");
        trainOrder.add("AC");
        trainOrder.add("Cargo");
        trainOrder.add("Guard");

        System.out.println("Initial Train Order:");
        System.out.println(trainOrder);


        trainOrder.add(2, "Pantry");

        System.out.println("\nAfter adding Pantry Car at position 2:");
        System.out.println(trainOrder);


        trainOrder.removeFirst();
        trainOrder.removeLast();

        System.out.println("\nAfter removing first and last bogies:");
        System.out.println(trainOrder);
    }
}