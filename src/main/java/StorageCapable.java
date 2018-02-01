import java.util.List;

public interface StorageCapable {

    public List<Product> getAllProduct();

    public void storageCDProduct(String name, int price, int tracks);

    public void storeBookProduct(String name, int price, int pages);
}
