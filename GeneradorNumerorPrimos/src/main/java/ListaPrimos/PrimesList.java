package ListaPrimos;

import java.util.ArrayList;

// Lista personalizada para almacenar solo números primos únicos.
public class PrimesList extends ArrayList<Integer> {

    // Verifica si un número es primo
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

    // Agrega números primos sin duplicados.
    @Override
    public synchronized boolean add(Integer number) {
        if (!isPrime(number)) {
            throw new IllegalArgumentException("Número no primo.");
        }
        if (this.contains(number)) {
            throw new IllegalArgumentException("Número ya existente.");
        }
        return super.add(number);
    }

    // Elimina solo si es número primo.
    @Override
    public synchronized boolean remove(Object number) {
        if (number instanceof Integer && isPrime((Integer) number)) {
            return super.remove(number);
        } else {
            throw new IllegalArgumentException("No se puede eliminar: no es primo.");
        }
    }

    // Devuelve la cantidad de primos almacenados
    public int getPrimesCount() {
        return this.size();
    }

}
