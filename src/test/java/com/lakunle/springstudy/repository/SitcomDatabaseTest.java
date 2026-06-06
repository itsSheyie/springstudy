package com.lakunle.springstudy.repository;

import com.lakunle.springstudy.model.Sitcom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SitcomDatabaseTest {
    private SitcomRepository sitcomDatabase;
    private Sitcom sitcom;

    @BeforeEach
    void setUp(){
        sitcomDatabase = new SitcomRepository();
        sitcom = new Sitcom("Seinfeld", 1994, 6, "Jerry Seinfeld");
    }

    @Test
    void saveSitcom_shouldSaveSuccessfully(){
        assertTrue(sitcomDatabase.saveSitcom(sitcom));
    }

    @Test
    void saveSitcom_shouldNotSaveDuplicate(){
        sitcomDatabase.saveSitcom(sitcom);
        assertFalse(sitcomDatabase.saveSitcom(sitcom));
    }

    @Test
    void getSitcomByName_shouldReturnSitcomByName(){
       sitcomDatabase.saveSitcom(sitcom);
       assertTrue(sitcomDatabase.getSitcomByName("Seinfeld").isPresent());
    }

    @Test
    void getSitcomByName_shouldReturnEmpty(){
        assertTrue(sitcomDatabase.getSitcomByName("Unknown").isEmpty());
    }

    @Test
    void removeSitcom_shouldRemoveSuccessfully(){
        sitcomDatabase.saveSitcom(sitcom);
        assertTrue(sitcomDatabase.removeSitcom("Seinfeld", "Jerry Seinfeld", 1994));
    }


}
