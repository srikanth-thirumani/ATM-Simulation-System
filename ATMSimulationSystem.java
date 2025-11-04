import java.util.*;

class User {
    private String userId;
    private int pin;
    private double balance;

    public User(String userId, int pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() { return userId; }
    public int getPin() { return pin; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
        else System.out.println("Invalid deposit amount!");
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
        else System.out.println("Insufficient balance or invalid amount!");
    }
}

public class ATMSimulationSystem {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample users
        users.put("user1", new User("user1", 1234, 5000.0));
        users.put("user2", new User("user2", 5678, 10000.0));

        System.out.println("--- Welcome to ATM Simulation ---");
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        User currentUser = authenticate(userId, pin);
        if (currentUser == null) {
            System.out.println("Invalid credentials!");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Current Balance: " + currentUser.getBalance());
                case 2 -> {
                    System.out.print("Enter amount to deposit: ");
                    currentUser.deposit(sc.nextDouble());
                    System.out.println("Deposit successful!");
                }
                case 3 -> {
                    System.out.print("Enter amount to withdraw: ");
                    currentUser.withdraw(sc.nextDouble());
                    System.out.println("Withdrawal successful!");
                }
                case 4 -> System.out.println("Thank you for using ATM!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    private static User authenticate(String userId, int pin) {
        User user = users.get(userId);
        if (user != null && user.getPin() == pin) return user;
        return null;
    }
}
