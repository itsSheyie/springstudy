package com.lakunle.springstudy.model;

public class Sitcom {

    private int id;
    private String name;

    private String producer;
    private int releaseYear;
    private int totalSeasons;
    private int watchCount = 0;

    public Sitcom (String name, int releaseYear, int totalSeasons, String producer){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getTotalSeasons() {
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
