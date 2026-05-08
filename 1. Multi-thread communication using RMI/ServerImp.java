import java.rmi.*;
import java.rmi.server.*;

public class ServerImp extends UnicastRemoteObject
    implements ServerInf {
        public ServerImp() throws RemoteException{}

        public double Add(double num1, double num2) throws RemoteException{
            return num1 + num2;
        }

        public double Sub(double num1, double num2) throws RemoteException{
            return num1 - num2;
        }
}
