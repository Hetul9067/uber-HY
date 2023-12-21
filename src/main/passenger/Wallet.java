package main.passenger;

public class Wallet {

    private int wallet_id;
    //wallet money
    private double money ;


    //for adding money to the wallet
    public void addMoney(double money){
        this.money+=money;
    }

    //for deducting money from the wallet
    public void deductMoney(double money){

    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(int wallet_id) {
        this.wallet_id = wallet_id;
    }
}
