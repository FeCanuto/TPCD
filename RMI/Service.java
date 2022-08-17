package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Service extends UnicastRemoteObject implements ServiceInterface {

    protected Service() throws RemoteException {
        super();
    }

    @Override
    public String media(String[] a) throws RemoteException {
        System.out.println("\n");
        Integer acumulador = 0;
        // Integer[] media = new Integer[4];
        Integer[] newValueArray = new Integer[4];
        String media;

        // Realizando um cast de String para Integer e armazenando no newValueArray
        for (int i = 0; i < a.length - 1; i++)
            newValueArray[i] = Integer.valueOf(a[i + 1]);

        // Realizando a soma dos valores no array
        for (int i = 0; i < newValueArray.length; i++) {
            // media[i] = newValueArray[i];
            acumulador += newValueArray[i];
        }

        // Realizando o cálculo da média
        acumulador = acumulador / 4;
        media = String.valueOf(acumulador);
        // Retornando a resposta ao Server
        System.out.println("Serviço média realizado, retornando resposta ao TCPServer\n");
        return media;
    }

    @Override
    public String mediana(String[] a) throws RemoteException {

        Integer[] newValueArray = new Integer[5];
        String mediana;

        // Realizando um cast de String para Integer e armazenando no newValueArray
        for (int i = 0; i < a.length - 1; i++)
            newValueArray[i] = Integer.valueOf(a[i + 1]);

        int n = newValueArray.length;
        int temp = 0;

        // Ordenação Bubble sort
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (newValueArray[j - 1] > newValueArray[j]) {
                    // Trocar valores
                    temp = newValueArray[j - 1];
                    newValueArray[j - 1] = newValueArray[j];
                    newValueArray[j] = temp;
                }
            }
        }
        System.out.println("Serviço mediana realizado, retornando resposta ao TCPServer\n");
        mediana = String.valueOf(newValueArray[2]);
        return mediana;
    }

    @Override
    public String moda(String[] a) throws RemoteException {

        int maxValue = 0, maxCount = 0;
        Integer[] newValueArray = new Integer[4];
        String moda;

        // Realizando um cast de String para Integer e armazenando no newValueArray
        for (int i = 0; i < a.length - 1; i++)
            newValueArray[i] = Integer.valueOf(a[i + 1]);

        // Identificando o valor que mais se repete no array
        for (int i = 0; i < newValueArray.length; ++i) {
            int count = 0;
            for (int j = 0; j < newValueArray.length; ++j) {
                if (newValueArray[j] == newValueArray[i])
                    ++count;
            }

            if (count > maxCount) {
                maxCount = count;
                maxValue = newValueArray[i];
            }
        }
        System.out.println("Serviço moda realizado, retornando resposta ao TCPServer\n");
        moda = String.valueOf(maxValue);
        return moda;
    }

}
