package User;

public class User {


    private static int nextid = 0;
    private int id = nextid++;
    private String name;
    private String lastname;
    private String username ;
    private String pin;
    private double amount;

    public User(String name, String lastname, String username, String pin, double amount){

        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.pin = pin;
        this.amount = amount;

    }

    public String getName(){
       return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
