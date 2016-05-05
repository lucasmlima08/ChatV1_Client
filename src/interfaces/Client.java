/**
 * 
 */
package interfaces;

/**
 * @author Lucas M Lima
 *
 */
public interface Client {
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Método de abertura do cliente:
	 *  Inicia a conexão com o servidor.
	 *  Chama o método de autenticação.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void startConnection();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Método de autenticação e inicialização da comunicação do cliente:
	 *  Envia o identificador de autenticação.
	 *  Aguarda a resposta de aceitação do servidor.
	 *  Em caso de aceitação inicia as threads de requisições.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void authentication();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de recebimento de mensagens pelo usuário:
	 *  Realiza a leitura de novas requisições do usuário.
	 *  Inclui as requisições lidas na lista de requisições
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread processSystemMessages();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de recebimento de mensagens do servidor para o cliente:
	 *  Faz a leitura das requisições pelo socket do servidor.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread requestsReceived();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de envio de mensagens para outros clientes:
	 *  Faz o envio das requisições para o socket do servidor.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread requestSend();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Método de informação:
	 *  Exibe uma mensagem de informação.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void informationMessage(String string);
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Método de fechamento da comunicação do cliente:
	 *  Interrompe as threads e o socket de comunicação com o servidor.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void closeConnection();
}
