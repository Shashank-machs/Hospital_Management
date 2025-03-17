package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection connection;

    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public void addPatient(){
        System.out.print("Enter patient Name: ");
        String name=scanner.next();
        System.out.print("Enter Patient Age: ");
        int age=scanner.nextInt();
        System.out.print("Enter Gender: ");
        String gender=scanner.next();

        try{
            String query="Insert into patients(name,age,gender)values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);

            int affectedRows=preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("patient Added Successfully!");

            }
            else{
                System.out.println("Failed to add Patients");
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void viewPatients(){
        String query="select * from patients";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();

                System.out.println("Patients: ");
                System.out.println("+------------+-------------------+--------+-------------+");
                System.out.println("| Patient ID | Name              | Age    | Gender      |");
                System.out.println("+------------+-------------------+--------+-------------+");
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int age =resultSet.getInt("age");
                String gender=resultSet.getString("gender");
                System.out.printf("| %-10s | %-17s | %-6s | %-11s |\n",id,name,age,gender);
                System.out.println("+------------+-------------------+--------+-------------+");

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getPatientByID(int id){
        String query ="Select * from patients where id=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
