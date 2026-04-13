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
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static boolean linearSearch(String[] arr, String key) {
        for (String id : arr) {
            if (id.equals(key)) return true;
        }
        return false;
    }

    static boolean binarySearch(String[] arr, String key) {
        Arrays.sort(arr);
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = key.compareTo(arr[mid]);

            if (cmp == 0) return true;
            else if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }

    static boolean safeSearch(String[] arr, String key) {
        if (arr.length == 0) {
            throw new IllegalStateException("No bogies available for search");
        }
        return linearSearch(arr, key);
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

        int[] capacities = {72, 56, 24, 70, 60};
        bubbleSort(capacities);

        String[] bogieNames = {"Sleeper","AC Chair","First Class","General","Luxury"};
        Arrays.sort(bogieNames);

        String[] bogieIds = {"BG101","BG205","BG309","BG412","BG550"};
        boolean result = safeSearch(bogieIds, "BG205");

        System.out.println("Search Result: " + result);
    }
}