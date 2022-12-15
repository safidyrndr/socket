package main;

import java.net.Socket;
import java.util.Scanner;

import code.Client;
import graph.Fenetre;

public class MainClient 
{
    public static void main(String[] args) throws Exception
    {
        try 
        {
            // Scanner scan = new Scanner(System.in);
            // System.out.println("Entrer votre nom pour le chat: ");
            // String userName = scan.nextLine();
            // Socket socket = new Socket("localhost", 1820);
            // Client client = new Client(socket, userName);
            // client.getMessage();
            // client.sendMessage();
            // System.out.println("OK");
            Fenetre f = new Fenetre();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
