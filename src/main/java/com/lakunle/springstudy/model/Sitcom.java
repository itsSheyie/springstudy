package com.lakunle.springstudy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Sitcom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "producer is required")
    private String producer;

    @Min(value = 1900, message = "Release year must be after 1900")
    private int releaseYear;
    @Positive(message = "Total seasons must be greater than 0")
    private Integer totalSeasons;
    private int watchCount = 0;

    public Sitcom(){

    }

    public Sitcom (String name, int releaseYear, Integer totalSeasons, String producer){
        this.name = name;
        this.releaseYear = releaseYear;
        this.totalSeasons = totalSeasons;
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(int totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    @Override
    public String toString(){
        return name + " | " +
                releaseYear + " | " +
                "with watch count: " + watchCount;
    }
}
