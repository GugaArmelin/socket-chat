package javaappsocket;

import java.io.IOException; 
import java.io.PrintStream; 
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente {
    
    //IP do servidor
    static String ipServidor;
    //porta do servidor
    static int porta;
    
    public Cliente() {
    
        //lê o ip e a porta do servidor
        ipServidor = JOptionPane.showInputDialog(null, "Insira o IP do servidor","127.0.0.1");
        porta      = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a porta do servidor","7000"));  
    }
    
    public void sendMessage(String msg) {
        //Declaro o Socket cliente
        Socket s = null;
        //Declaro a Stream de saida de dados
        PrintStream ps = null;
        try {
            //Cria o socket com o recurso desejado na porta especificada!
            s = new Socket(ipServidor, porta);
            //Cria a Stream de saida de dados
            ps = new PrintStream(s.getOutputStream());
            //Imprime uma linha para a stream de saída de dados
            ps.println(msg);
        } catch (IOException e) {
            System.out.println("Algum problema ocorreu ao criar ou enviar dados pelo Socket.");
            e.printStackTrace();
        } finally {
            try {
                //Encerra o socket cliente
                s.close();
            } catch (Exception e) {
                System.out.println("Algum problema ocorreu ao finalizar o socket");
            }
        }
    }
}
