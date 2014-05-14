import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class TempDB {
    private final FileInput in;
    private final FileOutput out;
    private LinkedList<String> addresses = new LinkedList<>();
    private HashMap<String, HashMap<String, Integer>> database = new HashMap<>();
    private int ADDRESS_MAX_LENGTH;

    public TempDB(FileInput in, FileOutput out) {
        this.in = in;
        this.out = out;
    }

    private void setMaxAddress(Set<String> book) {
        ADDRESS_MAX_LENGTH = 0;
        for (String address : book) {
            int length = address.length();
            if (length > ADDRESS_MAX_LENGTH) {
                ADDRESS_MAX_LENGTH = length;
            }
        }
    }

    public void add(String address, String task, int value) {
        if (!database.containsKey(address)) {
            addresses.add(address);
            database.put(address, new HashMap<String, Integer>());
        }
        database.get(address).put(task, value);
    }

    public void remove(String address) {
        addresses.remove(address);
        database.remove(address);
    }

    public void showList() {
        setMaxAddress(database.keySet());

        for (String address : addresses) {
            int sum = 0;

            for (String task : database.get(address).keySet()) {
                sum += database.get(address).get(task);
            }
            System.out.printf("%-" + ADDRESS_MAX_LENGTH + "s %d\n", address, sum);
        }
    }

    public void loadData() {
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String[] data = line.split("\\s+");
            addresses.add(data[0]);
            database.put(data[0], new HashMap<String, Integer>());
            for (int task = 1; task < data.length; task += 2) {
                database.get(data[0]).put(data[task], Integer.parseInt(data[task + 1]));
            }
        }
    }

    public void saveData() {
        setMaxAddress(database.keySet());

        for (String address : addresses) {
            String tasks = "";

            String[] tasksArray = database.get(address).keySet().toArray(new String[0]);
            Arrays.sort(tasksArray);
            for (String task : tasksArray) {
                tasks += task + " " + database.get(address).get(task) + " ";
            }

            out.printf("%-" + ADDRESS_MAX_LENGTH + "s %s\n", address, tasks);
        }
    }
}
