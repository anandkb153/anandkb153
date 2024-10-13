package Asset_Management_System;

import java.util.ArrayList;
import java.util.List;

public class Device {
    private String deviceId;
    private Employee owner;
    private List<Installation> installations;

    public Device(String deviceId, Employee owner) {
        this.deviceId = deviceId;
        this.owner = owner;
        this.installations = new ArrayList<>();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Employee getOwner() {
        return owner;
    }

    public List<Installation> getInstallations() {
        return installations;
    }

    public void addInstallation(Installation installation) {
        installations.add(installation);
    }
}

