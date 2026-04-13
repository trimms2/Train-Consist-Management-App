import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String toString() {
        return name + " (Capacity: " + capacity + ")";
    }
}

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

        System.out.println("\n=== Train Formation (LinkedHashSet - Ordered & Unique) ===");

        Set<String> formation = new LinkedHashSet<>();

        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        formation.add("Sleeper");

        System.out.println("Final Train Formation:");
        System.out.println(formation);

        System.out.println("\n=== Bogie Capacity Mapping (HashMap) ===");

        Map<String, Integer> bogieCapacity = new HashMap<>();

        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 40);

        System.out.println("Bogie Capacity Details:");

        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " → Capacity: " + entry.getValue());
        }

        System.out.println("\n=== UC7: Sorted Passenger Bogies by Capacity ===");

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nBogies sorted by capacity (Ascending):");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        bogies.sort((b1, b2) -> b2.capacity - b1.capacity);

        System.out.println("\nBogies sorted by capacity (Descending):");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        System.out.println("\n=== UC8: Filter Passenger Bogies Using Streams ===");

        List<Bogie> filteredBogies = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        for (Bogie b : filteredBogies) {
            System.out.println(b);
        }

        System.out.println("\nOriginal Bogie List (Unchanged):");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        System.out.println("\n=== UC9: Group Bogies by Type ===");

        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        for (Map.Entry<String, List<Bogie>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        System.out.println("\n=== UC10: Total Seating Capacity ===");

        int totalCapacity = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seating Capacity: " + totalCapacity);

        Scanner sc = new Scanner(System.in);

        System.out.println("\n===================================");
        System.out.println("UC11 - Validate Train ID and Cargo Code");
        System.out.println("===================================");

        System.out.print("\nEnter Train ID (Format: TRN-1234): ");
        String trainId = sc.nextLine();

        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String cargoCode = sc.nextLine();

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        boolean isTrainValid = trainMatcher.matches();
        boolean isCargoValid = cargoMatcher.matches();

        System.out.println("\nValidation Results:");
        System.out.println("Train ID Valid: " + isTrainValid);
        System.out.println("Cargo Code Valid: " + isCargoValid);

        System.out.println("\nUC11 validation completed...");
    }
}