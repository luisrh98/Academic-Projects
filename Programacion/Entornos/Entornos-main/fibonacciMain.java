package es.iessoterohernandez.daw.endes.fibonacciMain;

import java.util.Scanner;

import es.iessoterhernandez.daw.endes.fibonacci.Fibonacci;

public class fibonacciMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Ingrese un número para generar la secuencia de Fibonacci: ");
			int numero = scanner.nextInt();

			int[] resultado = Fibonacci.fibonacciHastaNumero(numero);

			System.out.print("Secuencia de Fibonacci hasta " + numero + ": ");
			for (int num : resultado) {
				System.out.print(num + " ");
			}

		} catch (java.util.InputMismatchException e) {
			System.out.println("Por favor, ingrese un número válido.");
		} finally {
			scanner.close();
		}
	}
}
