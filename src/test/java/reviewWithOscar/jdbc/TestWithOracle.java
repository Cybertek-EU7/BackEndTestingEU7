package reviewWithOscar.jdbc;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;

public class TestWithOracle {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @BeforeMethod
    public void connectToDB()  {
        String dbUrl = "jdbc:oracle:thin:@54.91.210.3:1521:xe";
        String dbUserName = "hr";
        String dbPassWord = "hr";
        String query = "select first_name,last_name,salary from employees";

        try {
            connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassWord);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @AfterMethod
    public void closeDB()  {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Test
    public void connectionTest() throws SQLException {


      //  resultSet.next()  : gets the cursor to the next row

      //  resultSet.getObject(1) :  brings the info in that cell

        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3));
        }


    }

    @Test
    public void verifyExample() throws SQLException {

        // get Steven King Salary and verify that it is 24000

        resultSet.next();  // at first query stands at the line zero
       // int actualSalary = resultSet.getInt(3);
        String actualSalary = resultSet.getString(3);
        String expectedSalary = "24000";
        System.out.println("actualSalary = " + actualSalary);
        System.out.println("expectedSalary = " + expectedSalary);

        Assert.assertEquals(actualSalary,expectedSalary);
    }



}
