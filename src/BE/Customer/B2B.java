package BE.Customer;

import java.beans.Customizer;
import java.beans.PropertyChangeListener;

public class B2B extends Customer {
    //DEFAULT B2B Customertype = 1

    public B2B(int customerid, String name, String address, double cvrNummer) {
        super(customerid, name, address, cvrNummer, 1);
    }



}
