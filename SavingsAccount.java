
public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500;

    public SavingsAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        balance += amount;
        System.out.printf("Deposited %.2f. New balance: %.2f%n", amount, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        if (balance - amount < MIN_BALANCE) {
            throw new InsufficientFundsException("Insufficient balance! Minimum required: " + MIN_BALANCE);
        }
        balance -= amount;
        System.out.printf("Withdrawn %.2f. New balance: %.2f%n", amount, balance);
    }
}
