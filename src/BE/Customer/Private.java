package BE.Customer;

public class Private extends Customer{
    //DEFAULT Private Customertype = 2

    public Private(int customerid, String name, String address, String cvrNummer) {
        super(customerid, name, address, cvrNummer, 2);
    }
    public Private(String name, String address, String cvrNummer) {
        super(name, address, cvrNummer, 2);
    }
}
