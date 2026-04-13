import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

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

class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    public String toString() {
        return type + " → " + cargo;
    }
}

class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

class PassengerBogie {
    String type;
    int capacity;

    PassengerBogie(String type, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) throw new InvalidCapacityException("Capacity must be greater than zero");
        this.type = type;
        this.capacity = capacity;
    }
}

class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

public class TrainApp {

    static void assignCargo(GoodsBogie bogie, String cargo) {
        try {
            if (bogie.type.equalsIgnoreCase("Rectangular") && cargo.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("Unsafe cargo assignment");
            }
            bogie.cargo = cargo;
        } catch (CargoSafetyException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Assignment attempt completed");
        }
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        Map<String, List<Bogie>> grouped =
                bogies.stream().collect(Collectors.groupingBy(b -> b.name));

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        Scanner sc = new Scanner(System.in);
        String trainId = sc.nextLine();
        String cargoCode = sc.nextLine();

        boolean trainValid = Pattern.matches("TRN-\\d{4}", trainId);
        boolean cargoValid = Pattern.matches("PET-[A-Z]{2}", cargoCode);

        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", ""));
        goods.add(new GoodsBogie("Rectangular", ""));

        assignCargo(goods.get(0), "Petroleum");
        assignCargo(goods.get(1), "Petroleum");

        List<Bogie> largeList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeList.add(new Bogie("Type" + i, i % 100));
        }

        long startLoop = System.nanoTime();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : largeList) {
            if (b.capacity > 60) loopResult.add(b);
        }
        long endLoop = System.nanoTime();

        long startStream = System.nanoTime();
        List<Bogie> streamResult = largeList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long endStream = System.nanoTime();

        try {
            PassengerBogie p1 = new PassengerBogie("Sleeper", 72);
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }

        int[] capacities = {72, 56, 24, 70, 60};
        bubbleSort(capacities);

        System.out.println("Sorted Capacities:");
        for (int c : capacities) {
            System.out.print(c + " ");
        }

        String[] bogieNames = {"Sleeper","AC Chair","First Class","General","Luxury"};
        Arrays.sort(bogieNames);

        System.out.println("\nSorted Bogie Names:");
        System.out.println(Arrays.toString(bogieNames));
    }
}