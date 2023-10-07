public class PlayerBet extends Hand{
    private int balance = 500;
    private int betAmount;
    private boolean win; 

    PlayerBet(int betAmount){
        this.balance -= betAmount;
        this.betAmount = betAmount;
    }

    public void updateBet(){
        if(this.win){
            this.balance += this.betAmount*2;
        }else{
            this.balance -= betAmount;
        }
    }

    public void updateStatus(boolean win){
        this.win = win;
    }

    public int getBalance(){
        return this.balance;
    }

    public int getBet(){
        return this.betAmount;
    }

    public void placebet(int bet){
        this.betAmount = bet;
    }
}