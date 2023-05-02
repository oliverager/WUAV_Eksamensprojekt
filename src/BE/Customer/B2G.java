package BE.Customer;

public class B2G extends Customer {
    //DEFAULT B2G Customertype = 3

    public B2G(String name, String address, double cvrNummer, int customerid) {
        super(name, address, cvrNummer, 3, customerid);
    }
}
