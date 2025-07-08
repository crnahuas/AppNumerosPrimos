package com.crnahuas.main;

import GeneradorPrimos.PrimeGenerator;
import ListaPrimos.PrimesList;

// Clase principal que inicia varios hilos y muestra resultados.
public class PrimeNumbersApp {

    public static void main(String[] args) {
       PrimesList primesList = new PrimesList(); 
       
        // Crear hilos con distintos rangos
        PrimeGenerator h1 = new PrimeGenerator(primesList, 1, 1000);
        PrimeGenerator h2 = new PrimeGenerator(primesList, 1001, 2000);
        PrimeGenerator h3 = new PrimeGenerator(primesList, 2001, 3000);
        
        // Asignar nombres a los hilos
        h1.setName("Hilo 1");
        h2.setName("Hilo 2");
        h3.setName("Hilo 3");

        // Iniciar hilos.
        System.out.println("Iniciando " + h1.getName());
        h1.start();

        System.out.println("Iniciando " + h2.getName());
        h2.start();

        System.out.println("Iniciando " + h3.getName());
        h3.start();
        
        // Esperar que todos los hilos terminen.
        try {
            h1.join();
            h2.join();
            h3.join();
        } catch (InterruptedException e) {
            System.out.println("Error al esperar la finalización de los hilos.");
        }
        
        // Resumen final del sistema
        System.out.println("\n=== PrimeSecure: Números Primos ===");
        System.out.println("Total de primos únicos encontrados: " + primesList.getPrimesCount());

        // Resumen individual por hilo
        mostrarResumenHilo(h1);
        mostrarResumenHilo(h2);
        mostrarResumenHilo(h3);

        // Mostrar los primeros 20 globales
        System.out.println("\nPrimeros 20 códigos primos únicos:");
        for (int i = 0; i < Math.min(20, primesList.size()); i++) {
            System.out.println(primesList.get(i));
        }
        
    }
    
    // Método para mostrar resumen de un hilo
    private static void mostrarResumenHilo(PrimeGenerator hilo) {
        System.out.println("\n" + hilo.getName() + ": encontró " + hilo.getPrimes().size() + " primos.");
        System.out.println("Primeros 10 encontrados:");
        for (int i = 0; i < Math.min(10, hilo.getPrimes().size()); i++) {
            System.out.println(hilo.getPrimes().get(i));
        }
    }
    
}
