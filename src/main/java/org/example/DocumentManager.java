package org.example;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.*;

public class DocumentManager {

    private final Map<String, Document> documents = new HashMap<>();
    private Integer idCounter = 1;

    public Document save(Document document) {

        Instant instant = Instant.now();
        if (document != null) {
            String documentId = String.valueOf(idCounter++);
            if (document.id == null) {
                document.setId(documentId);
            }
            if (document.author.id == null) {
                document.author.setId(documentId);
            }
            document.setCreated(instant);
            documents.put(document.id, document);
            System.out.println("Document save: " + document);
        } else {
            System.out.println("Not save");
        }
        return document;
    }

    public List<Document> search(SearchRequest request) {

        var searchResult = documents.values()
                .stream()
                .filter(document -> request.titlePrefixes == null ||
                        request.titlePrefixes.isEmpty() ||
                        request.titlePrefixes.stream().anyMatch(document.title::startsWith))
                .filter(document -> request.containsContents == null ||
                        request.containsContents.isEmpty() ||
                        request.containsContents.stream().anyMatch(document.content::contains))
                .filter(document -> request.authorIds == null ||
                        request.authorIds.isEmpty() ||
                        request.authorIds.stream().anyMatch(document.author.id::contains))
                .filter(document -> request.createdFrom == null ||
                        document.created.isAfter(request.createdFrom))
                .filter(document -> request.createdTo == null ||
                        document.created.isBefore(request.createdTo))
                .toList();

        searchResult.forEach(e -> {
            System.out.println("Document: " + e);
        });

        return searchResult;
    }

    public Optional<Document> findById(String id) {

        var document = documents.get(id);
        System.out.println("Document findById: " + document);

        return Optional.ofNullable(document);
    }

    @Data
    @Builder
    public static class SearchRequest {
        private List<String> titlePrefixes;
        private List<String> containsContents;
        private List<String> authorIds;
        private Instant createdFrom;
        private Instant createdTo;
    }

    @Data
    @Builder
    public static class Document {
        private String id;
        private String title;
        private String content;
        private Author author;
        private Instant created;
    }

    @Data
    @Builder
    public static class Author {
        private String id;
        private String name;
    }

}
