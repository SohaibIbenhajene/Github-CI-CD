//Ibenhajene Sohaib r0883629
package fact.it.supermarket.controller;


import fact.it.supermarket.model.Customer;
import fact.it.supermarket.model.Department;
import fact.it.supermarket.model.Staff;
import fact.it.supermarket.model.Supermarket;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class MainController {
    private ArrayList<Staff> staffArrayList;
    private ArrayList<Customer> customerArrayList;
    private ArrayList<Supermarket> supermarketArrayList;

//    Write your code here
    @RequestMapping("/1_generate_customer")
    public String generateCustomer(Model model){

        model.addAttribute("supermarket", supermarketArrayList);

        return "1_generate_customer";
    }

    @RequestMapping("/submitcustomer")
    public String submitCustomer(HttpServletRequest request, Model model){

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int yearOfBirth = Integer.parseInt(request.getParameter("birthYear"));
        int shopIndex = Integer.parseInt(request.getParameter("shopIndex"));
        Supermarket shop = supermarketArrayList.get(shopIndex);

        Customer customer = new Customer(firstName, lastName);
        customer.setYearOfBirth(yearOfBirth);
        shop.registerCustomer(customer);
        customerArrayList.add(customer);
        model.addAttribute("customer", customer);

        return "2_customer_welcomepage";
    }

    @RequestMapping("/3_generate_staff")
    public String generateStaff(){
        return "3_generate_staff";
    }

    @RequestMapping("/submitstaff")
    public String submitStaff(HttpServletRequest request, Model model){

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        boolean isStudent = (request.getParameter("isStudent") != null);
        String tempStartDate = request.getParameter("startDate");

        Staff staff = new Staff(firstName, lastName);
        if(!tempStartDate.equals("")){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate startDate = LocalDate.parse(tempStartDate, dtf);
            staff.setStartDate(startDate);
        }
        staff.setStudent(isStudent);
        staffArrayList.add(staff);
        model.addAttribute("staff", staff);

        return "4_staff_welcomepage";
    }

    @PostConstruct
    private void fillData(){
        staffArrayList = fillStaffMembers();
        customerArrayList = fillCustomers();
        supermarketArrayList = fillSupermarkets();
    }

    @RequestMapping("/5_show_staff")
    public String showStaff(Model model){

        model.addAttribute("staffList", staffArrayList);

        return "5_show_staff";
    }

    @RequestMapping("/6_show_customers")
    public String showCustomers(Model model){

        model.addAttribute("customersList", customerArrayList);

        return "6_show_customers";
    }

    @RequestMapping("/7_generate_supermarket")
    public String generateSupermarket(Model model){

        model.addAttribute("staffList", staffArrayList);

        return "7_generate_supermarket";
    }

    @RequestMapping("submitsupermarket")
    public String submitSupermarket(HttpServletRequest request, Model model){

        String supermarketName = request.getParameter("supermarketName");
        int managerIndex = Integer.parseInt(request.getParameter("managerIndex"));
        if (managerIndex < 0){
            model.addAttribute("errormessage", "You didn't choose a general manager!");
            return "error";
        }

        Supermarket supermarket = new Supermarket(supermarketName);
        supermarket.setGeneralManager(staffArrayList.get(managerIndex));

        //supermarketArrayList.add(supermarket);
        model.addAttribute("newSupermarket", supermarket);

        return "0_exam";

    }

    @RequestMapping("/8_show_supermarkets")
    public String showSupermarkets(Model model){

        model.addAttribute("supermarketList", supermarketArrayList);

        return "8_show_supermarkets";

    }

    @RequestMapping("/9_generate_department")
    public String generateDepartment(Model model){

        model.addAttribute("supermarketList", supermarketArrayList);
        model.addAttribute("staffList", staffArrayList);

        return "9_generate_department";

    }

    @RequestMapping("/submitdepartment")
    public String submitDepartment(HttpServletRequest request, Model model){

        String departmentName = request.getParameter("departmentName");
        String departmentPhoto = request.getParameter("departmentPhoto");
        boolean isRefrigerated = (request.getParameter("isRefrigerated") != null);

        int shopIndex = Integer.parseInt(request.getParameter("shopIndex"));
        if(shopIndex < 0){
            model.addAttribute("errormessage", "You didn't choose a supermarket!");
            return "error";
        }

        int staffIndex = Integer.parseInt(request.getParameter("staffIndex"));
        if(staffIndex < 0){
            model.addAttribute("errormessage", "You didn't choose a staff member!");
            return "error";
        }

        Department department = new Department(departmentName);
        department.setPhoto("/img/" + departmentPhoto);
        department.setRefrigerated(isRefrigerated);
        department.setResponsible(staffArrayList.get(staffIndex));

        Supermarket supermarket = supermarketArrayList.get(shopIndex);
        supermarket.addDepartment(department);
        model.addAttribute("supermarket", supermarket);

        return "10_show_departments";

    }

    @RequestMapping("/10_show_departments")
    public String showDepartments(HttpServletRequest request, Model model){

        int shopIndex = Integer.parseInt(request.getParameter("shopIndex"));

        Supermarket supermarket = supermarketArrayList.get(shopIndex);
        model.addAttribute("supermarket", supermarket);

        return "10_show_departments";

    }

    @RequestMapping("/searchdepartment")
    public String searchDepartments(HttpServletRequest request, Model model){

        String departmentName =  request.getParameter("departmentName");
        for (Supermarket shop : supermarketArrayList){
            Department department = shop.searchDepartmentByName(departmentName);
            if(department != null){
                model.addAttribute("department", department);
                return "11_search_department";
            }
        }

        model.addAttribute("errormessage", "There is no department with the name \'" + departmentName + "\'");
        return "error";

    }

    private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995,1,1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999,4,1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007,9,18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Customer> fillCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("Dominik", "Mioens");
        customer1.setYearOfBirth(2001);
        Customer customer2 = new Customer("Zion", "Noops");
        customer2.setYearOfBirth(1996);
        Customer customer3 = new Customer("Maria", "Bonetta");
        customer3.setYearOfBirth(1998);
        Customer customer4 = new Customer("Sohaib", "Ibenhajene");
        customer4.setYearOfBirth(2003);
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.get(0).addToShoppingList("Butter");
        customers.get(0).addToShoppingList("Bread");
        customers.get(1).addToShoppingList("Apple");
        customers.get(1).addToShoppingList("Banana");
        customers.get(1).addToShoppingList("Grapes");
        customers.get(1).addToShoppingList("Oranges");
        customers.get(2).addToShoppingList("Fish");
        customers.get(3).addToShoppingList("Peanut butter");
        customers.get(3).addToShoppingList("Pears");
        customers.get(3).addToShoppingList("Avocado");

        return customers;
    }

    private ArrayList<Supermarket> fillSupermarkets() {
        ArrayList<Supermarket> supermarkets = new ArrayList<>();
        Supermarket supermarket1 = new Supermarket("Delhaize");
        Supermarket supermarket2 = new Supermarket("Colruyt");
        Supermarket supermarket3 = new Supermarket("Albert Heyn");
        Department department1 = new Department("Fruit");
        Department department2 = new Department("Bread");
        Department department3 = new Department("Vegetables");
        department1.setPhoto("/img/fruit.jpg");
        department2.setPhoto("/img/bread.jpg");
        department3.setPhoto("/img/vegetables.jpg");
        department1.setResponsible(staffArrayList.get(0));
        department2.setResponsible(staffArrayList.get(1));
        department3.setResponsible(staffArrayList.get(2));
        supermarket1.addDepartment(department1);
        supermarket1.addDepartment(department2);
        supermarket1.addDepartment(department3);
        supermarket2.addDepartment(department1);
        supermarket2.addDepartment(department2);
        supermarket3.addDepartment(department1);
        supermarket3.addDepartment(department3);
        supermarket1.setGeneralManager(staffArrayList.get(0));
        supermarket2.setGeneralManager(staffArrayList.get(1));
        supermarket3.setGeneralManager(staffArrayList.get(2));
        supermarkets.add(supermarket1);
        supermarkets.add(supermarket2);
        supermarkets.add(supermarket3);
        return supermarkets;
    }

}
