package com.lakunle.springstudy.service;

import com.lakunle.springstudy.model.Sitcom;
import com.lakunle.springstudy.repository.SitcomDatabase;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SitcomService {
    private final SitcomDatabase sitcomDatabase;



    public SitcomService(SitcomDatabase sitcomDatabase){
        this.sitcomDatabase = sitcomDatabase;

    }

    public String saveSitcom(Sitcom sitcom){
        if(sitcomDatabase.saveSitcom(sitcom)){
            return "Saved!";
        }
        return "Already Exist";
    }

    public List<String> getSitcomNames(){
        return sitcomDatabase.getSitcomNames();
    }

    public List<Sitcom> getSitcomByProducer(String producer){
        return sitcomDatabase.getSitcomByProducer(producer);
    }

    public Optional<Sitcom> getByName(String name){
        return sitcomDatabase.getSitcomByName(name);
    }

    public List<Sitcom> getSitcomInYear(int year){
        return sitcomDatabase.getSitcomByYear(year);
    }

    public List<Sitcom> getAllSitcoms(){
        return sitcomDatabase.getSitcoms();
    }

    public String deleteSitcom(String name, int releaseYear, String producer){
        if(sitcomDatabase.removeSitcom(name, producer, releaseYear)){
            return name + " Removed!";
        }else
            return "Does not exists";
    }

    public String watchSitcom(String name){
        return sitcomDatabase.getSitcomByName(name).map(
                s -> {
                    s.setWatchCount(s.getWatchCount() + 1);
                    return name + " has been watched " + s.getWatchCount() + " times";
                }).orElse("Sitcom not found");


    }




    public Optional<Sitcom> mostWatchedSitcom(){
        return sitcomDatabase.getSitcoms()
                .stream().max(Comparator.comparingInt(Sitcom::getWatchCount));
    }
}
