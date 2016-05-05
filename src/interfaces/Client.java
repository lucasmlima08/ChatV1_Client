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
	 *  M�todo de abertura do cliente:
	 *  Inicia a conex�o com o servidor.
	 *  Chama o m�todo de autentica��o.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void startConnection();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  M�todo de autentica��o e inicializa��o da comunica��o do cliente:
	 *  Envia o identificador de autentica��o.
	 *  Aguarda a resposta de aceita��o do servidor.
	 *  Em caso de aceita��o inicia as threads de requisi��es.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void authentication();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de recebimento de mensagens pelo usu�rio:
	 *  Realiza a leitura de novas requisi��es do usu�rio.
	 *  Inclui as requisi��es lidas na lista de requisi��es
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread processSystemMessages();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de recebimento de mensagens do servidor para o cliente:
	 *  Faz a leitura das requisi��es pelo socket do servidor.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread requestsReceived();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Thread de envio de mensagens para outros clientes:
	 *  Faz o envio das requisi��es para o socket do servidor.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public Thread requestSend();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  M�todo de informa��o:
	 *  Exibe uma mensagem de informa��o.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void informationMessage(String string);
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  M�todo de fechamento da comunica��o do cliente:
	 *  Interrompe as threads e o socket de comunica��o com o servidor.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void closeConnection();
}
