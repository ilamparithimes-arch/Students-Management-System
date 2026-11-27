class CheckingAccount extends Account {

    private double overdraftLimit = 5000;

    public CheckingAccount(String accountNumber,
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

        if (amount > balance + overdraftLimit) {
            throw new InsufficientFundsException(
                "Overdraft Limit Exceeded!"
            );
        }

        balance -= amount;

        System.out.println("Withdrawal Successful!");
        System.out.println("Remaining Balance: " + balance);
    }
}