public class ListaEnlazadaSimple {

    // Atributos de la lista
    private NodoLista primero;

    // Métodos
    // Constructor
    public ListaEnlazadaSimple(){
        primero = null;
    }

    // Operaciones
    private boolean estaVacia(){
        return primero == null;
    }
    public void insertarFinal(Ticket ticket){
        NodoLista nodo = new NodoLista(ticket);
        // Si la lista estuviera vacía, el nodo nuevo sería el primero y se retorna temprano
        if (estaVacia()){
            primero = nodo;
            return;
        }

        // Si no es el caso, se recorre hasta el ultimo y a ese se le pone el nuevo como siguiente
        NodoLista temp = primero;
        while (temp.getSiguiente() != null) temp = temp.getSiguiente();
        temp.setSiguiente(nodo);
    }

    public Ticket buscar(int id){
        NodoLista temp = primero;
        while (temp != null){
            if (temp.getTicket().getId() == id) return temp.getTicket();
            temp = temp.getSiguiente();
        }
        return null;
    }

    private class NodoLista {
        // Atributos
        private Ticket ticket;
        private NodoLista siguiente;

        // Métodos
        // Constructor
        public NodoLista(Ticket ticket){
            this.ticket = ticket;
            siguiente = null;
        }

        // Getters
        public Ticket getTicket(){
            return ticket;
        }
        public NodoLista getSiguiente(){
            return siguiente;
        }

        // Setters
        public void setSiguiente(NodoLista siguiente){
            this.siguiente = siguiente;
        }
    }
}
