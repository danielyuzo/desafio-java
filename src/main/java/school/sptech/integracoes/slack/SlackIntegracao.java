package school.sptech.integracoes.slack;

import school.sptech.model.Servidor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SlackIntegracao {

    public static String enviarMensagem(String mensagem, Servidor servidor) throws IOException, InterruptedException {
        if (servidor.getWebhookSlack() != null) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(servidor.getWebhookSlack()))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("""
                            {
                                "text": "%s", 
                            }""".formatted(mensagem)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return "Houve um erro ao enviar a mensagem";
            } else {
                return response.body();
            }
        }
        return null;
    }
}