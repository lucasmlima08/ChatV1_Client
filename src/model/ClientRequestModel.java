package model;

import java.util.ArrayList;

import interfaces.ClientRequest;

public class ClientRequestModel implements ClientRequest {
	
	private int idSend;
	private ArrayList<Integer> idsFriends;
	private String message;

	public ClientRequestModel(int idSend, String message, ArrayList<Integer> idsFriends) {
		this.idSend = idSend;
		this.message = message;
		this.idsFriends = idsFriends;
	}
	
	public void setIdSend(int idSend) {
		this.idSend = idSend;
	}

	public int getIdSend() {
		return idSend;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setIdsClientsReceive(ArrayList<Integer> idsClientsReceived) {
		this.idsFriends = idsClientsReceived;
	}

	public ArrayList<Integer> getIdsClientsReceive() {
		return idsFriends;
	}
}
