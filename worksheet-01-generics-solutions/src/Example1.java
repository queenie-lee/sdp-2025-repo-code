public class Example1 {
    public static void main(String... args) {
        // use either <> or var
        // if both are used, then T is inferred to be Object
        var bankAccountStorage = new Storage<BankAccount>();
        var stringStorage = new Storage<String>();

        BankAccount account = new BankAccount(2025);
        bankAccountStorage.setItem(account);

        BankAccount account1 = bankAccountStorage.getItem();
        account1.deposit(15);
    }
}
