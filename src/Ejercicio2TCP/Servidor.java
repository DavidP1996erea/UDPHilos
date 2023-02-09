package Ejercicio2TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            // 1 - Crear socket de tipo servidor y le indicamos el puerto
            ServerSocket servidor = new ServerSocket(49200);
            InputStream is;
            OutputStream os;
            OutputStreamWriter osw ;
            BufferedReader br;
            InputStreamReader ir;
            String mensajeRecibido;
            Socket peticion;

            while (true) {
                peticion = servidor.accept();
                // 3 - Abrir flujos de lectura y escritura de datos
               is=peticion.getInputStream();

                 ir = new InputStreamReader(is);
                 br= new BufferedReader(ir);

                mensajeRecibido = br.readLine();
                new GestorProcesos(mensajeRecibido, peticion).start();
            }


        } catch (IOException e) {
            System.err.println("Ha habido algún error en la creación del Socket Servidor");
            e.printStackTrace();
        }
    }
}
