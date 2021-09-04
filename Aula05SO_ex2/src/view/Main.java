package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCorredorController;

public class Main {

	public static Semaphore semaforo;
	
	public static void main(String[] args) {
		int tamCorredot = 200;
		String nomePessoa;
		semaforo = new Semaphore(1);
		
		for (int i = 0; i < 4; i++) {
			switch (i) {
				case 0:
					nomePessoa = "Luiz";
					break;
				case 1:
					nomePessoa = "Felipe";
					break;
				case 2:
					nomePessoa = "Lipe";
					break;
				case 3:
					nomePessoa = "Não tenho ideia de nome";
					break;
				default:
					nomePessoa = "Default";
			}
			Thread corredor = new ThreadCorredorController(nomePessoa, tamCorredot, semaforo);
			corredor.start();
		}
	}

}
