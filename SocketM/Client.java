package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String userName;

///getters & setters
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

///constructor
    public Client(Socket socket, String userName) throws Exception
    {
        try 
        {
            this.setSocket(socket);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.getSocket().getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
            this.setUserName(userName);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

///function
    public void sendMessage() throws Exception
    {
        try 
        {
            this.getBufferedWriter().write(getUserName());
            this.getBufferedWriter().newLine();
            this.getBufferedWriter().flush();

            Scanner scan = new Scanner(System.in);
            while (getSocket().isConnected()) 
            {
                String message = scan.nextLine();
                getBufferedWriter().write(getUserName()+": "+message);
                getBufferedWriter().newLine();
                getBufferedWriter().flush();
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void getMessage() throws Exception
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String messageFromChat;
                while (getSocket().isConnected()) 
                {
                    try 
                    {
                        messageFromChat = getBufferedReader().readLine();
                        System.out.println(messageFromChat);
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        try 
        {
            if (bufferedReader != null) 
            {
                bufferedReader.close();
            }
            if (bufferedWriter != null) 
            {
                bufferedWriter.close();
            }
            if (socket != null) 
            {
                socket.close();
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
