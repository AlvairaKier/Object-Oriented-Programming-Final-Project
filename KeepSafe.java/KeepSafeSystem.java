import java.util.ArrayList;
import java.util.Scanner;

// Represents a financial transaction
class Transaction 
{
    // Encapsulation
    private final String type;
    private final String sourceAccount;
    private final String targetAccount;
    private final double amount;
    private final String timestamp;

    // Constructor to initialize transaction details
    public Transaction(String type, String sourceAccount, String targetAccount, double amount, String timestamp) 
    {
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // polymorphism and overriding
    @Override
    public String toString() 
    {
        return "\nTransaction: " + type +
                " | Sender: " + sourceAccount +
                " | Receiver: " + (targetAccount != null ? targetAccount : "N/A") +
                " | Amount: " + amount +
                " | Timestamp: " + timestamp;
    }
}

// Represents a bank account
class Account 
{
    // Encapsulion
    private final String accountName;
    private double balance;

    // Constructor to initialize account details
    public Account(String accountName, double balance) 
    {
        this.accountName = accountName;
        this.balance = balance;
    }

    // Getter for account name
    public String getAccountName() 
    {
        return accountName;
    }

    // Getter for balance
    public double getBalance() 
    {
        return balance;
    }

    // Method to deposit money into the account
    public void deposit(double amount, ArrayList<Transaction> transactions) {
        if (amount > 0) {
            balance += amount;

            // Log the transaction
            transactions.add(new Transaction("Deposit", accountName, null, amount, java.time.LocalDateTime.now().toString()));
            System.out.println("\n" + amount + " has been deposited to " + accountName + ". \nNew balance: " + balance);
        } else {
            System.out.println(" _________________________");
            System.out.println("|                         |");
            System.out.println("| Invalid deposit amount. |");
            System.out.println("|_________________________|");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount, ArrayList<Transaction> transactions) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;

            // Log the transaction
            transactions.add(new Transaction("Withdraw", accountName, null, amount, java.time.LocalDateTime.now().toString()));
            System.out.println("\n" + amount + " has been withdrawn from " + accountName + ". \nRemaining balance: " + balance);
        } else {
            System.out.println(" __________________________________________________");
            System.out.println("|                                                  |");
            System.out.println("| Invalid withdrawal amount or insufficient funds. |");
            System.out.println("|__________________________________________________|");
        }
    }
}

// inheritance
class KeepSafe extends Account {
    public KeepSafe(String accountName, double balance) 
    {
        super(accountName, balance);
    }

    // Method to transfer money between accounts
    public void transferMoney(KeepSafe targetAccount, double amount, ArrayList<Transaction> transactions) 
    {
        if (amount > 0 && amount <= this.getBalance()) 
        {
            // Withdraw from source account
            this.withdraw(amount, transactions);

            // Deposit into target account
            targetAccount.deposit(amount, transactions);

            // log the transfer transaction
            transactions.add(new Transaction("Transfer", this.getAccountName(), targetAccount.getAccountName(), amount, java.time.LocalDateTime.now().toString()));
            System.out.println("\nTransferred " + amount + " from " + this.getAccountName() + " to " + targetAccount.getAccountName());
        } else {
            System.out.println(" ______________________________________________________________");
            System.out.println("|                                                              |");
            System.out.println("| Transfer failed due to invalid amount or insufficient funds. |");
            System.out.println("|______________________________________________________________|");
        }
    }
}

public class KeepSafeSystem 
{
    // pre defined credentials for login
    private static final String USERNAME = "user123"; 
    private static final String PASSWORD = "pass123";

    // stores the transaction log
    private static final ArrayList<Transaction> transactions = new ArrayList<>();

    // user log in interface
    public static void main(String[] args) 
    {
        try (Scanner scanner = new Scanner(System.in)) 
        {

            boolean isAuthenticated = false;

            // login Interface
            System.out.println("+ ===== Welcome to KeepSafe ===== +\n");
            do 
            {
                // Prompt user for login credentials
                System.out.print("Enter Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                // Authenticate the user
                if (authenticate(username, password)) 
                {
                    isAuthenticated = true;
                    System.out.println("Login successful!\n");
                    runKeepSafeSystem(scanner);
                } else {
                    System.out.println("\nInvalid credentials. Please try again.\n");
                }
            } while (!isAuthenticated);
        }
    }

    // Method to authenticate the user input in log in
    private static boolean authenticate(String username, String password) 
    {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }

    // Main menu and system functionality
    private static void runKeepSafeSystem(Scanner scanner) 
    {
        // savings and expenses account amount initializer
        KeepSafe expensesAccount = new KeepSafe("Expenses Account", 50);
        KeepSafe savingsAccount = new KeepSafe("Savings Account", 150);

        boolean exit = false;

        // Display menu and handle user choices
        while (!exit) 
        {
            System.out.println("\n+ =========== KeepSafe Menu =========== +");
            System.out.println("| ------------------------------------- |");
            System.out.println("|\t(1) View Balances\t\t|");
            System.out.println("|\t(2) Deposit\t\t\t|");
            System.out.println("|\t(3) Withdraw\t\t\t|");
            System.out.println("|\t(4) Transfer Money\t\t|");
            System.out.println("|\t(5) View Transaction Log\t|");
            System.out.println("|\t(6) Exit\t\t\t|");
            System.out.println("| ------------------------------------- |");
            System.out.println("+ ===================================== +\n");
            System.out.print("Choose an option: ");

            // Get user input and perform corresponding action
            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1 -> 
                {
                    // Display account balances
                    System.out.println("\n+ =================== Account Balance =================== +");
                    System.out.println("| ------------------------------------------------------- |");
                    System.out.println("\t" + expensesAccount.getAccountName() + ": " + expensesAccount.getBalance());
                    System.out.println("\t" + savingsAccount.getAccountName() + ": " + savingsAccount.getBalance());
                    System.out.println("+ ======================================================= +");
                }

                case 2 -> 
                {
                    // Deposit money into selected account
                    System.out.println("\nCurrent Balances:");
                    System.out.println("\t" + expensesAccount.getAccountName() + ": " + expensesAccount.getBalance());
                    System.out.println("\t" + savingsAccount.getAccountName() + ": " + savingsAccount.getBalance());

                    // prompts the user where to deposit
                    System.out.print("\nEnter account to deposit to ((1) Expenses | (2) Savings): ");
                    int depositChoice = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();

                    switch (depositChoice) 
                    {
                        case 1 -> expensesAccount.deposit(depositAmount, transactions);
                        case 2 -> savingsAccount.deposit(depositAmount, transactions);
                        default -> System.out.println("\nInvalid choice.");
                    }
                }

                case 3 -> 
                {
                    // Withdraw money from selected account
                    System.out.println("\nCurrent Balances:");
                    System.out.println("\t" + expensesAccount.getAccountName() + ": " + expensesAccount.getBalance());
                    System.out.println("\t" + savingsAccount.getAccountName() + ": " + savingsAccount.getBalance());

                    // prompts the user where to withdraw
                    System.out.print("\nEnter account to withdraw from ((1) Expenses | (2) Savings): ");
                    int withdrawChoice = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();

                    switch (withdrawChoice) 
                    {
                        case 1 -> expensesAccount.withdraw(withdrawAmount, transactions);
                        case 2 -> savingsAccount.withdraw(withdrawAmount, transactions);
                        default -> System.out.println("\nInvalid choice.");
                    }
                }

                case 4 -> 
                {
                    // Transfer money between accounts
                    System.out.println("\nCurrent Balances:");
                    System.out.println("\t" + expensesAccount.getAccountName() + ": " + expensesAccount.getBalance());
                    System.out.println("\t" + savingsAccount.getAccountName() + ": " + savingsAccount.getBalance());

                    // prompts the user  where to direct their money
                    System.out.print("\nEnter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Transfer direction ((1) Expenses to Savings | (2) Savings to Expenses): ");
                    int transferChoice = scanner.nextInt();

                    switch (transferChoice) 
                    {
                        case 1 -> expensesAccount.transferMoney(savingsAccount, transferAmount, transactions);
                        case 2 -> savingsAccount.transferMoney(expensesAccount, transferAmount, transactions);
                        default -> System.out.println("\nInvalid choice.");
                    }
                }

                case 5 -> 
                {
                    // Display transaction log
                    System.out.println("\n+ ============== Transaction Log ============== +");
                    if (transactions.isEmpty()) 
                    {
                        System.out.println("No transactions recorded yet.");
                    } else {
                        // transaction records getter
                        for (Transaction t : transactions)
                        {
                            System.out.println(t);
                        }
                    }
                }

                case 6 -> 
                {
                    // Exit the system
                    System.out.println(" _______________________________________________");
                    System.out.println("|                                               |");
                    System.out.println("| Exiting system. Thank you for using KeepSafe! |");
                    System.out.println("|_______________________________________________|");
                    exit = true;
                }
                default -> System.out.println("\nInvalid option. Please try again.");
            }
        }
    }
}


