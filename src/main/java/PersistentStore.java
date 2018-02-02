
import java.util.List;


public class PersistentStore extends Store {

    @Override
    public void storeProduct(Product product) {
        listOfProducts.add(product);
    }

}

