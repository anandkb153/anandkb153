package Asset_Management_System;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String employeeId;
    private String name;
    private List<Device> devices;

    public Employee(String employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
        this.devices = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }
}

