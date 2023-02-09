package Ejercicio2TCP;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GestorProcesos extends Thread {

    private Socket socket;
    private OutputStream os;
    private OutputStreamWriter osw;
    String mensajeRecibido;

    public GestorProcesos(String mensajeRecibido, Socket socket) {
        this.socket = socket;
        this.mensajeRecibido = mensajeRecibido;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que devuelve al cliente un String
     *
     * @throws IOException
     */
    public void realizarProceso() throws IOException {
        os = this.socket.getOutputStream();
        osw = new OutputStreamWriter(os);

        BufferedWriter bw = new BufferedWriter(osw);
        osw.write(leerFichero(mensajeRecibido));
        bw.newLine();
        bw.flush();
    }


    /**
     * Este método recibe como parámetro el string enviado por el cliente, luego lee el fichero buscando esa
     * dirección. Para ello se hace un split de las lineas leidas, y en caso de que encuentre esa direccion
     * devuelve la ip. En caso contrario devuelve un mensaje diciendo que no exista esa direccion.
     * Este método devuelve un String.
     *
     * @param direccion
     * @return
     */
    public static String leerFichero(String direccion) {

        BufferedReader br = null;
        String[] contenido;
        String ip = "";

        try {

            br = new BufferedReader(new FileReader("C:\\Users\\david\\IdeaProjects\\UDPHilos\\src\\Ejercicio2TCP\\DNS"));
            Scanner sc = new Scanner(br);

            while (sc.hasNext()) {
                contenido = sc.nextLine().split(":");
                if (contenido[0].contains(direccion)) {
                    ip = contenido[1];
                }
                if (ip.equals("")) {
                    ip = "Esa direccion no existe";
                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ip;
    }
}
