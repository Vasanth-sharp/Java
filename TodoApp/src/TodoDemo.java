import java.sql.*;
import java.util.Scanner;

public class TodoDemo {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("Enter your choice\n"+
                    "1 => Add task\n" +
                    "2 => Read tasks\n" +
                    "3 => Update tasks\n" +
                    "4 => Delete tasks\n" +
                    "0 => Exit");
            int choice= scanner.nextInt();
            switch (choice){
                case 1:
                    addTodo();
                    break;
                case 2:
                    readTasks();
                    break;
                case 3:
                    updateTasks();
                    break;
                case 4:
                    deleteTodo();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice..");
            }
        }
    }
    public static void addTodo() throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your Todo:");
        String todo=scanner.next();

        String url="jdbc:mysql://localhost:3306/todoapp";
        String user="root";
        String password="root";

        Connection connection= DriverManager.getConnection(url,user,password);
        PreparedStatement pst=connection.prepareStatement("insert into todolist values (?)");
        pst.setString(1,todo);
        int row=pst.executeUpdate();
        connection.close();
        if(row==1)
            System.out.println("Todo Succesfully added...");
        else
            System.out.println("Issue in Addding...");
    }

    public static void readTasks() throws Exception{
        String url="jdbc:mysql://localhost:3306/todoapp";
        String user="root";
        String pass="root";

        Connection connection=DriverManager.getConnection(url,user,pass);
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from todolist");
        while (resultSet.next()){
            System.out.println("* "+resultSet.getString(1));
        }
        connection.close();
    }

    public static void updateTasks() throws Exception{
        String url="jdbc:mysql://localhost:3306/todoapp";
        String user="root";
        String pass="root";

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a old todo: ");
        String old=scanner.next();
        System.out.println("Enter a new todo: ");
        String newt=scanner.next();

        Connection connection=DriverManager.getConnection(url,user,pass);
        PreparedStatement pst=connection.prepareStatement("update todolist set todo=? where todo=?");
        pst.setString(1,newt);
        pst.setString(2,old);
        int row=pst.executeUpdate();
        connection.close();
        if(row==1)
            System.out.println("Succesfully updated...");
        else
            System.out.println("Issue in updation...");
    }
    public static void deleteTodo() throws Exception{
        String url="jdbc:mysql://localhost:3306/todoapp";
        String user="root";
        String pass="root";

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a todo to delete: ");
        String todo=scanner.next();

        Connection connection=DriverManager.getConnection(url,user,pass);
        PreparedStatement pst=connection.prepareStatement("delete from todolist where todo=?");
        pst.setString(1,todo);
        int row=pst.executeUpdate();
        connection.close();
        if(row==1)
            System.out.println("Todo succesfully deleted...");
        else
            System.out.println("Issue in deletion..");
    }
}
