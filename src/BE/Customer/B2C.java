package BE.Customer;

public class B2C extends Customer{
    //DEFAULT B2C Customertype = 2

    public B2C(int customerid, String name, String address, double cvrNummer) {
        super(customerid, name, address, cvrNummer, 2);
    }
}
