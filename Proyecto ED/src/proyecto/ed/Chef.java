package proyecto.ed;

import java.util.Stack;

public class Chef {
  public Stack<Hamburguesa> hamburguesasEnProceso;

    public Chef() {
        this.hamburguesasEnProceso = new Stack<>();
    }

    public void prepararHamburguesa(Hamburguesa hamburguesa) {
        hamburguesasEnProceso.push(hamburguesa);
    }
       public Hamburguesa completarHamburguesa() {
        return hamburguesasEnProceso.pop();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (!hamburguesasEnProceso.isEmpty()) {
            hamburguesasEnProceso.peek().getIngredientes().add(ingrediente);
        }
    }
        public void desecharHamburguesa() {
        if (!hamburguesasEnProceso.isEmpty()) {
            hamburguesasEnProceso.pop();
        }
    }
}
