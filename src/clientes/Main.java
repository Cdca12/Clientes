package clientes;

import java.util.Scanner;

/**
 *
 * @author Carlos Contreras
 */
public class Main {

    static int posicionVector = 0;
    static int datoMenor = 0;
    static int datoMayor = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numeroClientes = 5;
        Cliente[] clientes = new Cliente[numeroClientes];

        int opcion = 0;

        do {
            System.out.println("Menú Principal\n"
                    + "\n1. Alta de Clientes."
                    + "\n2. Baja de Clientes."
                    + "\n3. Consultas."
                    + "\n4. Salir."
                    + "\nIngrese una opción del menú:\r");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1: // Alta de Clientes
                    if (posicionVector == clientes.length) {
                        System.out.println("El vector de clientes está lleno.\n");
                        break;
                    } else {
                        Cliente cliente = new Cliente();
                        System.out.println("Ingrese la clave del cliente: ");

                        int claveAux = scanner.nextInt();
                        while (existeClave(clientes, claveAux)) {
                            System.out.println("Esta clave ya existe. Favor de ingresar una diferente.\r");
                            claveAux = scanner.nextInt();
                        }
                        cliente.clave = claveAux;

                        System.out.println("Ingrese el nombre del cliente: ");
                        String nombreAux = scanner.next().toLowerCase();

                        // TODO: Si ya estaba un nombre y se dió de baja, que se pueda
                        while (existeNombre(clientes, nombreAux)) {
                            System.out.println("Este nombre ya está registrado. Favor de ingresar uno diferente.\r");
                            nombreAux = scanner.next().toLowerCase();
                        }

                        cliente.nombre = nombreAux.toUpperCase().charAt(0) + nombreAux.substring(1);

                        System.out.println("Ingrese la edad del cliente: ");
                        cliente.edad = scanner.nextInt();
                        System.out.println("Ingrese el estado civil del cliente:"
                                + "\n\tS. Soltero."
                                + "\n\tC. Casado."
                                + "\n\tT. Divorciado. ");
                        char estadoCivilAux = scanner.next().toLowerCase().charAt(0);

                        while (!(estadoCivilAux == 's' || estadoCivilAux == 'c' || estadoCivilAux == 't')) {
                            System.out.println("Favor de ingresar una letra del menú (S/C/T).");
                            estadoCivilAux = scanner.next().toLowerCase().charAt(0);
                        }
                        cliente.estadoCivil = Character.toUpperCase(estadoCivilAux);
                        // Se añade cliente
                        añadirCliente(clientes, cliente);
                        posicionVector++;
                        System.out.println("¡El cliente se ha añadido exitosamente!\n");
                    }
                    break;
                case 2: // Baja de Clientes
                    System.out.println("Ingrese el nombre del cliente que desea dar de baja: ");
                    String nombreAux = scanner.next();
                    darDeBaja(clientes, nombreAux);
                    System.out.println("¡El cliente se ha dado de baja correctamente!\n");
                    break;
                case 3: // Consultas
                    System.out.println(
                            "\nSeleccione cómo desea consultar:"
                            //                            + "\n1. Catálogo ascendente clientes por clave."
                            + "\n1. Catálogo ascendente clientes."
                            + "\n2. Consulta por x cliente."
                            + "\n3. Menú anterior");
                    int opcionAux = scanner.nextInt();
                    switch (opcionAux) {
                        case 1:
                            catalogoAscendenteClientesPorClave(clientes);
                            break;
                        case 2:
                            System.out.println("Ingrese el nombre del cliente que desee consultar: ");
                            nombreAux = scanner.next();
                            consultaPorCliente(clientes, nombreAux);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Favor de insertar un número del menú de opciones.");
                    }
                    break;
                case 4: // Salir
                    System.out.println("Ha finalizado el programa correctamente.");
                    break;
                default:
                    System.out.println("Favor de insertar un número del menú de opciones.");
            }

        } while (opcion != 4);

    }

    public static void añadirCliente(Cliente[] clientes, Cliente cliente) {
        // Calcula el siguiente

        // Caso 1: Primer dato
        if (posicionVector == 0) {
            cliente.siguiente = -1;
        } // Caso 2: Nuevo dato menor que el menor
        else if (cliente.nombre.compareTo(clientes[datoMenor].nombre) < 0) {
            cliente.siguiente = datoMenor;
            datoMenor = posicionVector;
        } // Caso 3: Nuevo dato mayor que todos
        else if (cliente.nombre.compareTo(clientes[datoMayor].nombre) > 0) {
            cliente.siguiente = -1;
            clientes[datoMayor].siguiente = posicionVector;
            datoMayor = posicionVector;
        } // Caso 4: Dato entre 2
        else {
            int posicionAnterior = 0;
            for (int i = datoMenor; i != -1; i = clientes[i].siguiente) {
                if (cliente.nombre.compareTo(clientes[i].nombre) < 0) {
                    cliente.siguiente = clientes[posicionAnterior].siguiente;
                    clientes[posicionAnterior].siguiente = posicionVector;
                    break;
                }
                posicionAnterior = i;
            }
        }

        // Añadir cliente al vector
        clientes[posicionVector] = cliente;

    }

    public static boolean existeNombre(Cliente[] clientes, String nombre) {
        for (int i = 0; i < posicionVector; i++) {
            if (clientes[i].nombre.toLowerCase().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public static boolean existeClave(Cliente[] clientes, int clave) {
        for (int i = 0; i < posicionVector; i++) {
            if (clientes[i].clave == clave) {
                return true;
            }
        }
        return false;
    }

    public static void catalogoAscendenteClientesPorClave(Cliente[] clientes) {
        System.out.println("");
        for (int i = datoMenor; i != -1; i = clientes[i].siguiente) {
            System.out.println(clientes[i].toString());
        }
        System.out.println("");
    }

    public static void consultaPorCliente(Cliente[] clientes, String nombre) {
        System.out.println("");
        for (int i = datoMenor; i != -1; i = clientes[i].siguiente) {
            if (clientes[i].nombre.equalsIgnoreCase(nombre)) {
                System.out.println(clientes[i].toString());
                break;
            }
        }
        System.out.println("");
    }

    public static void darDeBaja(Cliente[] clientes, String nombre) {
        int posicionAnterior = 0;

        if (clientes[datoMenor].nombre.equalsIgnoreCase(nombre)) {
            datoMenor = clientes[datoMenor].siguiente;
        }

        for (int i = datoMenor; i != -1; i = clientes[i].siguiente) {
            if (clientes[i].nombre.equalsIgnoreCase(nombre)) {
                clientes[posicionAnterior].siguiente = clientes[i].siguiente;
                break;
            } else {
                posicionAnterior = i;
            }
        }

//        datoMenor = posicionVector;
    }

}
