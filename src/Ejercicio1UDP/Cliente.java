package Ejercicio1UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        DatagramSocket socket = null;
        int puertoServidor = 49201;
        String nombreServidor = "localhost";
        Scanner sc = new Scanner(System.in);
        String numero;
        String mensajeRecibido="";
        try {
            // 1 - Obtención de la dirección del servidor usando el métod getByName de
            // InetAddress
            System.out.println("(Ejercicio1UDP.Cliente): Estableciendo parámetros de conexión...");
            InetAddress direccionServidor = InetAddress.getByName(nombreServidor);

            // 2 - Creación del socket UDP
            System.out.println("(Ejercicio1UDP.Cliente): Creando el socket...");
            socket = new DatagramSocket();

            // 3 - Generación del datagrama
            System.out.println("(Ejercicio1UDP.Cliente): Creando datagrama...");

            /**
             * Mientras el mensaje recibido sea el de que ha acertado, se hará un bucle
             * pidiendo al usuario un número que será enviado al servidor para
             * comprobar si es el mismo que el generado aleatoriamente.
             */
            while (!mensajeRecibido.contains("Has adivinado el número")){
                System.out.println("Introduzca un número:");
                numero = sc.nextLine().trim();

                byte[] bufferSalida = numero.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor,
                        puertoServidor);

                // 4 - Envío del datagrama a través de send
                System.out.println("(Ejercicio1UDP.Cliente) Enviando datagrama...");
                socket.send(paqueteSalida);


                System.out.println("(Ejercicio1UDP.Cliente) Recibiendo respuesta...");
                byte[] bufferEntrada = new byte[64];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, direccionServidor,
                        puertoServidor);
                socket.receive(paqueteEntrada);
                mensajeRecibido = new String(bufferEntrada).trim();
                System.out.println(mensajeRecibido);

            }

            System.out.println("Has acertado");
            socket.close();




            // 6 - Cierre del socket
            System.out.println("(Ejercicio1UDP.Cliente): Cerrando conexión...");
            socket.close();
            System.out.println("(Ejercicio1UDP.Cliente): Conexión cerrada.");

        } catch (SocketException e) {
            System.err.println("Error al conectar con el servidor.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("No se ha podido enviar o recibir el paquete");
            e.printStackTrace();
        }

    }
}
