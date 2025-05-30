package Entities;

public class User {
    private final String name;
    private final String email;
    private final String password;
    private double monthlyExpenseLimit = 0.0;
    private boolean emergencyMode;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getMonthlyExpenseLimit() { return this.monthlyExpenseLimit; }

    public void setMonthlyExpenseLimit(double limit) {
        this.monthlyExpenseLimit = limit;
    }

    public boolean isEmergencyMode() {
        // Example: Auto-enable emergency mode if expenses exceed limits
        // You would need to track current month's expenses elsewhere
        return this.emergencyMode; // or some calculated condition
    }
    public void toggleEmergencyMode() {
        this.emergencyMode = !this.emergencyMode;
    }

    public void setEmergencyMode(boolean emergencyMode) {
        this.emergencyMode = emergencyMode;
    }

}
