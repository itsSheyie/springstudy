package com.lakunle.springstudy.service;

import com.lakunle.springstudy.model.Sitcom;
import com.lakunle.springstudy.repository.SitcomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SitcomServiceTest {

    @Mock
    private SitcomRepository sitcomDatabase;

    @InjectMocks
    private SitcomService sitcomService;

    private Sitcom sitcom;

    @BeforeEach
    void setUp(){
        sitcom = new Sitcom("Friends", 1994, 10, "David Crane");
    }

    @Test
    void saveSitcom_shouldReturnSavedMessage(){
        when(sitcomDatabase.saveSitcom(sitcom)).thenReturn(true);
        assertEquals("Saved!", sitcomService.saveSitcom(sitcom));

    }

    @Test
    void saveSitcom_shouldReturnAlreadyExistsMessage(){
        when(sitcomDatabase.saveSitcom(sitcom)).thenReturn(false);
        assertEquals("Already Exist", sitcomService.saveSitcom(sitcom));
    }

    @Test
    void watchSitcom_shouldIncrementWatchCount(){
        when(sitcomDatabase.getSitcomByName("Friends")).thenReturn(Optional.of(sitcom));

        String result = sitcomService.watchSitcom("Friends");

        assertTrue(result.contains("1 times"));
    }

    @Test
    void watchSitcom_shouldReturnNotfound(){
        when(sitcomDatabase.getSitcomByName("Unknown")).thenReturn(Optional.empty());

        assertEquals("Sitcom not found", sitcomService.watchSitcom("Unknown"));
    }


}
