
public abstract class Account {
    protected int accountNumber;
    protected String name;
    protected double balance;
    private static int accCounter = 1000;
    public static final String BANK_NAME = "ABC Bank";

    public Account(String name, double balance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (Double.isNaN(balance)) {
            throw new IllegalArgumentException("Invalid opening balance");
        }
        this.accountNumber = getNextAccNumber();
        this.name = name;
        this.balance = balance;
    }

    private static synchronized int getNextAccNumber() {
        return ++accCounter;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public void showDetails() {
        System.out.println("Bank: " + BANK_NAME);
        System.out.println("Account No: " + accountNumber + ", Name: " + name + ", Balance: " + String.format("%.2f", balance));
    }

    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getName() { return name; }
}
