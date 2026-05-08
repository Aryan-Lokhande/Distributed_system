import java.rmi.*;

public class Server {

    public static void main(String[] args) {
        try{
            ServerImp obj = new ServerImp();
            Naming.rebind("Server", obj);

            System.out.println("Server Starderd....");
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }

    }
}
