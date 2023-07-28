
package proyecto.ed;

public class Utils {
     private static String generarTipoHamburguesaAleatoria() {
        // Lista de tipos de hamburguesas disponibles
        String[] tiposHamburguesa = {"Hamburguesa de carne", "Hamburguesa con queso", "Hamburguesa clásica"};
        
        // aqui se genera un índice aleatorio para tener el tipo de hamburguesa
        int indiceAleatorio = (int) (Math.random() * tiposHamburguesa.length);
        
        // se devuelve el tipo de hamburguesa generado aleatoriamente
        return tiposHamburguesa[indiceAleatorio];
    }

    private int obtenerPuntajeOrden(Orden orden) {
        // Obtener el puntaje de una orden completada
        String tipoHamburguesa = orden.getTipo();
        
        // Asignar el puntaje según el tipo de hamburguesa
        switch (tipoHamburguesa) {
            case "Hamburguesa de carne":
                return 5;
            case "Hamburguesa con queso":
                return 10;
            case "Hamburguesa clásica":
                return 15;
            default:
                return 0;
        }
    }

}
