import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Warehouse {
    private static ArrayList<Warehouse> warehouses = new ArrayList<>();
    private String warehouseNum = "";
    private int stockLimit = -1;
    private HashMap<String, Integer> currentStock = new HashMap<>();

    public Warehouse(String warehouseNum, int stockLimit) {
        this.warehouseNum = warehouseNum;
        this.stockLimit = stockLimit;
    }

    public Warehouse(String warehouseNum) {
        this.warehouseNum = warehouseNum;
    }

    public static void add(String warehouseNum, int stockLimit) {
        Warehouse w = new Warehouse(warehouseNum, stockLimit);
    }

    public static void add(String warehouseNum) {
        Warehouse w = new Warehouse(warehouseNum);
        warehouses.add(w);
    }

    public static ArrayList<String> listAll() {
        return warehouses.stream()
                .map(Warehouse::getWarehouseNum)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Warehouse getWarehouse(String warehouseNum) {
        return warehouses.stream()
                .filter(warehouse -> warehouseNum.equals(warehouse.getWarehouseNum()))
                .findFirst()
                .orElse(null);
    }

    public static void stock(String sku, String warehouseNum, int qty) {
        getWarehouse(warehouseNum).stock(sku, qty);
    }

    public static void unStock(String sku, String warehouseNum, int qty) {
        getWarehouse(warehouseNum).unStock(sku, qty);
    }

    public static void list(String warehouseNum) {
        Warehouse w = Warehouse.getWarehouse(warehouseNum);
        w.currentStock.forEach((sku,qty) -> {
            String pName = Product.getProduct(sku).getpName();
            System.out.println(pName + " " + sku + " " + qty);
        });
    }

    private void unStock(String sku, int qty) {
        if (currentStock.get(sku) > qty) {
            currentStock.put(sku, currentStock.get(sku) - qty);
        } else {
            currentStock.remove(sku);
        }
    }

    private void stock(String sku, int qty) {
        if (currentStock.containsKey(sku)) {
            if (stockLimit != -1) {
                currentStock.put(sku, Math.min(currentStock.get(sku) + qty, stockLimit));
            } else {
                currentStock.put(sku, currentStock.get(sku) + qty);
            }
        } else if(stockLimit != -1){
            currentStock.put(sku, Math.min(stockLimit, qty));
        } else {
            currentStock.put(sku,qty);
        }
    }

    public String getWarehouseNum() {
        return warehouseNum;
    }

    public void setWarehouseNum(String warehouseNum) {
        this.warehouseNum = warehouseNum;
    }
}
