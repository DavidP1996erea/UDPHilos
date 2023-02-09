package Ejercicio1UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class GestorProcesos extends Thread{
    DatagramSocket socket;
    DatagramPacket datagramaEntrada;
    int numeroAleatorio;


    public GestorProcesos(int numeroAleatorio,DatagramSocket socket, DatagramPacket datagramaEntrada) {
        super();
        this.socket = socket;
        this.datagramaEntrada = datagramaEntrada;
        this.numeroAleatorio=numeroAleatorio;
    }

    @Override
    public void run() {
        realizarProceso();
    }

    /**
     * Método que recoge el mensaje recibido y lo convierte en entero, luego se comprueba
     * que ese número sea el mismo que el generado aleatoriamente, en cada caso se le irá
     * informando al usuario si el número es mayor o menor.
     */
    public void realizarProceso() {



        // Recepción de mensaje del cliente
        String mensajeRecibido = new String(datagramaEntrada.getData()).trim();
        int numero = Integer.parseInt(mensajeRecibido);



        // 5 - Generación y envío de la respuesta
        System.out.println("(Ejercicio1UDP.Servidor): Enviando datagrama...");
        byte[] mensajeEnviado=null ;

        if(numero==numeroAleatorio){
            mensajeEnviado=  new String("Has adivinado el número").getBytes();

        } else if (numero<numeroAleatorio) {
            mensajeEnviado= new String("El numero es menor que el aleatorio").getBytes();
        }else if (numero>numeroAleatorio) {
            mensajeEnviado= new String("El numero es mayor que el aleatorio").getBytes();

        }
        DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length,
                datagramaEntrada.getAddress(), datagramaEntrada.getPort());
        try {
            socket.send(packetSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
