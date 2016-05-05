/**
 * 
 */
package interfaces;

import java.util.ArrayList;

/**
 * @author Lucas M Lima
 *
 */
public interface ClientRequest {
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Define o(s) cliente(s) que vão receber a requisição.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void setIdSend(int idSend);
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Retorna os clientes que vão receber a requisição.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public int getIdSend();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Define a mensagem que o(s) cliente(s) vão receber.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void setMessage(String message);
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Retorna a mensagem que o(s) cliente(s) vão receber.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public String getMessage();
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Define o(s) cliente(s) que vão receber a requisição.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public void setIdsClientsReceive(ArrayList<Integer> idsClientsReceived);
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  Retorna os clientes que vão receber a requisição.
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public ArrayList<Integer> getIdsClientsReceive();
}
