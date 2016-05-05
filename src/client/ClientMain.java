package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import interfaces.Client;
import model.ClientRequestModel;

public class ClientMain implements Client {
	
	private String serverIP;
	private int serverPort;
	private Socket client;
	private int idClient; 
	private boolean clientConnected;
	private ArrayList<ClientRequestModel> requestsReceived, requestsSend;

	public ClientMain() {
		serverIP = "192.168.15.2";
		serverPort = 9292;
		requestsReceived = new ArrayList<ClientRequestModel>();
		requestsSend = new ArrayList<ClientRequestModel>();
	}

	public static void main(String[] args) {
		ClientMain client = new ClientMain();
		client.startConnection();
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  M�todo de abertura do cliente.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void startConnection() {
		try {
			idClient = 1552;
			client = new Socket(serverIP, serverPort);
			clientConnected = true;
			authentication();
			processSystemMessages().start();
		} catch (Exception e) {
			informationMessage("Erro ao abrir conex�o com o servidor: " + e.getMessage());
		}
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  M�todo de autentica��o e inicializa��o da comunica��o do cliente.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void authentication() {
		try {
			boolean available = false;
			// Envia o Id do Cliente.
			PrintStream printStream = new PrintStream(client.getOutputStream());
			// Aguarda a resposta do servidor.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
			while (!available) {
				printStream.println(idClient);
				informationMessage("Identificador enviado: " + idClient);
				String read = bufferedReader.readLine();
				// Verifica se o usu�rio foi rejeitado pelo servidor.
				if (read.equals("accepted")){
					informationMessage("Cliente aceito pelo servidor!");
					//processSystemMessages().start();
					requestsReceived().start();
					requestSend().start();
					available = true;
				}
				// Verifica se o cliente foi rejeitado pelo servidor.
				if (read.equals("blocked")){
					informationMessage("Cliente recusado pelo servidor!");
					closeConnection();
					available = true;
				}
			}
		} catch (Exception e) {
			informationMessage("Houve um erro ao enviar a requisi��o recebida: " + e.getMessage());
		}
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de recebimento de mensagens pelo usu�rio.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread processSystemMessages() {
		Thread processSystem = new Thread(new Runnable() {
			public void run() {
				try {
					/*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
					while (clientConnected) {
						// Faz a leitura das requisi��es enviadas pelo cliente e joga na lista.
						String read = bufferedReader.readLine();
						if (!read.equals("")){
							ClientRequestModel request = new ClientRequestModel(null, read);
							requestsReceived.add(request);
						}
					}*/
					requestsSend.add(new ClientRequestModel(idClient, "Tudo bom!!!", null));
					requestsSend.add(new ClientRequestModel(idClient, "Hello!", null));
					requestsSend.add(new ClientRequestModel(idClient, "Oieee", null));
				} catch (Exception e) {
					informationMessage("Houve um erro ao ler a requisi��o do cliente: " + e.getMessage());
				}
			}
		});
		return processSystem;
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de recebimento de mensagens do servidor para o cliente.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread requestsReceived() {
		Thread send = new Thread(new Runnable() {
			public void run() {
				try {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
					while (clientConnected) {
						// Faz a leitura das requisi��es enviadas pelo cliente e joga na lista.
						String read = bufferedReader.readLine();
						if (!read.equals("")){
							ClientRequestModel request = new ClientRequestModel(0, read, null);
							requestsReceived.add(request);
						}
					}
				} catch (Exception e) {
					informationMessage("Houve um erro ao ler a requisi��o do cliente: " + e.getMessage());
				}
			}
		});
		return send;
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de envio de mensagens para outros clientes.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread requestSend() {
		Thread received = new Thread(new Runnable() {
			public void run() {
				try {
					PrintStream printStream = new PrintStream(client.getOutputStream());
					while (clientConnected) {
						ArrayList<ClientRequestModel> requestsClone = new ArrayList<>(requestsSend);
						int index = 0;
						// Percorre as requisi��es recebidas e envia para o cliente.
						for (ClientRequestModel request: requestsClone) {
							printStream.println(request.getMessage());
							informationMessage("Requisi��o Enviada: " + request.getMessage());
							requestsSend.remove(index);
						}
					}
				} catch (Exception e) {
					informationMessage("Houve um erro ao enviar a requisi��o recebida: " + e.getMessage());
				}
			}
		});
		return received;
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Retorna a mensagem que o(s) cliente(s) v�o receber.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void informationMessage(String string) {
		System.out.println(string);
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  M�todo de fechamento da comunica��o do cliente.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void closeConnection() {
		informationMessage("Conex�o fechada do cliente!");
		clientConnected = false;
	}
}
