package dev.terry.entities;

import dev.terry.entities.enums.Category;
import dev.terry.entities.enums.Status;

public class Expense {
    private int id; //ID of the expense, cannot be 0
    private String description;
    private Category type;
    private double amount; //Amount of the expense, cannot be 0
    //private boolean isModifiable = true;
    private int employeeId; //Get the employee assigned via ID, cannot be 0
    private Status status; //Displays the status as either PENDING, APPROVED or DENIED

    public Category getType() {
        return type;
    }

    public void setType(Category type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Expense(){

    }
    public Expense(int id, String description, Category type, double amount, int employeeId) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.employeeId = employeeId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", employeeAssigned=" + employeeId +
                ", status=" + status +
                '}';
    }
}
