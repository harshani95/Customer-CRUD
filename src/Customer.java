public class Customer {
    private long id;
    private String name;
    private String address;
    private double salary;
    private String date;

    public Customer(long id) {
        this.id = id;
    }

    public Customer(long id, String name, String address, double salary, String date) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", date='" + date + '\'' +
                '}';
    }
}
