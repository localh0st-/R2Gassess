import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

public class Product {
    private static ArrayList<Product> products = new ArrayList<>();
    private String pName;
    private String sku;

    public static void add(String pName, String sku) {
        Product p = new Product(pName, sku);
        products.add(p);
    }

    public Product(String pName, String sku) {
        this.pName = pName;
        this.sku = sku;
    }

    public static Product getProduct(String sku) {
        return products.stream()
                .filter(product -> sku.equals(product.getSku()))
                .findFirst()
                .orElse(null);
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public static ArrayList<String> listAll() {
        ArrayList<String> strings = new ArrayList<>();
        products.forEach(product -> {
            String productSku = product.getSku();
            String pname = product.getpName();
            strings.add(pname + " " + productSku);
        });
        return strings;
    }
}
