import java.io.*;
import java.net.*;

public class Tarefa extends Thread {
    private Socket conexao;
    private boolean fim = false;

    public Tarefa(Socket conexao) throws Exception {
        if (conexao == null)
            throw new Exception("Socket nullo");
        this.conexao = conexao;
    }

    // public void setInformation(StringBuilder info) throws Exception {
    // if (info == null)
    // throw new Exception("Objeto nulo");
    // if (info.equals(""))
    // throw new Exception("String vazia!");
    // this.info = info;
    // }

    // public StringBuilder getInformation() {
    // return this.info;
    // }

    public void morra() {
        this.fim = true;
    }

    public void run() {
            try {
                PrintWriter transmissor = new PrintWriter(this.conexao.getOutputStream());
                BufferedReader receptor = new BufferedReader(new InputStreamReader(this.conexao.getInputStream())); // autofalante
                System.out.println("Conexão estabalecida");


                ServletHandler handler = new ServletHandler(conexao);
                handler.handleRequest();
                this.sleep(1500);

                transmissor.close();
                receptor.close();
                conexao.close();
            } catch (Exception e) {
                System.err.print(e.getMessage() + "Classe tarefa");
            }
    }
}
