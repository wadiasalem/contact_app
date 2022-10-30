package com.example.listviewapp1;

public class Contact {

    static int incriment = 0;
    int id;
    String name;
    String lastName;
    String tel;

    public Contact(String name, String lastName, String tel) {

        this.id = incriment++;
        this.name = name;
        this.lastName = lastName;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tel=" + tel +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
