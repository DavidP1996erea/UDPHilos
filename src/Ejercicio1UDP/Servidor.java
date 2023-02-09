package Ejercicio1UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Se genera el número aleatorio
            Random numerosAleatorios = new Random();
            int numeroAleatorio = numerosAleatorios.nextInt(100);
            // 1 - Crear DatagramSocket y le indicamos el puerto
            System.out.println("(Ejercicio1UDP.Servidor) Creando socket...");
            socket = new DatagramSocket(49201);

                while(true) {
                    // 2 - Crear array de bytes que actuará de buffer
                    byte[] buffer = new byte[64];

                    // 3 - Creación de datagrama con la clase DatagramPacket
                    DatagramPacket datagramaEntrada = new DatagramPacket(buffer, buffer.length);

                    // 4 - Recepción del datagrama mediante el método receive
                    System.out.println("(Ejercicio1UDP.Servidor) Esperando peticiones...");
                    socket.receive(datagramaEntrada);

                    // Se crea un objeto de esta clase y se le pasa los valores
                    new GestorProcesos(numeroAleatorio,socket, datagramaEntrada).start();
                }

            // 6 - Cierre del socket
//			System.out.println("(Ejercicio1UDP.Servidor): Cerrando la conexión...");
//			socket.close();
//			System.out.println("(Ejercicio1UDP.Servidor): Conexión cerrada");
//
        } catch (SocketException e) {
            System.out.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al recibir el paquete");
            e.printStackTrace();
        }

    }
}
