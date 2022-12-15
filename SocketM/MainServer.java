package main;

import java.net.ServerSocket;

import code.Server;

public class MainServer 
{
    public static void main(String[] args) throws Exception
    {
        try 
        {
            ServerSocket serverSocket = new ServerSocket(1820);
            Server server = new Server(serverSocket);
            server.startServer();
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
}
