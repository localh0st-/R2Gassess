import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String str = in.nextLine();
            Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
            ArrayList<String> input = new ArrayList<>();
            while (m.find())
                input.add(m.group(1).replace("\"",""));
            String command = "";
            String[] param = new String[3];
            int j = 0;
            for (int i = 0; i < input.size(); i++) {
                if ((input.get(i).equals("ADD") || input.get(i).equals(("LIST"))) && i == 0 && i + 1 < input.size()) {
                    command = input.get(i) + " " + input.get(i + 1);
                    i++;
                } else if (i == 0) command = input.get(i);
                else param[j++] = input.get(i).replace("\"","");
            }
            execute(command, param);
            System.out.println();
        }
    }

    private static void execute(String command, String[] param) {
        switch(command){
            case "ADD WAREHOUSE":
                if(param[1] != null) Warehouse.add(param[0], Integer.parseInt(param[1]));
                else Warehouse.add(param[0]);
                break;
            case "ADD PRODUCT":
                Product.add(param[0],param[1]);
                break;
            case "STOCK":
                Warehouse.stock(param[0], param[1], Integer.parseInt(param[2]));
                break;
            case "UNSTOCK":
                Warehouse.unStock(param[0],param[1],Integer.parseInt(param[2]));
                break;
            case "LIST PRODUCTS":
                System.out.println(Product.listAll());
                break;
            case "LIST WAREHOUSES":
                System.out.println(Warehouse.listAll());
                break;
            case "LIST WAREHOUSE":
                Warehouse.list(param[0]);
                break;
            default:
                System.out.println("Invalid command");
        }
    }
}