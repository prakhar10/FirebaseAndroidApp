package entity;

/**
 * Created by Prakhar on 11/9/2017.
 */

public class User {

    private String name;
    private String contact;
    private String address;
    private int age;

    public User(String name, String contact, String address, int age) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
