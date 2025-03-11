package com.learnova.classedge.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learnova.classedge.domain.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
   

}
