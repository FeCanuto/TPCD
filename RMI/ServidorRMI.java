package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {

    public ServidorRMI() throws RemoteException{
        
        ServiceInterface engine = new Service();   //instanciando os servicos
        Registry registry = LocateRegistry.createRegistry(9901); // Criando registro RMI
        System.out.println("RMI funcionando na porta: "+9901);
        registry.rebind("RMI", engine);              //associando nome à instancia
        System.out.println("Endereço RMI: \""+"rmi://localhost:9901"+"/"+(registry.list()[0])+"\"");        
    }

    public static void main(String[] args) throws RemoteException {
        new ServidorRMI();
    }
}
