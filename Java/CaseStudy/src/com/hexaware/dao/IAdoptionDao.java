package com.hexaware.dao;

import com.hexaware.entity.AdoptionEvent;

import java.util.List;

public interface IAdoptionDao {
    boolean hostEvent (AdoptionEvent event);
    List<AdoptionEvent> getAllEvents();
}
