package com.joblisting.jobportal.Repository;

import com.joblisting.jobportal.Model.Post;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SearchPostImpl implements SearchPost{
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        // This is used for search implementation feature in the application
        MongoDatabase database = client.getDatabase("JobPortalBDOListing");
        MongoCollection<Document> collection = database.getCollection("JobsPostsInDB");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("profile", "desc", "techs")))),
                                new Document("$sort",
                                new Document("exp", 1L)),
                                new Document("$limit", 5L)));

        // as from the above search navigator code, we will find our job posts, but they will be in the results,
        // to convert them into the posts, returnable format we are using below Lambda Equation
        result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
        return posts;
    }
}
