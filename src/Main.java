import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {

    static BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    static ColaPrioridad pendientes = new ColaPrioridad();
    static ListaEnlazadaSimple resueltos = new ListaEnlazadaSimple();

    public static void main(String[] args) throws IOException{

        int opcion = -1;
        while (opcion != 0){
            System.out.println(" --- Sistema de Tickets ---");
            System.out.println("1. Menú de usuario");
            System.out.println("2. Menu de Administrador");
            System.out.println("0. Salir");
            opcion = leerEntero ("Opción: ");
            switch (opcion){
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuAdministrador();
                    break;
                case 0:
                    System.out.println("Hasta Luego.");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // Menu
    public static void menuUsuario() throws IOException{
        int opcion = -1;
        while (opcion != 0){
            System.out.println(" --- Menu de Usuario ---");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket");
            System.out.println("0. Regresar");
            opcion = leerEntero ("Opción: ");
            switch (opcion){
                case 1:
                    crearTicket();
                    break;
                case 2:
                    buscarTicket();
                    break;
                case 0:
                    System.out.println();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void menuAdministrador() throws IOException {
        int opcion = -1;
        while (opcion != 0){
            System.out.println("\n--- Menu de Administrador ---");
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            switch (opcion){
                case 1:
                    verFrente();
                    break;
                case 2:
                    resolverTicket();
                    break;
                case 0:
                    System.out.println();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Opciones del usuario
    public static void crearTicket() throws IOException {
        System.out.print("Nombre completo: ");
        String nombre = lector.readLine().trim();
        System.out.print("Descripción del problema: ");
        String descripcion = lector.readLine().trim();

        if (nombre.isEmpty() || descripcion.isEmpty()){
            System.out.println("El nombre y la descripción no pueden estar vacíos.");
            return;
        }
        Ticket ticket = new Ticket(descripcion, nombre);
        pendientes.insertar(ticket);
        System.out.println("Ticket creado. Su número de ticket es: " + ticket.getId());
    }

    public static void buscarTicket() throws IOException {
        int id = leerEntero("Número de ticket a buscar: ");
        // Si el id nunca se asignó, el ticket no existe
        if (id < 1 || id > Ticket.getCantidad()){
            System.out.println("Ese ticket no existe.");
            return;
        }
        Ticket ticket = resueltos.buscar(id);
        if (ticket == null){
            System.out.println("El ticket #" + id + " está pendiente.");
        } else {
            System.out.println(ticket);
        }
    }

    // Opciones del administrador
    public static void verFrente(){
        Ticket ticket = pendientes.verFrente();
        if (ticket != null) System.out.println(ticket);
    }

    public static void resolverTicket(){
        Ticket ticket = pendientes.eliminar();
        if (ticket == null) return;
        ticket.setFechaResolucion(LocalDateTime.now());
        resueltos.insertarFinal(ticket);
        System.out.println("Ticket resuelto:" + ticket);
    }

    // Lee un número y vuelve a preguntar si el usuario escribe algo que no lo es
    public static int leerEntero(String mensaje) throws IOException {
        int numero = 0;
        boolean valido = false;
        while (!valido){
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(lector.readLine().trim());
                valido = true;
            } catch (NumberFormatException e){
                System.out.println("Debe ingresar un número.");
            }
        }
        return numero;
    }
}
