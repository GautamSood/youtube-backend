package com.example.youtube.Videos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideosRepository extends JpaRepository<Videos, Long> {

    Optional<Videos> findById(Long id);


    void deleteById(Long id);
}