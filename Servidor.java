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

            Teclado teclado = new Teclado();
            for(;;){
                System.out.println("Caso dejese finalizar o servidor digite 'fim'");
                String resp = teclado.readString().toLowerCase();
                if(resp.equals("fim")){
                    aConexao.morra();
                    System.out.println("\n\nSERVIDOR FINALIZADO");
                    host.close();
                    System.exit(0);
                }else{
                    continue;
                }
            }

        } catch (Exception erro) {
            System.err.println(erro.getMessage() + " - Classe servidor");
        }
    }
}
