package com.lakunle.springstudy.controller;

import com.lakunle.springstudy.model.Sitcom;
import com.lakunle.springstudy.service.SitcomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sitcoms")
public class SitcomController {
    private final SitcomService sitcomService;

    public SitcomController(SitcomService sitcomService){
        this.sitcomService = sitcomService;
    }

    @PostMapping
    public ResponseEntity<String> saveSitcom(@Valid @RequestBody Sitcom sitcom){
        return ResponseEntity.ok(sitcomService.saveSitcom(sitcom));
    }

    @GetMapping()
    public ResponseEntity<List<Sitcom>> allSitcom(){
        return ResponseEntity.ok(sitcomService.getAllSitcom());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity <Sitcom> getSitcomByName(@PathVariable String name){
        return sitcomService.getByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Sitcom>> getSitcomByYear(@PathVariable int year){
        return ResponseEntity.ok(sitcomService.getSitcomByYear(year));
    }

    @GetMapping("/watch/{name}")
    public ResponseEntity<String> watchSitcom(@PathVariable String name){
        return ResponseEntity.ok(sitcomService.watchSitcom(name));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteSitcom(@PathVariable String name, @RequestParam int releaseYear,  @RequestParam String producer){
        return ResponseEntity.ok(sitcomService.deleteSitcom(name, releaseYear, producer));
    }
}
