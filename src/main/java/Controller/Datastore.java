package Controller;

import Model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Datastore {

    private static Connection cxn = null;
    private static Statement sqlStatement = null;

    private static void executeQuery(String query){
        try {
            Class.forName("org.sqlite.JDBC");
            cxn = DriverManager.getConnection("jdbc:sqlite:tasks.db");
            sqlStatement = cxn.createStatement();
            sqlStatement.executeUpdate(query);
            sqlStatement.close();
            cxn.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    private static ResultSet executeQueryWithResult(String query){
        ResultSet result = null;
        try {
            Class.forName("org.sqlite.JDBC");
            cxn = DriverManager.getConnection("jdbc:sqlite:tasks.db");
            cxn.setAutoCommit(false);
            sqlStatement = cxn.createStatement();
            result = sqlStatement.executeQuery( "SELECT id, description FROM tasks;" );
            result.close();
            sqlStatement.close();
            cxn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    public static void initializeStorage(){
        System.out.println("initializing storage");
        executeQuery("CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, description TEXT NOT NULL);");
        System.out.println("Tables created successfully");
    }

    public static void addTask(Task task){
        executeQuery("insert into tasks ('description') values ('" + task.getDescription() + "')");
        System.out.println("Record created successfully");
    }

    public static void deleteTask(int id){
        executeQuery("DELETE FROM tasks WHERE id = "+ id +";");
        System.out.println("Record deleted successfully");
    }

    public static List<Task> getTasks(){

        List<Task> tasks = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            cxn = DriverManager.getConnection("jdbc:sqlite:tasks.db");
            cxn.setAutoCommit(false);

            sqlStatement = cxn.createStatement();
            ResultSet rs = sqlStatement.executeQuery( "SELECT id, description FROM tasks;" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                tasks.add(new Task(id, description));
            }
            rs.close();
            sqlStatement.close();
            cxn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Record(s) retrieved successfully");
        return tasks;
    }
}
