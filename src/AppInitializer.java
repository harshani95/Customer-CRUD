import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppInitializer {
    public static void main(String[] args) {

        try{
            Customer customer = new Customer(1001, "Kamal", "Colombo", 30000, "2020-8-23");
            if (saveCustomer(customer)){
                System.out.println("success!");
            }else{
                System.out.println("Try again!");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try{
            Customer customer = findById(1001);
            if (customer!=null){
                System.out.println("success!");
                System.out.println(customer.toString());
            }else{
                System.out.println("Try again!");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try{
          List<Customer> customers = findAll();
            if (!customers.isEmpty()){
                System.out.println("success!");
                for (Customer c:customers
                     ) {
                    System.out.println(c.toString());
                }
            }else{
                System.out.println("Try again!");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try{
            Customer customer = new Customer(1001, "Sunil", "Colombo", 40000, "2020-10-23");
            if (updateCustomer(customer)){
                System.out.println("success!");
            }else{
                System.out.println("Try again!");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try{

            if (deleteCustomer(1001)){
                System.out.println("success!");
            }else{
                System.out.println("Try again!");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private static boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
        sessionFactory.close();
        return true;
        }

    private static Customer findById(long id) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM customer WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new Customer(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    private static List<Customer> findAll() throws ClassNotFoundException, SQLException {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer ";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            customerList.add(new Customer(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));

        }
        return customerList;
    }

    private static boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name=?, address=?, salary=?, dob=? WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1,c.getName());
        preparedStatement.setString(2,c.getAddress());
        preparedStatement.setDouble(3,c.getSalary());
        preparedStatement.setObject(4,c.getDate());
        preparedStatement.setLong(5,c.getId());
        return preparedStatement.executeUpdate()>0;
    }

    private static boolean deleteCustomer(long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE  * FROM customer WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,id);
        return preparedStatement.executeUpdate()>0;
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate_db","root","1218");
        }
}
