// this is our controller class - MVN controller
package com.joblisting.jobportal.Contoller;
import com.joblisting.jobportal.Model.Post;
import com.joblisting.jobportal.Repository.PostRepository;
import com.joblisting.jobportal.Repository.SearchPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    PostRepository repo;

    @Autowired
    SearchPost srepo;

    // Get all the posts in the database - this will return all the available Job posting currently present for opening in the database
    @GetMapping("/allPosts")
    public List<Post> getAllPosts(){
        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    public List<Post> searchPost(@PathVariable String text){
        return srepo.findByText(text);
    }

    // Post the new Job Post - this will take the data from the client and add it in the DB, which can be again shown in our GET data page on request.
    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        return repo.save(post);
    }
}
