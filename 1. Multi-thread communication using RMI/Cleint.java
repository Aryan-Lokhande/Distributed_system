import java.rmi.*;
import java.util.Scanner;

public class Cleint {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String serverURL = "rmi://localhost/Server";
            ServerInf obj = (ServerInf)Naming.lookup(serverURL);
            System.out.println("Enter you first number: ");
            double num1 = sc.nextDouble();
            System.out.println("Enter you Second number: ");
            double num2 = sc.nextDouble();

            System.out.println("\n***************** Result *********************\n");
            System.out.println("Addintion: " + obj.Add(num1,num2));
            System.out.println("Addintion: " + obj.Sub(num1,num2));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
    
}
