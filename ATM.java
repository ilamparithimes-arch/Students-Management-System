import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class ATM {

    private Customer customer;
    private ArrayList<String> transactionLog;
    private Scanner sc;

    public ATM(Customer customer) {
        this.customer = customer;
        transactionLog = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public void start() {

        int choice;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt();

                Account account = customer.getAccount();

                switch (choice) {

                    case 1:
                        if (verifyPIN()) {
                            System.out.println(
                                "Balance: " + account.getBalance()
                            );
                        }
                        break;

                    case 2:
                        if (verifyPIN()) {
                            System.out.print("Enter amount: ");
                            double depositAmount = sc.nextDouble();

                            account.deposit(depositAmount);

                            transactionLog.add(
                                LocalDateTime.now()
                                + " Deposit: " + depositAmount
                            );
                        }
                        break;

                    case 3:
                        if (verifyPIN()) {
                            System.out.print("Enter amount: ");
                            double withdrawAmount = sc.nextDouble();

                            account.withdraw(withdrawAmount);

                            transactionLog.add(
                                LocalDateTime.now()
                                + " Withdrawal: " + withdrawAmount
                            );
                        }
                        break;

                    case 4:
                        if (verifyPIN()) {
                            System.out.println(
                                "===== Transaction History ====="
                            );

                            for (String log : transactionLog) {
                                System.out.println(log);
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } catch (InsufficientFundsException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

                choice = 0;

            } catch (java.util.InputMismatchException e) {

                System.out.println("Please enter numbers only!");
                sc.nextLine();
                choice = 0;
            }

        } while (choice != 5);
    }

    private boolean verifyPIN() {

        System.out.print("Enter PIN: ");
        String enteredPin = sc.next();

        if (customer.getAccount().verifyPIN(enteredPin)) {
            return true;
        }

        System.out.println("Invalid PIN!");
        return false;
    }

    public static void main(String[] args) {

        SavingsAccount account =
            new SavingsAccount("123456", 10000, "1234");

        Customer customer =
            new Customer("Ilamparithi", "C101", account);

        ATM atm = new ATM(customer);

        atm.start();
    }
}