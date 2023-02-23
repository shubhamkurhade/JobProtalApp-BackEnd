package com.joblisting.jobportal.Repository;

import com.joblisting.jobportal.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
