package com.globox.globoxtest.controllers;

import com.globox.globoxtest.dtos.RatedTitleDTO;
import com.globox.globoxtest.dtos.TitleDTO;
import com.globox.globoxtest.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/titles")
public class TitleController {

    @Autowired
    private TitleService titleService;


    // Functionality 1
    @Async
    @GetMapping("/director-writer-same")
    public CompletableFuture<ResponseEntity<List<TitleDTO>>> getTitlesWithSameDirectorAndWriter() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(titleService.getTitlesWithSameDirectorAndWriter()));
    }

    // Functionality 2
    @GetMapping("/common-actors")
    public ResponseEntity<List<TitleDTO>> getCommonTitlesBetweenActors(
            @RequestParam String actor1,
            @RequestParam String actor2) {
        List<TitleDTO> titles = titleService.getCommonTitlesBetweenActors(actor1, actor2);
        return ResponseEntity.ok(titles);
    }

    // Functionality 3
    @GetMapping("/best-by-genre")
    public ResponseEntity<List<RatedTitleDTO>> getBestTitlesByGenre(
            @RequestParam String genre) {
        List<RatedTitleDTO> titles = titleService.getBestTitlesByGenre(genre);
        return ResponseEntity.ok(titles);
    }
}
