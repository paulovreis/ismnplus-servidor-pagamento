import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket host = new ServerSocket(3030);
            System.out.println("SERVIDOR RODANDO NA PORTA 3030...");

            while (true) {
                Socket conexao = host.accept();
                Tarefa thread = new Tarefa(conexao);
                thread.start();
            }

        } catch (Exception erro) {
            System.err.println(erro.getMessage() + "Classe servidor");
        }
    }
}
