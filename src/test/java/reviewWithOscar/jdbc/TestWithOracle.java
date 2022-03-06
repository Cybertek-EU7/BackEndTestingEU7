package reviewWithOscar.jdbc;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void listOfMapExample(){
        Map<String,Object> rowOneData = new HashMap<>();  // insertion order is not kept
        rowOneData.put("firstName","Steven");
        rowOneData.put("lastName","King");
        rowOneData.put("salary","24000");
        System.out.println("rowOneData = " + rowOneData);

        Map<String,Object > rowTwoData = new HashMap<>();
        rowTwoData.put("firstName","Neena");
        rowTwoData.put("lastName","Kochhar");
        rowTwoData.put("salary","17000");
        System.out.println("rowTwoData = " + rowTwoData);

        List<Map<String,Object>> list = new ArrayList<>();

        list.add(rowOneData);
        list.add(rowTwoData);

        // get Neena's salary
        System.out.println("get Neena's salary"+ list.get(1).get("salary"));

    }


    @Test
    public void DynamicListOfMap(){





    }

}
