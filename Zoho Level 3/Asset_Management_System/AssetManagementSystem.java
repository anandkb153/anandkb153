package Asset_Management_System;

import java.text.SimpleDateFormat;
import java.util.*;

public class AssetManagementSystem {
    private List<Software> softwares;
    private List<Device> devices;
    private List<Employee> employees;
    private List<Vendor> vendors;
    private Scanner scanner;

    public AssetManagementSystem() {
        this.softwares = new ArrayList<>();
        this.devices = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.vendors = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Methods to add entities
    public void addSoftware(Software software) {
        softwares.add(software);
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addVendor(Vendor vendor) {
        vendors.add(vendor);
    }

    // Methods to install software
    public void installSoftwareOnDevice(Device device, Software software, Date installationDate) {
        Installation installation = new Installation(software, installationDate);
        device.addInstallation(installation);
    }

    // Report methods
    public int numberOfInstallations(String softwareName) {
        int count = 0;
        for (Device device : devices) {
            for (Installation installation : device.getInstallations()) {
                if (installation.getSoftware().getName().equals(softwareName)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int numberOfSoftwareInstalledOnDevice(String deviceId) {
        for (Device device : devices) {
            if (device.getDeviceId().equals(deviceId)) {
                return device.getInstallations().size();
            }
        }
        return 0;
    }

    public int numberOfSoftwareInstalledForEmployee(String employeeId) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                for (Device device : employee.getDevices()) {
                    count += device.getInstallations().size();
                }
            }
        }
        return count;
    }

    public double amountSpentOnSoftware(String softwareName) {
        double total = 0;
        for (Device device : devices) {
            for (Installation installation : device.getInstallations()) {
                if (installation.getSoftware().getName().equals(softwareName)) {
                    total += installation.getSoftware().getCostPerDevice();
                }
            }
        }
        return total;
    }

    public double amountSpentForEmployee(String employeeId) {
        double total = 0;
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                for (Device device : employee.getDevices()) {
                    for (Installation installation : device.getInstallations()) {
                        total += installation.getSoftware().getCostPerDevice();
                    }
                }
            }
        }
        return total;
    }

    public int numberOfInstallationsFromVendor(String vendorId) {
        int count = 0;
        for (Device device : devices) {
            for (Installation installation : device.getInstallations()) {
                if (installation.getSoftware().getVendor().getVendorId().equals(vendorId)) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<Device> devicesWithExpiredSoftware(Date currentDate) {
        List<Device> expiredDevices = new ArrayList<>();
        for (Device device : devices) {
            for (Installation installation : device.getInstallations()) {
                if (installation.getSoftware().getExpiryDate().before(currentDate)) {
                    expiredDevices.add(device);
                    break;
                }
            }
        }
        return expiredDevices;
    }

    // Method to get user input and perform actions
    public void run() {
        while (true) {
            System.out.println("\nAsset Management System Menu:");
            System.out.println("1. Add Vendor");
            System.out.println("2. Add Software");
            System.out.println("3. Add Employee");
            System.out.println("4. Add Device");
            System.out.println("5. Install Software on Device");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addVendor();
                    break;
                case 2:
                    addSoftware();
                    break;
                case 3:
                    addEmployee();
                    break;
                case 4:
                    addDevice();
                    break;
                case 5:
                    installSoftware();
                    break;
                case 6:
                    generateReport();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addVendor() {
        System.out.print("Enter Vendor ID: ");
        String vendorId = scanner.nextLine();
        System.out.print("Enter Vendor Name: ");
        String name = scanner.nextLine();
        Vendor vendor = new Vendor(vendorId, name);
        addVendor(vendor);
        System.out.println("Vendor added successfully.");
    }

    private void addSoftware() {
        System.out.print("Enter Software Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Vendor ID: ");
        String vendorId = scanner.nextLine();
        Vendor vendor = findVendorById(vendorId);
        if (vendor == null) {
            System.out.println("Vendor not found. Please add the vendor first.");
            return;
        }
        System.out.print("Enter Cost Per Device: ");
        double costPerDevice = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Expiry Date (yyyy-mm-dd): ");
        String expiryDateString = scanner.nextLine();
        Date expiryDate = parseDate(expiryDateString);
        if (expiryDate == null) {
            System.out.println("Invalid date format.");
            return;
        }
        Software software = new Software(name, vendor, costPerDevice, expiryDate);
        addSoftware(software);
        System.out.println("Software added successfully.");
    }

    private void addEmployee() {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        Employee employee = new Employee(employeeId, name);
        addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private void addDevice() {
        System.out.print("Enter Device ID: ");
        String deviceId = scanner.nextLine();
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found. Please add the employee first.");
            return;
        }
        Device device = new Device(deviceId, employee);
        employee.addDevice(device);
        addDevice(device);
        System.out.println("Device added successfully.");
    }

    private void installSoftware() {
        System.out.print("Enter Device ID: ");
        String deviceId = scanner.nextLine();
        Device device = findDeviceById(deviceId);
        if (device == null) {
            System.out.println("Device not found. Please add the device first.");
            return;
        }
        System.out.print("Enter Software Name: ");
        String softwareName = scanner.nextLine();
        Software software = findSoftwareByName(softwareName);
        if (software == null) {
            System.out.println("Software not found. Please add the software first.");
            return;
        }
        System.out.print("Enter Installation Date (yyyy-mm-dd): ");
        String installationDateString = scanner.nextLine();
        Date installationDate = parseDate(installationDateString);
        if (installationDate == null) {
            System.out.println("Invalid date format.");
            return;
        }
        installSoftwareOnDevice(device, software, installationDate);
        System.out.println("Software installed successfully on device.");
    }

    private void generateReport() {
        System.out.println("\nReport Menu:");
        System.out.println("1. Number of installations of a particular software");
        System.out.println("2. Number of software installed on a device");
        System.out.println("3. Number of software installed for an employee");
        System.out.println("4. Amount spent on a software");
        System.out.println("5. Amount spent for an employee");
        System.out.println("6. Number of installations from a vendor");
        System.out.println("7. Devices with expired software");
        System.out.print("Choose a report option: ");
        int reportOption = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (reportOption) {
            case 1:
                System.out.print("Enter Software Name: ");
                String softwareName = scanner.nextLine();
                System.out.println("Number of installations of " + softwareName + ": " + numberOfInstallations(softwareName));
                break;
            case 2:
                System.out.print("Enter Device ID: ");
                String deviceId = scanner.nextLine();
                System.out.println("Number of software installed on device " + deviceId + ": " + numberOfSoftwareInstalledOnDevice(deviceId));
                break;
            case 3:
                System.out.print("Enter Employee ID: ");
                String employeeId = scanner.nextLine();
                System.out.println("Number of software installed for employee " + employeeId + ": " + numberOfSoftwareInstalledForEmployee(employeeId));
                break;
            case 4:
                System.out.print("Enter Software Name: ");
                String softwareNameForAmount = scanner.nextLine();
                System.out.println("Amount spent on " + softwareNameForAmount + ": " + amountSpentOnSoftware(softwareNameForAmount));
                break;
            case 5:
                System.out.print("Enter Employee ID: ");
                String employeeIdForAmount = scanner.nextLine();
                System.out.println("Amount spent for employee " + employeeIdForAmount + ": " + amountSpentForEmployee(employeeIdForAmount));
                break;
            case 6:
                System.out.print("Enter Vendor ID: ");
                String vendorId = scanner.nextLine();
                System.out.println("Number of installations from vendor " + vendorId + ": " + numberOfInstallationsFromVendor(vendorId));
                break;
            case 7:
                System.out.print("Enter Current Date (yyyy-mm-dd): ");
                String currentDateStr = scanner.nextLine();
                Date currentDate = parseDate(currentDateStr);
                if (currentDate == null) {
                    System.out.println("Invalid date format.");
                    return;
                }
                List<Device> expiredDevices = devicesWithExpiredSoftware(currentDate);
                System.out.println("Devices with expired software:");
                for (Device device : expiredDevices) {
                    System.out.println(device.getDeviceId());
                }
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private Vendor findVendorById(String vendorId) {
        for (Vendor vendor : vendors) {
            if (vendor.getVendorId().equals(vendorId)) {
                return vendor;
            }
        }
        return null;
    }

    private Software findSoftwareByName(String softwareName) {
        for (Software software : softwares) {
            if (software.getName().equals(softwareName)) {
                return software;
            }
        }
        return null;
    }

    private Employee findEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    private Device findDeviceById(String deviceId) {
        for (Device device : devices) {
            if (device.getDeviceId().equals(deviceId)) {
                return device;
            }
        }
        return null;
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        AssetManagementSystem system = new AssetManagementSystem();
        system.run();
    }
}
