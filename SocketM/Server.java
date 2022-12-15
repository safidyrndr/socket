package code;

import java.net.ServerSocket;
import java.net.Socket;

import code.Chat;

public class Server 
{
    ServerSocket serverSocket;

    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    public void startServer() throws Exception
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try 
                {                
                    while (!serverSocket.isClosed()) 
                    {
                        Socket socket  = serverSocket.accept();
                        System.out.println("Nouveau client conncté !");
                        Chat chat = new Chat(socket);
                    
                        Thread thread = new Thread(chat);
                        thread.start();
                    }
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }  
        }).start();
    }

    public void closeServer() throws Exception
    {
        try 
        {
            if (serverSocket != null) 
            {
                serverSocket.close();
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
