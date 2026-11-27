abstract class Account {

    protected String accountNumber;
    protected double balance;
    protected String pin;

    public Account(String accountNumber,
                   double balance, String pin) {

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public void deposit(double amount)
            throws IllegalArgumentException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero!"
            );
        }

        balance += amount;
        System.out.println("Deposit Successful!");
    }

    public abstract void withdraw(double amount)
            throws InsufficientFundsException;

    public double getBalance() {
        return balance;
    }

    public boolean verifyPIN(String enteredPin) {
        return pin.equals(enteredPin);
    }
}