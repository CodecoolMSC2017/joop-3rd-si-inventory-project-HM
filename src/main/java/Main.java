import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PersistentStore myPersistent = new PersistentStore();
        StoreManager myStorage = new StoreManager();
        myStorage.addStorage(myPersistent);
        myStorage.addCDProduct("Heroin Diaries",5000,13);
        myStorage.addBookProduct("On the road", 3490, 378);

        //myPersistent.loadProducts();
        System.out.println(myStorage.listProducts());
        System.out.println(myStorage.getTotalProductPrice());
    }
}
