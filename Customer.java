class Customer {

    private String name;
    private String customerId;
    private Account account;

    public Customer(String name, String customerId,
                    Account account) {
        this.name = name;
        this.customerId = customerId;
        this.account = account;
    }

    public void displayCustomerDetails() {
        System.out.println("Customer Name: " + name);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Account Balance: " + account.getBalance());
    }

    public Account getAccount() {
        return account;
    }
}