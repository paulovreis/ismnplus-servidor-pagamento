import java.io.*;
import java.net.*;
import java.lang.Object;
public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket host = new ServerSocket(3030);
            System.out.println("SERVIDOR RODANDO NA PORTA 3030...");

            AceitadoraDeConexao aConexao = new AceitadoraDeConexao(host);
            aConexao.start();

            for(;;){
                Teclado teclado = new Teclado();
                System.out.println("Caso dejese finalizar o servidor digite 'fim'");
                String resp = teclado.readString().toLowerCase();
                if(resp.equals("fim")){
                    aConexao.morra();
                    host.close();
                    System.out.println("\n\nSERVIDOR FINALIZADO");
                    System.exit(0);
                }
            }

        } catch (Exception erro) {
            System.err.println(erro.getMessage() + "Classe servidor");
        }
    }
}
