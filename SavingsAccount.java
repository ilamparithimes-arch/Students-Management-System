class SavingsAccount extends Account {

    private double interestRate = 5.0;

    public SavingsAccount(String accountNumber,
                          double balance, String pin) {

        super(accountNumber, balance, pin);
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Invalid amount!"
            );
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                "Insufficient Balance!"
            );
        }

        balance -= amount;
        System.out.println("Withdrawal Successful!");
    }

    public void calculateInterest() {

        double interest = balance * interestRate / 100;
        balance += interest;

        System.out.println("Interest Added: " + interest);
    }
}