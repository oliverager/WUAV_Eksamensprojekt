package BE.Customer;

import java.beans.Customizer;
import java.beans.PropertyChangeListener;

public class B2B extends Customer {
    //DEFAULT B2B Customertype = 1

    public B2B(String name, String address, double cvrNummer, int customerid) {
        super(name, address, cvrNummer, 1, customerid);
    }
}
