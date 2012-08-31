package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {
	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(9998); // Qual porta seria interessante usar ?
		System.out.println("Porta aberta!");

		Socket usuario = servidor.accept();
		System.out.println("Nova conexão com o cliente "
				+ usuario.getInetAddress().getHostAddress());

		Scanner mensagem = new Scanner(usuario.getInputStream());
		while(mensagem.hasNextLine()){
			System.out.println(mensagem.nextLine());
		}
		
		System.out.println("Porta fechada!");
		servidor.close();
	}
}
