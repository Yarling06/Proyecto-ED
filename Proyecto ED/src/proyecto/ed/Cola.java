package proyecto.ed;

import java.util.Iterator;

public class Cola<T> implements Iterable<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;

    public void encolar(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        if (isEmpty()) {
            inicio = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
        }
    }

    public T desencolar() {
        if (isEmpty()) {
            return null;
        }
        T valorDesencolado = inicio.getValor();
        inicio = inicio.getSiguiente();
        if (inicio == null) {
            fin = null;
        }
        return valorDesencolado;
    }

    public T frente() {
        if (isEmpty()) {
            return null;
        }
        return inicio.getValor();
    }

    public boolean isEmpty() {
        return inicio == null;
    }

    public int size() {
        int count = 0;
        Nodo<T> current = inicio;
        while (current != null) {
            count++;
            current = current.getSiguiente();
        }
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new ColaIterator();
    }

    private class ColaIterator implements Iterator<T> {
        private Nodo<T> current = inicio;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T valor = current.getValor();
            current = current.getSiguiente();
            return valor;
        }
    }
}
