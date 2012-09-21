package start;

import java.io.IOException;

import server.HttpServer;

public class Start {
	public static void main(String[] args) throws InterruptedException,	IOException {
		HttpServer newRequest = new HttpServer();

		while (true) {
		newRequest.runServer();
		}
	}
}
