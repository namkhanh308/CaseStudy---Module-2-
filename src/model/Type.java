package model;

import java.io.Serializable;

public class Type implements Serializable {
    private String id;
    private String typeName;
    private double discount;
    private String description;

    public Type() {
    }

    public Type(String id, String typeName, String description) {
        this.id = id;
        this.typeName = typeName;
        this.description = description;
        this.discount = 0;
    }

    public Type(String id, String typeName, double discount, String description) {
        this.id = id;
        this.typeName = typeName;
        this.discount = 0;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id='" + id + '\'' +
                ", typeName='" + typeName + '\'' +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                '}';
    }
}