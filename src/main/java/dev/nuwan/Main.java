package dev.nuwan;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.Scanner;

public class Main {

    private static final String FIREBASE_PROJECT_URL = "https://YOUR_DB_URL.firebaseio.com/"; // Change the db url
    private static final String API_ROOT_NODE_URL = FIREBASE_PROJECT_URL + "api.json";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opt = 0;

        while (opt != 3) {
            printMenu();
            opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1:
                    System.out.println("New data added to " + addData());
                    break;
                case 2:
                    System.out.println(getData());
                    break;
                case 3:
                    break;
            }

        }

    }

    private static String addData() {
        System.out.print("Please enter name to add : ");
        String name = scanner.nextLine();
        JsonNode jsonObj = new JsonNode("{\"name\" : \"" + name + "\"}");
        HttpResponse<String> postResponse = Unirest
                .post(API_ROOT_NODE_URL).body(jsonObj)
                .asString();
        return postResponse.getBody();
    }

    private static String getData() {
        return Unirest.get(API_ROOT_NODE_URL + "?print=pretty").asString().getBody();
    }

    private static void printMenu() {
        System.out.println(
                "Please select option :\n\t1. Add Data\n\t2. View Data\n\t3. Exit"
        );
    }

}
