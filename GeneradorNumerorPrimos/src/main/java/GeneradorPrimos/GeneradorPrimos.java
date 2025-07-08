package GeneradorPrimos;

import ListaPrimos.PrimesList;
import java.util.ArrayList;
import java.util.List;

// Hilo que genera números primos en un rango y los agrega a la lista.
public class GeneradorPrimos extends Thread {

    private PrimesList listaPrimos;
    private int inicio;
    private int fin;
    private List<Integer> propios = new ArrayList<>(); // Lista local

    // Constructor Vacío.
    public GeneradorPrimos() {

    }

    // Constructor.
    public GeneradorPrimos(PrimesList listaPrimos, int inicio, int fin) {
        this.listaPrimos = listaPrimos;
        this.inicio = inicio;
        this.fin = fin;
    }

    // Devuelve los primos encontrados por este hilo
    public List<Integer> getPrimes() {
        return propios;
    }

    // Verifica si un número es primo (versión local)
    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Se agregara solo si es primo y no duplicado.
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            if (isPrime(i)) {
                try {
                    listaPrimos.add(i); // Agrega al total
                    propios.add(i);    // Guarda como encontrado por este hilo
                } catch (IllegalArgumentException e) {
                    // Ignorar duplicados
                }
            }
        }
        System.out.println(getName() + " finalizó con " + propios.size() + " primos encontrados.");
    }

}
