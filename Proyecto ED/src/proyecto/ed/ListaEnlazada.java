/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.ed;
public class ListaEnlazada<T> {
    private Nodo<T> inicio;

    public void agregarAlInicio(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        nuevoNodo.setSiguiente(inicio);
        inicio = nuevoNodo;
    }

    public void agregarAlFinal(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        if (isEmpty()) {
            inicio = nuevoNodo;
        } else {
            Nodo<T> actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    public boolean eliminar(T valor) {
        if (isEmpty()) {
            return false;
        }
        if (inicio.getValor().equals(valor)) {
            inicio = inicio.getSiguiente();
            return true;
        }
        Nodo<T> actual = inicio;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getValor().equals(valor)) {
            actual = actual.getSiguiente();
        }
        if (actual.getSiguiente() != null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            return true;
        }
        return false;
    }

    public boolean buscar(T valor) {
        Nodo<T> actual = inicio;
        while (actual != null) {
            if (actual.getValor().equals(valor)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean isEmpty() {
        return inicio == null;
    }
}
