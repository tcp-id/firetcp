package com.example.tcp.controller;

import com.example.tcp.domain.dto.ErrorMessage;
import com.example.tcp.domain.dto.ResponseList;
import com.example.tcp.domain.model.Anime;
import com.example.tcp.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllAnime() {
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findBy()));
    }

    @GetMapping("/{id}")
    public List<?> findAnimePorId(@PathVariable UUID animeid) {
        return animeRepository.findAll();
    }

    @PostMapping("/")
    public Anime createMovie(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }

    @DeleteMapping
    public ResponseEntity<?> delAnime(@PathVariable UUID id){
        Anime anime = animeRepository.findById(id).orElse(null);
        if(anime == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No hi es"));
        }
        animeRepository.delete(anime);
        return  ResponseEntity.ok().build();
    }

    /*
    @DeleteMapping(value = "/posts/{id}")


    public ResponseEntity<Long> deletePost(@PathVariable Long id) {
        var isRemoved = postService.delete(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);


        public boolean delete (Long id){
            var isRemoved = this.post.removeIf(post -> post.getId().equals(id));
            return isRemoved;
        }


    }
    */

}

