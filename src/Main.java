import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            boolean reading = true;
            FileInput in = new FileInput();
            FileOutput out = new FileOutput();
            TempDB database = new TempDB(in, out);

            while (reading) {
                String[] line = input.readLine().split("\\s+");

                switch (line[0]) {
                    case "load":
                        database = new TempDB(in, out);
                        in.openFile(line[1]);
                        database.loadData();
                        in.closeFile();
                        break;
                    case "store":
                        out.openFile(line[1]);
                        database.saveData();
                        out.closeFile();
                        break;
                    case "add":
                        database.add(line[1], line[2], Integer.parseInt(line[3]));
                        break;
                    case "remove":
                        database.remove(line[1]);
                        break;
                    case "list":
                        database.showList();
                        break;
                    case "stop":
                        reading = false;
                        break;
                    default:
                        System.out.println("Wrong command, you can\n" +
                                "use one of this commands:\n" +
                                "\tload %file%\n" +
                                "\t\tclose current database without saving and load new from %file%\n" +
                                "\tstore %file%\n" +
                                "\t\tsave current database to file\n" +
                                "\tadd %address% %taskId% %score%\n" +
                                "\t\tadd new field to current database\n" +
                                "\tremove %address%\n" +
                                "\t\tremove all fields associated with this %address%\n" +
                                "\tlist\n" +
                                "\t\tshow current database\n" +
                                "\tstop\n" +
                                "\t\tstop the current program");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
