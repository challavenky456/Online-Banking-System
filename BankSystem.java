
import java.util.ArrayList;

public class BankSystem {
    private final ArrayList<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account created successfully! Account No: " + account.getAccountNumber());
    }

    public Account findAccount(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) return acc;
        }
        return null;
    }

    public boolean transferFunds(int fromAccNo, int toAccNo, double amount) throws InsufficientFundsException {
        Account from = findAccount(fromAccNo);
        Account to = findAccount(toAccNo);
        if (from == null || to == null) {
            System.out.println("One of the account numbers does not exist.");
            return false;
        }
        from.withdraw(amount);
        to.deposit(amount);
        return true;
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        for (Account acc : accounts) {
            acc.showDetails();
            System.out.println("--------------------");
        }
    }

    public boolean deleteAccount(int accNo) {
        Account acc = findAccount(accNo);
        if (acc != null) {
            accounts.remove(acc);
            System.out.println("Account " + accNo + " deleted.");
            return true;
        }
        return false;
    }
}
