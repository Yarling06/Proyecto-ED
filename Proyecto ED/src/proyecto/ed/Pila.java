/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.ed;

public class Pila<T> {
    private Nodo<T> cima;

    public void push(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T valor = cima.getValor();
        cima = cima.getSiguiente();
        return valor;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return cima.getValor();
    }

    public boolean isEmpty() {
        return cima == null;
    }
}
