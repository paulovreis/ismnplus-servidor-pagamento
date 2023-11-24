import java.io.*;

public class Teclado {
    public Teclado(){

    }
    public String readString()throws Exception{
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String s = teclado.readLine();
        if(s == null)
            throw new Exception("String nula");
        return s;
    }
}
