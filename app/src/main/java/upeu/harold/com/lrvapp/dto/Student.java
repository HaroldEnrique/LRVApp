package upeu.harold.com.lrvapp.dto;

public class Student {

    private int id;
    private String name;
    private String escuela;
    private String semester;
    private String address;
    private String phone;
    private byte[] image;

    public Student() {
    }

    public Student(String name, String escuela, String semester, String address, String phone, byte[] image) {
        this.name = name;
        this.escuela = escuela;
        this.semester = semester;
        this.address = address;
        this.phone = phone;
        this.image = image;
    }

    public Student(int id, String name, String escuela, String phone, byte[] image) {
        this.id = id;
        this.name = name;
        this.escuela = escuela;
        this.phone = phone;
        this.image = image;
    }
    public Student( String name, String escuela, String phone, byte[] image) {

        this.name = name;
        this.escuela = escuela;
        this.phone = phone;
        this.image = image;
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

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
