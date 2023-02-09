package Ejercicio2TCP;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        try {
            Scanner sc= new Scanner(System.in);
            // 1 - Crear un socket de tipo cliente indicando IP y puerto del servidor
            System.out.println("(Cliente) Estableciendo conexión con el servidor");
            Socket cliente = new Socket("192.168.0.15", 49200);

            // 2 - Abrir flujos de lectura y escritura
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            System.out.println("(Cliente) Conexión establecida");
            System.out.println("Enviar datos:");
            System.out.println("Introduce una direccion");
            String direccion = sc.nextLine();

            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            osw.write(direccion);
            bw.newLine();
            bw.flush();

            // 3 - Intercambiar datos con el servidor

            // Leo mensajes que me envía el servidor
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br=new BufferedReader(ir);
            System.out.println("El servidor me envía el siguiente mensaje: " + br.readLine());

            // 4 - Cerrar flujos de lectura y escritura
            is.close();
            os.close();

            // 5 - Cerrar la conexión
            System.out.println("(Cliente) Cerrando conexiones...");
            cliente.close();
            System.out.println("(Cliente) Conexiones cerradas...");

        } catch (UnknownHostException e) {
            System.err.println("No se encuentra el host especificado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }
}
