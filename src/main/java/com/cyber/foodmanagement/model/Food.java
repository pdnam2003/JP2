package com.cyber.foodmanagement.model;



import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Food {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private Category category;
    private SimpleObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    public Food(int id, String name, String description, double price, int quantity, String imageUrl, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getImageUrl() { return imageUrl; }
    public Category getCategory() { return category; }

    public SimpleObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    public void setImage(Image image) {
        this.imageProperty.set(image);
    }

    public Image getImage() {
        return imageProperty.get();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImageProperty(Image imageProperty) {
        this.imageProperty.set(imageProperty);
    }
}
