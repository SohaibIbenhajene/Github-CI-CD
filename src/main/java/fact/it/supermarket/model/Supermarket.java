//Ibenhajene Sohaib r0883629
package fact.it.supermarket.model;

import java.util.ArrayList;

public class Supermarket {
    private String name;
    private int numberCustomers;
    private ArrayList<Department> departmentList = new ArrayList<>();
    private Staff generalManager;

    public Supermarket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberCustomers() {
        return numberCustomers;
    }

    public ArrayList<Department> getDepartmentList() {
        return departmentList;
    }

    public int getNumberOfDepartments(){
        return this.departmentList.size();
    }

    public void addDepartment(Department department){
        this.departmentList.add(department);
    }

    public Department searchDepartmentByName(String name){
        for (Department departmentName : this.departmentList){
            if (departmentName.getName().equals(name)){
                return departmentName;
            }
        }
        return null;
    }

    public void registerCustomer(Customer customer){
        this.numberCustomers += 1;
        customer.setCardNumber(this.numberCustomers);
    }

    public Staff getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(Staff generalManager) {
        this.generalManager = generalManager;
    }
}
