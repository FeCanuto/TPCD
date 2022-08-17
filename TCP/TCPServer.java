package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import RMI.ServiceInterface;

/**
 * Trabalho final de Computação distribuida e paralela
 * @author Felipe Sousa Canuto dos Santos
 * RA: 171257375
 */


public class TCPServer {
	public static void main(String args[]) throws NotBoundException {
		try {
			int serverPort = 7896; // the server port
			try (ServerSocket listenSocket = new ServerSocket(serverPort)) { // Instanciando ServerSocket
				while (true) {
					Socket clientSocket = listenSocket.accept();
					new Connection(clientSocket);
				}
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	ServiceInterface services;

	public Connection(Socket aClientSocket) throws NotBoundException {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			System.out
					.println("Cliente >> " + clientSocket.getLocalAddress() + ":" + clientSocket.getLocalPort() + "\n"); // Info Client
			services = (ServiceInterface) Naming.lookup("rmi://localhost:9901/RMI"); // Realizando o lookup
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try {
			while (true) {
				// Dados enviados pelo Client
				String data = in.readUTF();
				System.out.println("Dados recebidos do TCPClient");
				// Armazenando dados do client em um vetor de String
				String[] args = data.split(" ");
				// Inicializando String resposta para o Client
				String resposta = "";
				Integer choice = Integer.valueOf(args[0]);

				switch (choice) {
					case 1:// servico para calcular média
						resposta += services.media(args);
						System.out.println("Recebido resposta do service média\n");
						break;
					case 2:// servico para calcular mediana
						resposta += services.mediana(args);
						System.out.println("Recebido resposta do service mediana\n");
						break;
					case 3:// servico para calcular moda
						resposta += services.moda(args);
						System.out.println("Recebido resposta do service moda\n");
						break;
					default:
						resposta += "O serviço solicitado não está disponível nessa aplicação\n";
						break;
				}
				out.writeUTF(">> " + resposta + "\n"); // resposta ao cliente
			}
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				/* close failed */}

		}
	}
}