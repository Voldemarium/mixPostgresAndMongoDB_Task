package ru.synergy.services;

import com.mongodb.client.*;
import lombok.Getter;
import org.bson.Document;

import java.io.Closeable;
import java.util.Map;

import static com.mongodb.client.model.Projections.*;

public class LogService implements Closeable {
    private final MongoClient mongoClient;
    @Getter
    private final MongoCollection<Document> logCollection;

    public LogService() {
        mongoClient = MongoClients.create();
        var database = mongoClient.getDatabase("syn");
        logCollection = database.getCollection("logs");
    }

    public void add(Map<String, Object> data) {
        logCollection.insertOne(new Document(data));
    }

    public String printMetaByContactId(int contact_id) {
        FindIterable<Document> iterable = logCollection.find(new Document("newContactId", contact_id));
        StringBuilder builder = new StringBuilder();
        for (Document document : iterable) {
            builder.append(document);
        }
        return builder.toString();
    }

    public void printMetaByUserId(int user_id) {
        FindIterable<Document> iterable = logCollection.find(new Document("userId", user_id))
                .projection(fields(include("newContactId", "creation time", "userId"), excludeId()));
        for (Document document : iterable) {
            System.out.println(document);
        }
    }

    @Override
    public void close() {
        mongoClient.close();
    }

}