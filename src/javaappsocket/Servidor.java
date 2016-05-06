package javaappsocket;

import interfaces.CallBackMessage;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.ServerSocket; 
import java.net.Socket;
import javax.swing.JOptionPane;

public class Servidor {

    static int porta;
    
    public Servidor(CallBackMessage cbm) {
        porta = Integer.parseInt(JOptionPane.showInputDialog(null,
               "Insira a porta em que o servidor vai escutar",
               "7000"));
               
        ServerSocket serv = null;
        Socket s = null;
        BufferedReader entrada = null;
        try {
            serv = new ServerSocket(porta);
            System.out.println("Servidor escutando na porta "+porta+"...");
            s = serv.accept();

            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String dados = entrada.readLine();
            while (dados.indexOf("sair") == -1){
                if(dados != null){
                    cbm.appendMessage("[" + s.getInetAddress().getHostName() + "]: " +dados);
                }
                s.close();
                s = serv.accept();
                entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
                dados = entrada.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                s.close();
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
