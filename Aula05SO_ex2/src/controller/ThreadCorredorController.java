package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCorredorController extends Thread {
	
	private String nomePessoa;
	private int tamCorredor;
	private Semaphore semaforo;
	
	public ThreadCorredorController(String nomePessoa, int tamCorredor, Semaphore semaforo) {
		this.nomePessoa = nomePessoa;
		this.tamCorredor = tamCorredor;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		pessoaAnda();
	}

	private void pessoaAnda() {
		Random geraPasso = new Random();
		int passo = 0;
		
		do {
			try {
				Thread.sleep(1000);
				passo += geraPasso.nextInt((6 - 4) + 1) + 4;
				if (passo >= tamCorredor) {
					passo = tamCorredor;
					System.err.println("A pessoa chamada "+ nomePessoa +" chegou na porta");
				}
				System.out.println("A pessoa chamada "+ nomePessoa +" caminhou "+ passo +"m");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while(passo < tamCorredor);
		atravessaPorta();
	}

	private void atravessaPorta() {
		Random geraTempo = new Random();
		
		try {
			semaforo.acquire();
			Thread.sleep((geraTempo.nextInt(2) + 1) * 1000);
			System.err.println("A pessoa chamada "+ nomePessoa +" passou pela porta");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
}
