package model.tm;

import javafx.scene.control.Button;

public class CustomerTm {

    private String Id;
    private String name;
    private String address;
    private double salary;

    private Button deleteButton;


    public CustomerTm() {
    }

    public CustomerTm(String id, String name, String address, double salary, Button deleteButton) {
        Id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.deleteButton = deleteButton;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
}
