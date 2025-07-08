package GeneradorPrimos;

import ListaPrimos.ListaPrimos;

// Hilo que genera números primos en un rango y los agrega a la lista.
public class GeneradorPrimos extends Thread {

    private ListaPrimos listaPrimos;
    private int inicio;
    private int fin;
    
    // Constructor Vacío.
    public GeneradorPrimos() {

    }
    
    // Constructor.
    public GeneradorPrimos(ListaPrimos listaPrimos, int inicio, int fin) {
        this.listaPrimos = listaPrimos;
        this.inicio = inicio;
        this.fin = fin;
    }

    // Verifica si un número es primo (versión local)
    private boolean esPrimo(int number) {
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
    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                try {
                    listaPrimos.add(i); 
                } catch (IllegalArgumentException e) {
                    // Ignorar si no es válido o ya existe
                }
            }
        }
    }

}
