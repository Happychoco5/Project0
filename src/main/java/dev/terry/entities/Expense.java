package dev.terry.entities;

public class Expense {
    private int id; //ID of the expense, cannot be 0
    private String description;
    private Category type;
    private double amount; //Amount of the expense, cannot be 0
    //private boolean isModifiable = true;
    private int employeeAssigned; //Get the employee assigned via ID, cannot be 0
    private Status status; //Displays the status as either PENDING, APPROVED or DENIED

    public Expense(){

    }
    public Expense(int id, String description, Category type, double amount, int employeeAssigned) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.employeeAssigned = employeeAssigned;
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

    public int getEmployeeAssigned() {
        return employeeAssigned;
    }

    public void setEmployeeAssigned(int employeeAssigned) {
        this.employeeAssigned = employeeAssigned;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", employeeAssigned=" + employeeAssigned +
                ", status=" + status +
                '}';
    }
}
