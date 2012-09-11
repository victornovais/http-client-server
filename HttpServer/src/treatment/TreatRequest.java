package treatment;

import java.util.ArrayList;

public class TreatRequest {
	public ArrayList<String> splitWords(String request) {
		ArrayList<String> list = new ArrayList<String>();
		// Separando cada elemento da requisicao
		String[] breakedList = request.split(" ");
		
		// Adicionando-os à uma lista, para facilitar o tratamento
		for(String word : breakedList) {
			list.add(word);
		}
		return list;
	}
}
