//Ibenhajene Sohaib r0883629
package fact.it.supermarket.model;

import java.util.ArrayList;

public class Customer extends Person {
    private int cardNumber;
    private int yearOfBirth;
    private ArrayList<String> shoppingList = new ArrayList<>();

    public Customer(String firstName, String surName) {
        super(firstName, surName);
        this.cardNumber = -1;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public ArrayList<String> getShoppingList() {
        return shoppingList;
    }

    public boolean addToShoppingList(String productName){
        boolean possibleToAdd = false;

        if (this.shoppingList.size() < 5){
            this.shoppingList.add(productName);
            possibleToAdd = true;
        }
        return possibleToAdd;
    }

    public int getNumberOnShoppingList(){
        return this.shoppingList.size();
    }

    public String toString(){
        return "Customer " + super.toString() + " with card number " + this.cardNumber;
    }
}
