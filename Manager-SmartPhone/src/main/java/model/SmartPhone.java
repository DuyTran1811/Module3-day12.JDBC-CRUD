package model;

public class SmartPhone {
    private int id;
    private String brand;
    private String name;
    private float price;
    private String year;
    private String sizescreen;

    public SmartPhone() {
    }

    public SmartPhone(String brand, String name, float price, String year, String sizescreen) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.year = year;
        this.sizescreen = sizescreen;
    }

    public SmartPhone(int id, String brand, String name, float price, String year, String sizescreen) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.year = year;
        this.sizescreen = sizescreen;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public float getPrice() { return price;}

    public String getYear() {
        return year;
    }

    public String getSizescreen() { return sizescreen; }
}
