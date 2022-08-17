package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Interface Remota com os métodos
public interface ServiceInterface extends Remote{
    public String media(String[] a) throws RemoteException;
    public String mediana(String[] a) throws RemoteException;
    public String moda(String[] a) throws RemoteException;
}
