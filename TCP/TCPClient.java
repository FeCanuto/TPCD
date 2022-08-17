package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Trabalho final de Computação distribuida e paralela
 * @author Felipe Sousa Canuto dos Santos
 * RA: 171257375
 */


public class TCPClient {
	public static void main(String args[]) {
		Socket s = null;
		String option;
		int auxiliar;

		try (Scanner scanner = new Scanner(System.in)) {
			try {

				// Definindo a porta do TCP client
				int serverPort = 7896;
				// Instanciando socket TCP
				s = new Socket("localhost", serverPort);
				// Fluxo de entrada de dados
				DataInputStream in = new DataInputStream(s.getInputStream());
				// Fluxo de saída de dados
				DataOutputStream out = new DataOutputStream(s.getOutputStream());

				do {

					String vetor = "";

					// Serviços de média, mediana e moda disponíveis para resolução
					System.out.println("Serviços disponíveis: \n1 - Média\n2 - Mediana\n3 - Moda\n ");
					option = scanner.next();

					// Opção média selecionado
					if (option != null && option.equals("Media")) {
						vetor += "1 ";
						System.out.println("Insira 4 valores para calcular a média\n");
						for (int i = 1; i < 5; i++) {
							vetor += scanner.next() + " "; // concatenando a string vetor
						}

					// Opção Mediana selecionado
					} else if (option != null && option.equals("Mediana")) {
						System.out.println("Insira 5 valores para calcular a mediana\n");
						vetor += "2 ";
						for (int i = 1; i < 6; i++) {
							vetor += scanner.next() + " "; // concatenando a string vetor
						}

					// Opção Moda selecionado
					} else if (option != null && option.equals("Moda")) {
						System.out.println("Insira 4 valores para calcular a moda\n");
						vetor += "3 ";
						for (int i = 1; i < 5; i++) {
							vetor += scanner.next() + " "; // concatenando a string vetor
						}
					}

					if (option.length() > 1) {
						//envia fluxo de dados
						out.writeUTF(vetor); // UTF is a string encoding see Sn. 4.4
						String data = in.readUTF();
						System.out.println("recebido: " + data + "\n");
					}

					System.out.println("Deseja executar novamente ?");
					auxiliar = scanner.nextInt();
				} while (auxiliar == 0);

			} catch (UnknownHostException e) {
				System.out.println("Socket:" + e.getMessage());
			} catch (EOFException e) {
				System.out.println("EOF:" + e.getMessage());
			} catch (IOException e) {
				System.out.println("readline:" + e.getMessage());
			} finally {
				if (s != null)
					try {
						s.close();
					} catch (IOException e) {
						System.out.println("close:" + e.getMessage());
					}
			}
		}
	}
}
