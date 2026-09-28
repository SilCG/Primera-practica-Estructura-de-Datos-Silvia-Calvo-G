import java.time.LocalDateTime;


public class Ticket {

    // Atributo estático: lo comparten todos los tickets, para que cada ID sea unico
    private static int cantidad = 0;

    // Atributos
    private int id;
    private String descripcion;
    private String nombreCompleto;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    // Métodos
    // Constructor
    public Ticket(String descripcion, String nombreCompleto){
        cantidad++;
        id = cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        fechaCreacion = LocalDateTime.now();
        fechaResolucion = null;
    }

    // Getters
    public static int getCantidad(){
        return cantidad;
    }
    public int getId(){
        return id;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getNombreCompleto(){
        return nombreCompleto;
    }
    public LocalDateTime getFechaCreacion(){
        return fechaCreacion;
    }
    public LocalDateTime getFechaResolucion(){
        return fechaResolucion;
    }

    // Setters
    public void setFechaResolucion(LocalDateTime fechaResolucion){
        this.fechaResolucion = fechaResolucion;
    }

    // toString
    @Override
    public String toString(){
        String resolucion;
        if (fechaResolucion == null){
            resolucion = "Pendiente";
        }
        else {
            resolucion = fechaResolucion.toString();
        }
        return "\nTicket #" + id + "\nUsuario: " + nombreCompleto + "\nDescripción: " + descripcion + "\nFecha de creación: " + fechaCreacion + "\nFecha de resolución: " + resolucion + "\n";
    }
}
