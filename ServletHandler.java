import java.io.*;
import java.net.*;
import java.lang.Object;

public class ServletHandler {
    private Socket clientSocket;

    public ServletHandler(Socket clientSocket) throws Exception {
        if (clientSocket == null)
            throw new Exception("Socket nulo");
        this.clientSocket = clientSocket;
    }

    public void handleRequest() {
        try {
            String resposta;
            BufferedReader receptor = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter transmissor = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = receptor.readLine();
            System.out.println("Recebido: " + request);

            if (request == null) {
                transmissor.println("HTTP/1.1 404 Not Found\r\n\r\n");
                transmissor.flush();
            } else {
                if (request != null && request.startsWith("OPTIONS")) {
                    // Responder à solicitação OPTIONS para CORS
                    transmissor.println("HTTP/1.1 200 OK");
                    transmissor.println("Access-Control-Allow-Origin: *");
                    transmissor.println("Access-Control-Allow-Methods: POST, OPTIONS"); // Adicione métodos permitidos
                    transmissor.println("Access-Control-Allow-Headers: Content-Type");
                    transmissor.println("Access-Control-Max-Age: 86400"); // Tempo em segundos que a resposta OPTIONS
                                                                          // pode ser armazenada em cache
                }
                if (request.startsWith("POST")) {
                    if (request.startsWith("POST /checkout")) {
                        transmissor.println("HTTP/1.1 200 OK");
                        transmissor.println("Content-Type: text/plain");
                        transmissor.println("Access-Control-Allow-Origin: *");
                        transmissor.println(""); // Linha em branco antes do corpo da resposta

                        // Leia o corpo da requisição
                        StringBuilder reqBody = new StringBuilder();
                        while (receptor.ready()) {
                            reqBody.append((char) receptor.read());
                        }
                        resposta = "Pagamento confirmado!";
                        transmissor.println(resposta);

                    }
                }
            }

            receptor.close();
            transmissor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
