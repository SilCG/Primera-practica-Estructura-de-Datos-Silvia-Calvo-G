import java.util.ArrayList;

public class ColaPrioridad {

    // Atributos
    private ArrayList<Ticket> cola;

    // Métodos
    public ColaPrioridad(){
        cola = new ArrayList<>();
    }

    // Operaciones
    private boolean estaVacia(){
        return cola.isEmpty();
    }

    // Criterio de prioridad: el ticket más antiguo (creado primero) tiene mayor prioridad
    private boolean tieneMayorPrioridad(Ticket nuevo, Ticket existente){
        return nuevo.getFechaCreacion().isBefore(existente.getFechaCreacion());
    }

    // Se busca la posición que le toca al ticket según su prioridad y se inserta ahí
    public void insertar(Ticket ticket){
        int posicion = 0;
        while (posicion < cola.size() && !tieneMayorPrioridad(ticket, cola.get(posicion))){
            posicion++;
        }
        cola.add(posicion, ticket);
    }

    public Ticket eliminar(){
        if (estaVacia()){
            System.out.println("No hay tickets pendientes.\n");
            return null;
        }
        return cola.removeFirst();
    }

    public Ticket verFrente(){
        if (estaVacia()){
            System.out.println("No hay tickets pendientes.\n");
            return null;
        }
        return cola.getFirst();
    }
}