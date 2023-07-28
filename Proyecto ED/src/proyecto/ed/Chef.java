
package proyecto.ed;

public class Chef {

    private String nombre;
    private int puntajeFinal;

    public Chef(String nombre) {
        this.nombre = nombre;
        this.puntajeFinal =0;
    }

    public void prepararOrden(Orden orden) {
      if (orden.isCompletada()){
          int puntajeOrden = calcularPuntajeOrden(orden);
          puntajeFinal+=puntajeOrden;
          System.out.println(nombre+"preparo la orden"+orden.getTipo()+
                  "Puntaje total"+puntajeOrden);
      }else{
          System.out.println("La orden"+orden.getTipo()+ "se encuentra incompleta");
      }
    }
    
    
    private int calcularPuntajeOrden(Orden orden) {
        int puntaje = 0;
        switch (orden.getTipo()) {
            case "Hamburguesa de carne":
                puntaje = 5;
                break;
            case "Hamburguesa con queso":
                puntaje = 10;
                break;
            case "Hamburguesa clásica":
                puntaje = 15;
                break;
        }
        return puntaje;
    }
    
    public int getPuntajeFinal(){
        return puntajeFinal;
    }
}


