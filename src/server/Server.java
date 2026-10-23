package server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import model.Operation;
public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket();
        InetAddress localAddr=InetAddress.getByName("0.0.0.0");
        serverSocket.bind(new InetSocketAddress(localAddr,12345));
        System.out.println("Je suis un serveur en attente la connexion d'un client");
        Socket clientSocket=serverSocket.accept();
        System.out.println("un client est connecté");
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        while(true){
            Operation opObjet=(Operation) in.readObject();
            if(opObjet==null){
                System.out.println("Le client a demandé à se déconnecter");
                break;
            }
            int op1=opObjet.getOp1();
            String operateur=opObjet.getOp();
            int op2= opObjet.getOp2();
            System.out.println("Opération récue - Opération : "+op1+ " "+ operateur + " "+ op2);

            int res=0;
            if(operateur.equals("+")) res=op1+op2;
            else if (operateur.equals("-")) res=op1-op2;
            else if (operateur.equals("*")) res=op1*op2;
            else if (operateur.equals("/")) {
                if (op2 == 0) {
                    out.writeObject("Erreur: Division par zero");
                    out.flush();
                    continue;
                }
                res = op1 / op2;
            }
            out.writeObject(res);
            out.flush();
        }
        clientSocket.close();
        serverSocket.close();
        System.out.println("Serveur arrêté");
    }
}