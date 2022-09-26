//Ibenhajene Sohaib r0883629
package fact.it.supermarket.model;

public class Department {
    private String name;
    private String photo;
    private boolean refrigerated;
    private Staff responsible;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isRefrigerated(){
        return refrigerated;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }

    public Staff getResponsible() {
        return responsible;
    }

    public void setResponsible(Staff responsible) {
        this.responsible = responsible;
    }
}
