package clientes;

/**
 *
 * @author Carlos Contreras
 */
public class Cliente {

    int clave;
    String nombre;
    int edad;
    char estadoCivil;
    int siguiente;
    
    public String toString() {
        return "Clave: " + clave + 
                " | Nombre: " + nombre + 
                " | Edad: " + edad +
                " | Estado Civil: " + estadoCivil +
                " | Siguiente: " + siguiente; // Referencia
                
    }
    
}
