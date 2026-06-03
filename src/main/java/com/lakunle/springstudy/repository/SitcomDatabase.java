package com.lakunle.springstudy.repository;

import com.lakunle.springstudy.model.Sitcom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SitcomDatabase {

    private List<Sitcom> sitcoms = new ArrayList<>();

    public boolean isAvailable (String name){
        return sitcoms.stream()
                .map(Sitcom::getName)
                .anyMatch(s -> s.equals(name));
    }

    public boolean isAvailable(String name, int releaseYear){
       return sitcoms.stream().anyMatch(s-> s.getName().equals(name) && s.getReleaseYear() == releaseYear);
    }

    public boolean isAvailable(String name, int releaseYear, String producer){
        return sitcoms.stream().anyMatch(s-> s.getName().equals(name) && s.getReleaseYear() == releaseYear && s.getProducer().equals(producer));
    }


    public boolean saveSitcom(Sitcom sitcom){
        if(!isAvailable(sitcom.getName(), sitcom.getReleaseYear(), sitcom.getProducer())){
            sitcoms.add(sitcom);
            return true;
        }
        return false;
    }

    public List<Sitcom> getSitcomByYear(int releaseYear){
        return sitcoms.stream().filter(s -> s.getReleaseYear() == releaseYear).toList();
    }

    public List<Sitcom> getSitcomByProducer (String producer){
        return sitcoms.stream().filter(s -> s.getProducer().equals(producer)).toList();
    }

    public Optional<Sitcom> getSitcomByName (String name){
        return sitcoms.stream().filter(s -> s.getName().equals(name))
                .findFirst();
    }

    public List<String> getSitcomNames(){
        return sitcoms.stream().map(Sitcom :: getName).toList();
    }


    public List<Sitcom> getSitcoms(){
        return List.copyOf(sitcoms);
    }

    public boolean removeSitcom (String name, String producer, int releaseYear){
        return sitcoms.removeIf(s-> s.getName().equals(name) && s.getReleaseYear()== releaseYear && s.getProducer().equals(producer));
    }

}
