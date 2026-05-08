import java.rmi.*;

interface ServerInf extends Remote{
    public double Add(double num1, double num2) throws RemoteException;
    public double Sub(double num1, double num2) throws RemoteException;
}