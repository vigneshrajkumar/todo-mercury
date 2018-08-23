package Controller;

import Model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Datastore {

    public static void initializeStorage(){

        System.out.println("initializing storage");

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tasks.db");

            stmt = c.createStatement();
            String sql = "CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, description TEXT NOT NULL);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        System.out.println("Tables created successfully");
    }

    public static void addTask(Task task){

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tasks.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "insert into tasks ('description') values ('" + task.getDescription() + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Record created successfully");

    }

    public static void deleteTask(int id){

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tasks.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DELETE FROM tasks WHERE id = "+ id +";";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Record deleted successfully");

    }

    public static List<Task> getTasks(){

        List<Task> tasks = new ArrayList<>();

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tasks.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id, description FROM tasks;" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                tasks.add(new Task(id, description));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Record(s) retrieved successfully");
        return tasks;

    }
}
