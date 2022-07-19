import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    /**
     */
    public static void main(String[] args) throws Exception {

        // fazer conexao HTTP e buscar os top 250 filmes

        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest Request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(Request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair os dados que interessa(titulo, poster, classificacao)

        var parser = new JsonParser();

        List<Map<String, String>> ListaDeFilmes = parser.parse(body);

        // exibir e manipular os dados, deixando bonitinho.
        for (Map<String, String> filme : ListaDeFilmes) {

            System.out.println("\u001b[32;1m \u001b[47;1m" + filme.get("title") + " " + "\u001b[m");


            System.out.println(filme.get("image"));
            String rate = filme.get("imDbRating");
            System.out.println("\u001b[45;1m" + "Classificação: " + rate + "\u001b[m");
            System.out.println("\u2B50".repeat(Math.max(0, Math.round(Float.parseFloat(rate)))));
            System.out.println();

        }


    }
}
