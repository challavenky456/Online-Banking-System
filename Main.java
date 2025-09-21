
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankSystem bankSystem = new BankSystem();

        while (true) {
            printMenu();
            int choice;
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer choice.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Enter opening balance: ");
                        double balance = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Account Type (1.Savings / 2.Current): ");
                        int type = Integer.parseInt(sc.nextLine().trim());
                        Account acc = (type == 1) ? new SavingsAccount(name, balance) : new CurrentAccount(name, balance);
                        bankSystem.addAccount(acc);
                        break;

                    case 2:
                        System.out.print("Enter account number: ");
                        int accNoDep = Integer.parseInt(sc.nextLine().trim());
                        Account accDep = bankSystem.findAccount(accNoDep);
                        if (accDep != null) {
                            System.out.print("Enter deposit amount: ");
                            double depAmt = Double.parseDouble(sc.nextLine().trim());
                            accDep.deposit(depAmt);
                        } else {
                            System.out.println("Account not found!");
                        }
                        break;

                    case 3:
                        System.out.print("Enter account number: ");
                        int accNoW = Integer.parseInt(sc.nextLine().trim());
                        Account accW = bankSystem.findAccount(accNoW);
                        if (accW != null) {
                            System.out.print("Enter withdrawal amount: ");
                            double wAmt = Double.parseDouble(sc.nextLine().trim());
                            try {
                                accW.withdraw(wAmt);
                            } catch (InsufficientFundsException ex) {
                                System.out.println("Error: " + ex.getMessage());
                            }
                        } else {
                            System.out.println("Account not found!");
                        }
                        break;

                    case 4:
                        System.out.print("Enter sender account number: ");
                        int senderAcc = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter receiver account number: ");
                        int receiverAcc = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter transfer amount: ");
                        double tAmt = Double.parseDouble(sc.nextLine().trim());
                        try {
                            boolean ok = bankSystem.transferFunds(senderAcc, receiverAcc, tAmt);
                            if (ok) System.out.println("Transfer successful!");
                        } catch (InsufficientFundsException ex) {
                            System.out.println("Transfer failed: " + ex.getMessage());
                        }
                        break;

                    case 5:
                        System.out.print("Enter account number: ");
                        int accNo = Integer.parseInt(sc.nextLine().trim());
                        Account accD = bankSystem.findAccount(accNo);
                        if (accD != null) accD.showDetails();
                        else System.out.println("Account not found!");
                        break;

                    case 6:
                        bankSystem.displayAllAccounts();
                        break;

                    case 7:
                        System.out.println("Exiting... Thank you!");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Numeric input expected. " + nfe.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n====== ONLINE BANKING SYSTEM ======");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Display Account Details");
        System.out.println("6. Display All Accounts");
        System.out.println("7. Exit");
    }
}
