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
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.type = type;
        this.capacity = capacity;
    }

    public String toString() {
        return type + " (Capacity: " + capacity + ")";
    }
}

public class TrainApp {

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
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Rectangular", "Coal"));

        boolean safe = goods.stream().allMatch(g ->
                !g.type.equalsIgnoreCase("Cylindrical") ||
                        g.cargo.equalsIgnoreCase("Petroleum")
        );

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
            PassengerBogie p2 = new PassengerBogie("AC Chair", 60);
            System.out.println(p1);
            System.out.println(p2);
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }
    }
}