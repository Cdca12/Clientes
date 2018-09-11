package clientes;

import java.util.Scanner;

/**
 *
 * @author Carlos Contreras
 */
public class Clientes {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numeroClientes = 3;
        Cliente clientes[] = new Cliente[numeroClientes];
        int ultimaPosicion = 0;
        int opcion = 0;

        do {
            System.out.println("Menú Principal\n"
                    + "\n1. Alta de Clientes."
                    + "\n2. Baja de Clientes."
                    + "\n3. Consultas."
                    + "\n4. Salir."
                    + "\nTeclee una opción del menú:\r");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1: // Alta de Clientes
                    if (ultimaPosicion == clientes.length - 1) {
                        System.out.println("El vector de clientes está lleno.");
                        break;
                    } else {
                        Cliente cliente = new Cliente();
                        System.out.println("Ingrese el nombre del cliente: ");
                        cliente.nombre = scanner.next();
                        System.out.println("Ingrese al edad del cliente: ");
                        cliente.edad = scanner.nextInt();
                        System.out.println("Ingrese el estado civil del cliente:"
                                + "\n\tS. Soltero."
                                + "\n\tC. Casado."
                                + "\n\tT. Divorciado. ");
                        // TODO: Validar que solo se ingreses esas letras.
                        cliente.estadoCivil = scanner.next().toLowerCase().charAt(0);
                        clientes[ultimaPosicion] = cliente;
                        ultimaPosicion++;
                        System.out.println("¡El cliente se ha añadido exitosamente!");
                    }
                    break;
                case 2: // Baja de Clientes

                    break;
                case 3: // Consultas

                    break;
                case 4: // Salir
                    System.out.println("Ha finalizado el programa correctamente.");
                    break;
                default:
                    System.out.println("Inserte un número del menú de opciones.");
            }

        } while (opcion != 4);

    }

}
