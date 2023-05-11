package BE.Customer;

public class B2G extends Customer {
    //DEFAULT B2G Customertype = 3

    public B2G(int customerid, String name, String address, double cvrNummer) {
        super(customerid, name, address, cvrNummer, 3);
    }
}
