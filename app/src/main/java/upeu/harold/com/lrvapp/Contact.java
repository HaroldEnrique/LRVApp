package upeu.harold.com.lrvapp;

public class Contact {

    private String name;
    private String phone;
    private int Photo;


    public Contact() {
    }

    public Contact(String name, String phone, int photo) {
        this.name = name;
        this.phone = phone;
        Photo = photo;
    }

    // getter


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getPhoto() {
        return Photo;
    }


    //Setter


    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
