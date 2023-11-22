import java.io.*;
import java.net.*;
import java.lang.Object;

public class AceitadoraDeConexao extends Thread{
    private ServerSocket host;
    private boolean fim = false;

    public AceitadoraDeConexao(ServerSocket host)throws Exception {
        if(host == null)
            throw new Exception("Socket nulo");
        this.host = host;
    }

    public void morra(){
        this.fim = true;
    }

    @Override
    public void run(){
        try{
            while(this.fim == false){
                Socket conexao = this.host.accept();
                Tarefa thread = new Tarefa(conexao);
                thread.start();
            }
        }catch(Exception e){
            System.err.println(e.getMessage() + "Classe AceitadoraDeConexao");
        }
    }
}
