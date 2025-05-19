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
        return isEmergencyMode();
    }

    public void setEmergencyMode(boolean emergencyMode) {
        this.emergencyMode = emergencyMode;
    }

}
