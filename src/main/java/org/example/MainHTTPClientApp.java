package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class MainHTTPClientApp{
    public static void main(String[] args) throws Exception {

        HttpClient httpClient = HttpClient.newHttpClient();

        menuLoop:
        while (true) {

            System.out.println("");
            System.out.println("FLIGHT SYSTEM 2000: (Y2k Compliant) ");
            System.out.println("");
            System.out.println("Select your choice: ");
            System.out.println("___________________________________");
            System.out.println("Press 1. To view Cities menu");
            System.out.println("Press 2. To view Airports menu ");
            System.out.println("Press 3. To view Aircraft menu");
            System.out.println("Press 4. To view Passengers menu");
            System.out.println("Press 5. To exit Program");
            System.out.println("___________________________________");

            Scanner scanner = new Scanner(System.in);
            int selection = 0;
            try {
                selection = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid entry(1-5).");
                scanner.nextLine(); // consume the invalid input
                continue;
            }

            switch(selection){
                case 1:
                    while (true) {
                        System.out.print("Cities Menu: ");
                        System.out.println("");
                        System.out.print("Enter a command (GET, POST, or QUIT): ");
                        String command = scanner.next();

                        if (command.equalsIgnoreCase("GET")) {
                            // Example GET request to retrieve all cities
                            HttpRequest getRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/cities"))
                                    .GET()
                                    .build();
                            HttpResponse<String> getResponse = httpClient.send(getRequest,
                                    HttpResponse.BodyHandlers.ofString());
                            System.out.println("GET response body: " + getResponse.body());
                        } else if (command.equalsIgnoreCase("POST")) {
                            // Example POST request to create a new city
                            System.out.print("Enter city name: ");
                            String name = scanner.next();
                            System.out.print("Enter state: ");
                            String state = scanner.next();
                            int population = 0;
                            try {
                                System.out.print("Enter population: ");
                                population = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline character
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                scanner.nextLine(); // consume the invalid input
                                continue;
                            }
                            if (name.trim().isEmpty() || state.trim().isEmpty() || population <= 0) {
                                System.out.println("Invalid input. Please enter valid values for city name, state, and population.");
                                continue;
                            }
                            String postBody = String.format("{\"name\": \"%s\", \"state\": \"%s\", \"population\": %d}",
                                    name, state, population);
                            HttpRequest postRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/cities"))
                                    .header("Content-Type", "application/json")
                                    .POST(HttpRequest.BodyPublishers.ofString(postBody)).build();
                            HttpResponse<String> postResponse = httpClient.send(postRequest,
                                    HttpResponse.BodyHandlers.ofString());
                            System.out.println("POST response body: " + postResponse.body());
                        } else if (command.equalsIgnoreCase("QUIT")) {
                            break;
                        } else {
                            System.out.println("Invalid command. Please enter GET, POST, or QUIT.");
                            continue;
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.print("Airports Menu: ");
                        System.out.println("");
                        System.out.print("Enter a command (GET, POST, or QUIT): ");
                        String command2 = scanner.next();

                        if (command2.equalsIgnoreCase("GET")) {
                            // Example GET request to retrieve all airports
                            HttpRequest getRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/airports"))
                                    .GET()
                                    .build();
                            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                            System.out.println("GET response body: " + getResponse.body());
                        } else if (command2.equalsIgnoreCase("POST")) {
                            System.out.print("Enter Airport name: ");
                            String name = scanner.next();
                            System.out.print("Enter state: ");
                            String code = scanner.next();
                            scanner.nextLine(); // Consume the newline character
                            String postBody = String.format("{\"name\": \"%s\", \"code\": \"%s\"}", name, code);
                            HttpRequest postRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/airports"))
                                    .header("Content-Type", "application/json")
                                    .POST(HttpRequest.BodyPublishers.ofString(postBody))
                                    .build();
                            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
                            System.out.println("POST response body: " + postResponse.body());
                        } else if (command2.equalsIgnoreCase("QUIT")) {
                            break;
                        } else {
                            System.out.println("Invalid command. Please enter GET, POST, or QUIT.");
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.print("Aircraft Menu: ");
                        System.out.println("");
                        System.out.print("Enter a command (GET, POST, or QUIT): ");
                        String command3 = scanner.next();

                        if (command3.equalsIgnoreCase("GET")) {
                            // Example GET request to retrieve all Aircraft
                            HttpRequest getRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/aircrafts"))
                                    .GET()
                                    .build();
                            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                            System.out.println("GET response body: " + getResponse.body());
                        } else if (command3.equalsIgnoreCase("POST")) {
                            // Example POST request to create a new Aircraft
                            System.out.print("Enter Aircraft Type: ");
                            String type = scanner.next();
                            System.out.print("Enter Airline Name: ");
                            String airlineName = scanner.next();
                            System.out.print("Enter Number of Passengers: ");
                            int numberOfPassengers = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            String postBody = String.format("{\"type\": \"%s\", \"airlineName\": \"%s\", \"numberOfPassengers\": \"%d\"}", type, airlineName, numberOfPassengers);
                            HttpRequest postRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/aircrafts"))
                                    .header("Content-Type", "application/json")
                                    .POST(HttpRequest.BodyPublishers.ofString(postBody))
                                    .build();
                            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
                            System.out.println("POST response body: " + postResponse.body());
                        } else if (command3.equalsIgnoreCase("QUIT")) {
                            break;
                        } else {
                            System.out.println("Invalid command. Please enter GET, POST, or QUIT.");
                        }
                    }
                    break;

                case 4:
                    while (true) {
                    System.out.print("Passengers Menu: ");
                    System.out.println("");
                    System.out.print("Enter a command (GET, POST, or QUIT): ");
                    String command4 = scanner.next();

                    if (command4.equalsIgnoreCase("GET")) {
                        // Example GET request to retrieve all passengers
                        HttpRequest getRequest = HttpRequest.newBuilder()
                                .uri(URI.create("http://localhost:8080/passengers"))
                                .GET()
                                .build();
                        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                        System.out.println("GET response body: " + getResponse.body());
                        continue;
                    } else if (command4.equalsIgnoreCase("POST")) {
                        // Example POST request to create a new airport
                        System.out.print("Enter Passengers First Name: ");
                        String firstName = scanner.next();
                        System.out.print("Enter Passengers Last Name: ");
                        String lastName = scanner.next();
                        System.out.print("Enter Passenger Phone Number:  ");
                        String phoneNumber = scanner.next();
                        scanner.nextLine(); // Consume the newline character
                        String postBody = String.format("{\"firstName\": \"%s\", \"lastName\": \"%s\", \"phoneNumber\": \"%s\"}", firstName, lastName, phoneNumber);
                        HttpRequest postRequest = HttpRequest.newBuilder()
                                .uri(URI.create("http://localhost:8080/passengers"))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(postBody))
                                .build();
                        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
                        System.out.println("POST response body: " + postResponse.body());
                        continue;
                    } else if (command4.equalsIgnoreCase("QUIT")) {
                        break;
                    } else {
                        System.out.println("Invalid command. Please enter GET, POST, or QUIT.");
                        }
                    }
                    break;

                case 5:
                    // Exit the loop and terminate the program
                    System.out.println("Thank You For Using FLIGHT SYSTEM 2000... Good Bye");
                    break menuLoop;
            }

        }
    }
}