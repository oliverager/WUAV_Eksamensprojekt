package BE.Customer;

public class B2C extends Customer{
    //DEFAULT B2C Customertype = 2

    public B2C(String name, String address, double cvrNummer, int customerid) {
        super(name, address, cvrNummer, 2, customerid);
    }
}
