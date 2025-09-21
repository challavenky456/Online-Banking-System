
public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = -10000;

    public CurrentAccount(String name, double balance) {
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
        if (balance - amount < OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException("Overdraft limit exceeded!");
        }
        balance -= amount;
        System.out.printf("Withdrawn %.2f. New balance: %.2f%n", amount, balance);
    }
}
