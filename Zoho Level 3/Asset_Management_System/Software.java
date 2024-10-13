package Asset_Management_System;

import java.util.Date;

public class Software {
    private String name;
    private Vendor vendor;
    private double costPerDevice;
    private Date expiryDate;

    public Software(String name, Vendor vendor, double costPerDevice, Date expiryDate) {
        this.name = name;
        this.vendor = vendor;
        this.costPerDevice = costPerDevice;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public double getCostPerDevice() {
        return costPerDevice;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}

