package com.joblisting.jobportal.Repository;

import com.joblisting.jobportal.Model.Post;

import java.util.List;

public interface SearchPost {
    List<Post> findByText(String text);
}
