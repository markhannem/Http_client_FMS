package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MainHTTPClientApp {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read the user's command
            System.out.print("Enter a command (GET, POST, or QUIT): ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("GET")) {
                // Example GET request to retrieve all cities
                HttpRequest getRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/cities"))
                        .GET()
                        .build();
                HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("GET response body: " + getResponse.body());
            } else if (command.equalsIgnoreCase("POST")) {
                // Example POST request to create a new city
                System.out.print("Enter city name: ");
                String name = scanner.nextLine();
                System.out.print("Enter state: ");
                String state = scanner.nextLine();
                System.out.print("Enter population: ");
                int population = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                String postBody = String.format("{\"name\": \"%s\", \"state\": \"%s\", \"population\": %d}", name, state, population);
                HttpRequest postRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/city"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(postBody))
                        .build();
                HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("POST response body: " + postResponse.body());
            } else if (command.equalsIgnoreCase("QUIT")) {
                // Exit the loop and terminate the program
                break;
            } else {
                System.out.println("Invalid command. Please enter GET, POST, or QUIT.");
            }
        }
    }
}