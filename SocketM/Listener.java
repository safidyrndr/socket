package code;

import java.awt.event.*;

import graph.Fenetre;

public class Listener implements ActionListener
{
    Fenetre f;
    String userName = "";
    
    public Listener(Fenetre f)
    {
        this.f = f;
    }

    public void actionPerformed(ActionEvent e)
    {
        try 
        {
            if (e.getSource() == f.getUserButton()) 
            {
                this.userName = f.getUserField().getText();
                f.setClient(new Client(f.getSocket(), this.userName));
                f.getUserName().setText(this.userName);
            }
            if (e.getSource() == f.getButton()) 
            {
                f.getTextArea().setText(f.getTextField().getText());
            }
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}
