package com.lakunle.springstudy.service;

import com.lakunle.springstudy.model.Sitcom;
import com.lakunle.springstudy.repository.SitcomRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class SitcomService {
    private SitcomRepository sitcomRepository;

    public SitcomService(SitcomRepository sitcomRepository){
        this.sitcomRepository = sitcomRepository;
    }

    public String saveSitcom(Sitcom sitcom){
        if(sitcomRepository.existsByNameAndReleaseYearAndProducer(
                sitcom.getName(),
                sitcom.getReleaseYear(),
                sitcom.getProducer()
        ))return "Already Exists";

        sitcomRepository.save(sitcom);
        return "Saved!";
    }

    public List<String> getSitcomNames(){
        return sitcomRepository.findAll()
                .stream()
                .map(Sitcom::getName)
                .toList();
    }

    public List<Sitcom> getSitcomByProducer(String producer){
        return sitcomRepository.findByProducer(producer);
    }

    public Optional<Sitcom> getByName(String name){
        return sitcomRepository.findByName(name);
    }

    public List<Sitcom> getSitcomByYear(int year){
        return sitcomRepository.findByReleaseYear(year);
    }

    public List<Sitcom> getAllSitcom(){
        return sitcomRepository.findAll();
    }


    public String deleteSitcom(String name, int releaseYear, String producer){
        return sitcomRepository.findByNameAndReleaseYearAndProducer(name, releaseYear, producer)
                .map( s -> {
                    sitcomRepository.delete(s);
                    return name + " Removed ";
                }).orElse( " Does not exists");

    }

    public String watchSitcom(String name){
        return sitcomRepository.findByName(name)
                .map(s -> {
                            s.setWatchCount(s.getWatchCount() + 1);
                            sitcomRepository.save(s);
                            return name + " has been watched " + s.getWatchCount() + " times";
                }).orElse("Does not exist");
    }

    public Optional<Sitcom> mostWatchedSitcom(){
        return sitcomRepository.findAll()
                .stream()
                .max(Comparator.comparingInt(Sitcom::getWatchCount));
    }
}
