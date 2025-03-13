package org.urfu.practice2;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class Task4 {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Task4() {
    }

    public static void startTask() throws IOException, InterruptedException {
        try (HttpClient client = HttpClient.newHttpClient())
        {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/user-agent"))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var userAgentResponse = objectMapper.readTree(response.body()).get("user-agent");
            System.out.println(userAgentResponse);
        }
    }
}
