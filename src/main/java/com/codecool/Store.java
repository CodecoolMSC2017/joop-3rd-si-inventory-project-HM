package com.codecool;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.codecool.BookProduct;
import com.codecool.CDProduct;
import com.codecool.Product;
import com.codecool.StorageCapable;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


import java.util.ArrayList;
import java.util.List;

public abstract class Store implements StorageCapable {
    static File inputFile = new File("result.xml");
    static String filename = "Products.xml";
    List<Product> productList = new ArrayList<>();

    public List<Product> getProducts(){
        return productList;
    }

    @Override
    public List<Product> getAllProduct() {
        return productList;

    }

    @Override
    public void storeCDProduct(String name, int price, int size) {
        Product CD = new CDProduct(name, price, size);
        store(CD);
    }

    @Override
    public void storeBookProduct(String name, int price, int size) {
        Product book = new BookProduct(name, price, size);
        store(book);
    }

    protected abstract void storeProduct(Product product);

    protected Product createProduct(String type, String name, int price, int size) {
        if (type == "CD") {
            CDProduct CD = new CDProduct(name, price, size);
            return CD;

        } else {
            BookProduct Book = new BookProduct(name, price, size);
            return Book;

        }

    }

    private void saveToXml() {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Products.xml"));

            Element rootElement = doc.createElement("com.codecool.Store");
            doc.appendChild(rootElement);



            for (int i = 0; i < getAllProduct().size() ; i++) {
                if (getAllProduct().get(i) instanceof CDProduct) {
                    Element product1 = doc.createElement("com.codecool.Product");
                    rootElement.appendChild(product1);
                    Attr attr = doc.createAttribute("type");
                    attr.setValue("cd");
                    product1.setAttributeNode(attr);

                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(getAllProduct().get(i).getName()));
                    product1.appendChild(name);

                    Element price = doc.createElement("price");
                    price.appendChild(doc.createTextNode(Integer.toString(getAllProduct().get(i).getPrice())));
                    product1.appendChild(price);

                    Element numOfTracks = doc.createElement("tracks");
                    numOfTracks.appendChild(doc.createTextNode(Integer.toString(((CDProduct) getAllProduct().get(i)).getNumOfTracks())));
                    product1.appendChild(numOfTracks);

                    transformer.transform(source, result);

                } else if (getAllProduct().get(i) instanceof BookProduct) {
                    Element product1 = doc.createElement("com.codecool.Product");
                    rootElement.appendChild(product1);
                    Attr attr = doc.createAttribute("type");
                    attr.setValue("book");
                    product1.setAttributeNode(attr);

                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(getAllProduct().get(i).getName()));
                    product1.appendChild(name);

                    Element price = doc.createElement("price");
                    price.appendChild(doc.createTextNode(Integer.toString(getAllProduct().get(i).getPrice())));
                    product1.appendChild(price);

                    Element numOfTracks = doc.createElement("pages");
                    numOfTracks.appendChild(doc.createTextNode(Integer.toString(((BookProduct) getAllProduct().get(i)).getNumOfPages())));
                    product1.appendChild(numOfTracks);

                    transformer.transform(source, result);
                }
            }
        } catch (ParserConfigurationException pcex) {
            pcex.printStackTrace();
        } catch (TransformerException tfex) {
            tfex.printStackTrace();
        }
    }



    public List<Product> loadProducts() {
        try {
            File fXmlFile = new File("Products.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("com.codecool.Product");
            //System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("type").equals("cd")) {
                        Product product = new CDProduct(eElement.getElementsByTagName("name").item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("tracks").item(0).getTextContent()));
                        productList.add(product);
                    } else if (eElement.getAttribute("type").equals("book")) {
                        Product product = new BookProduct(eElement.getElementsByTagName("name").item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("pages").item(0).getTextContent()));
                        productList.add(product);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }




    public void store(Product product) {
        storeProduct(product);
        saveToXml();

    }
}
