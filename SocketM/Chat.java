package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Chat implements Runnable
{
    public static ArrayList<Chat> chats = new ArrayList<>();
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String userName;

///getters & setters
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public ArrayList<Chat> getChats() {
        return chats;
    }
    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
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

///constructor
    public Chat(Socket socket) throws Exception
    {
        try 
        {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = bufferedReader.readLine();
            chats.add(this);
            // showMessage("SERVER: " + userName + " est connecté !");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

///function
    @Override
    public void run()
    {
        String message;

        while (socket.isConnected()) 
        {
            try 
            {
                message = bufferedReader.readLine();
                showMessage(message);
            } 
            catch (Exception e) 
            {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void showMessage(String message) throws Exception
    {
        for (Chat chat : chats) 
        {
            try 
            {
                if (!chat.getUserName().equals(getUserName())) 
                {
                    chat.getBufferedWriter().write(message);
                    chat.getBufferedWriter().newLine();
                    chat.getBufferedWriter().flush();
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void closeChat() throws Exception
    {
        try {
            getChats().remove(this);
            showMessage("SERVER: "+getUserName()+" a quitté le chat !");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        try 
        {
            closeChat();
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
