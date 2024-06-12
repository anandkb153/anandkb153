package Asset_Management_System;

public class Vendor {
    private String vendorId;
    private String name;

    public Vendor(String vendorId, String name) {
        this.vendorId = vendorId;
        this.name = name;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getName() {
        return name;
    }
}

