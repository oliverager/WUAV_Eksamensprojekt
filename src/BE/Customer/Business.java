package BE.Customer;

public class Business extends Customer {
    //DEFAULT Business Customertype = 1

    public Business(int customerid, String name, String address, String cvrNummer) {
        super(customerid, name, address, cvrNummer, 1);
    }
    public Business(String name, String address, String cvrNummer) {
        super(name, address, cvrNummer, 1);
    }



}
