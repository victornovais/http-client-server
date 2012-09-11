package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import treatment.TreatRequest;	

public class HttpServer {
	public static void main(String[] args) throws IOException {
		TreatRequest treat = new TreatRequest();

		final String BREAK_LINE = "\r\n";
		String fullRequest;

		ServerSocket servidor = new ServerSocket(9999);
		System.out.println("Porta aberta!");
		while (true) {
			Socket clientSocket = servidor.accept();
			System.out.println("Nova conexão com o cliente "
					+ clientSocket.getInetAddress().getHostAddress());

			//Aqui fazer verificação de segurança para pacotes maiores que
			// 1mb e usar o método shutDownInput/Output ou close para desligá-lo
			
			
			// Criando objetos que serão a entrada do cliente e a resposta do
			// server
			BufferedReader clientRequest = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			DataOutputStream serverResponse = new DataOutputStream(
					clientSocket.getOutputStream());

			// Lendo a requisição do nosso cliente (Apenas a primeira linha)
			fullRequest = clientRequest.readLine();

			if (fullRequest != null && (fullRequest.startsWith("GET"))
					|| (fullRequest.startsWith("POST"))
					|| (fullRequest.startsWith("PUT"))) {

				// Separando elementos da requisicao
				ArrayList<String> requestPieces = treat.splitWords(fullRequest);
				
				//Analisando a requisicao e escrevendo a resposta
				if (requestPieces.get(1).equals("/")) {
					// Tratando a versão do protocolo HTTP
					if (requestPieces.get(2).equals("HTTP/1.0")) {
						serverResponse.writeBytes("HTTP/1.0 200 OK"	+ BREAK_LINE);
						serverResponse.writeBytes("Connection: Close" + BREAK_LINE);
					} else {
						serverResponse.writeBytes("HTTP/1.1 200 OK"	+ BREAK_LINE);
					}
					serverResponse.writeBytes("Server: Java HTTP Server" + BREAK_LINE);
					serverResponse.writeBytes("Content-Lenght: " + serverResponse.size() + BREAK_LINE);
					serverResponse.writeBytes(BREAK_LINE);
					
					if (requestPieces.get(2).equals("HTTP/1.0")) {
						clientSocket.close();
					}
				}
				else {
					serverResponse.writeBytes("HTTP/1.0 404 Not Found");
					clientSocket.close();
				}
			}
		} 
	}
}
