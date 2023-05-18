package BE.Customer;

public class Customer {

    private String name;
    private String address;
    private String cvrNummer;
    private int customertype;
    private int customerid;

    public Customer(int customerid, String name, String address, String cvrNummer, int customertype) {
        this.customerid = customerid;
        this.name = name;
        this.address = address;
        this.cvrNummer = cvrNummer;
        this.customertype = customertype;
    }

    public Customer(String name, String address, String cvrNummer, int customertype) {
        this.name = name;
        this.address = address;
        this.cvrNummer = cvrNummer;
        this.customertype = customertype;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCvrNummer() {
        return cvrNummer;
    }

    public int getCustomertype() {
        return customertype;
    }

    public int getCustomerid() {
        return customerid;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cvrNummer=" + cvrNummer +
                ", customertype=" + customertype +
                ", customerid=" + customerid +
                '}';
    }
}

