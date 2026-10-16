package hello;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
public class HelloWorld {
    private static Map<String, Integer> resgistroClientes = new HashMap<>();

    public static void main(String[] args) {
        int [] arr ={11,2,20,56,32,78};
        int port = 8080;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión aceptada IP del cliente " +
                        clientSocket.getInetAddress());

                manejarPeticion(clientSocket, arr);
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void manejarPeticion(Socket clientSocket, int [] data) {
        try (OutputStream output = clientSocket.getOutputStream()) {
           
    StringBuilder response = new StringBuilder();

    String recurso = "ABCD";

new Thread(() -> {
    try {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        //ejecucion 1 agregar 10 dolares a usuario 0
        System.out.println("Agregando dinero a usuario 0");
        synchronized (data) {
            data[0] = data[0]+10;
            System.out.println("Adicion de dinero a usuario 0 finalizada. Saldo: " + data[0]);
        }
        System.out.println("Ejecución en segundo plano terminada B" + recurso);
    } catch (Exception e) {
        e.printStackTrace();
    }
}).start();

new Thread(() -> {
    try {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        //ejecucion 2 retirar 20 dolares a usuario 0
        System.out.println("retiro de dinero a usuario 0");
        synchronized (data) {
            if(data[0]>=20){
                data[0] = data[0]-20;
                System.out.println("retiro de dinero finalizado. Saldo: " + data[0]);
            } else {
                System.out.println("Saldo insuficiente para retiro. Saldo: " + data[0]);
            }
        }
       
       
    } catch (Exception e) {
        e.printStackTrace();
    }
}).start();

new Thread(() -> {
    try {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        //ejecucion 3 agregar 20 dolares a usuario 1
        System.out.println("Agregando dinero a usuario 1");
        synchronized (data) {
            data[1] = data[1]+20;
            System.out.println("Adicion de dinero a usuario 1 finalizada. Saldo: " + data[1]);
            System.out.println("\n");
            System.out.println("\n");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}).start();

String respuesta = "Hola Cliente!\nSaldos: " + Arrays.toString(data) + "\n";
addHeader(response, respuesta.length());
response.append(respuesta);

output.write(response.toString().getBytes());
output.flush();
} catch (Exception e) {
    e.printStackTrace();
}
}


public static void addHeader(StringBuilder strBuilder, int contenidoLongitud) {
    strBuilder.append("HTTP/1.1 200 OK\r\n")
            .append("Content-Type: text/plain\r\n")
            .append("Content-Length: ").append(contenidoLongitud)
            .append("\r\n")
            .append("\r\n");
}
}