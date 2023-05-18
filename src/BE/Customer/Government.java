package BE.Customer;

public class Government extends Customer {
    //DEFAULT Government Customertype = 3

    public Government(int customerid, String name, String address, String cvrNummer) {
        super(customerid, name, address, cvrNummer, 3);
    }
    public Government(String name, String address, String cvrNummer) {
        super(name, address, cvrNummer, 2);
    }
}
