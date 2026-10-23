package client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.InetAddress;
public class Client {
    public static void main(String[] args) throws Exception {
        String serverIp="192.168.100.5";
        System.out.println("Je suis un client pas encore connecté...");
        Socket socket = new Socket(InetAddress.getByName(serverIp), 12345);
        System.out.println("Je suis un client connecté");
        Scanner scanner = new Scanner(System.in);
        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            System.out.println("Tapez une opération complète (Ex:55*25) ou 'quitter' ");
            String op = scanner.nextLine();
            out.println(op);
            if (op.equalsIgnoreCase("quitter")) {break;}
            String response = in.readLine();
            System.out.println("Résultat du serveur =" + response);
        }
        socket.close();
        scanner.close();
    }
}