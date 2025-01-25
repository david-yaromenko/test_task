package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DocumentManager documentManager = new DocumentManager();

        DocumentManager.Author author1 = DocumentManager.Author.builder().name("John").build();
        DocumentManager.Author author2 = DocumentManager.Author.builder().name("Brad").build();
        DocumentManager.Author author3 = DocumentManager.Author.builder().name("Mike").build();
        DocumentManager.Author author4 = DocumentManager.Author.builder().name("Lila").build();

        DocumentManager.Document document1 = DocumentManager.Document.builder().author(author1).title("Task").content("First method").build();
        DocumentManager.Document document2 = DocumentManager.Document.builder().author(author2).title("Article").content("Program").build();
        DocumentManager.Document document3 = DocumentManager.Document.builder().author(author3).title("Tabasco").content("Drink water").build();
        DocumentManager.Document document4 = DocumentManager.Document.builder().author(author4).title("Gym art").content("Health").build();

        List<String> prefixes = new ArrayList<>();
        prefixes.add("Ta");
        prefixes.add("art");
        prefixes.add("hea");
        prefixes.add("gy");

        List<String> contains = new ArrayList<>();
        contains.add("First method");
        contains.add("Program");
        contains.add("Drink water");
        contains.add("Health");

        List<String> authorIds = new ArrayList<>();
        authorIds.add("1");
        authorIds.add("2");
        authorIds.add("3");
        authorIds.add("4");

        System.out.println("!!!SAVE METHOD!!!");
        documentManager.save(document1);
        System.out.println();
        documentManager.save(document2);
        System.out.println();
        documentManager.save(document3);
        System.out.println();
        documentManager.save(document4);
        System.out.println();
        System.out.println("!!!FIND BY ID METHOD!!!");
        documentManager.findById("2");
        System.out.println();
        System.out.println("!!!SEARCH METHOD!!!");

        DocumentManager.SearchRequest searchRequest = new DocumentManager.SearchRequest(null, contains, authorIds, document1.getCreated(), document4.getCreated());
        documentManager.search(searchRequest);
    }
}