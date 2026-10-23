package server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket();
        InetAddress localAddr=InetAddress.getByName("0.0.0.0");
        serverSocket.bind(new InetSocketAddress(localAddr,12345));
        System.out.println("Je suis un serveur en attente la connexion d'un client");
        Socket clientSocket=serverSocket.accept();
        System.out.println("un client est connecté");
        BufferedReader in =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);
        while(true){
            String op=in.readLine();
            if(op==null || op.equalsIgnoreCase("quitter")){
                break;
            }
            System.out.println("Opération récue : "+op);
            String[] parties=op.split(" ");
            int op1=Integer.parseInt(parties[0]);
            String operateur=parties[1];
            int op2= Integer.parseInt(parties[2]);
            int res=0;
            if(operateur.equals("+")) res=op1+op2;
            else if (operateur.equals("-")) res=op1-op2;
            else if (operateur.equals("*")) res=op1*op2;
            else if (operateur.equals("/")) res=op1/op2;
            out.println(res);
        }
        clientSocket.close();
        serverSocket.close();
    }
}