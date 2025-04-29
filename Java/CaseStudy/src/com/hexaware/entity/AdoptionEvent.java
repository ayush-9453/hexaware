package com.hexaware.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdoptionEvent {
    private String eventName;
    private List<IAdoptable> participants = new ArrayList<>();

    public AdoptionEvent(String eventName, LocalDate eventDate, int eventID) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventID = eventID;
    }

    public AdoptionEvent(String eventName, LocalDate eventDate) {
         this.eventName = eventName;
         this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    private LocalDate eventDate;
    private int eventID;

    public void setParticipants(List<IAdoptable> participants) {
        this.participants = participants;
    }

    public boolean registerParticipant(IAdoptable participant) {
        return participants.add(participant);
    }

    public List<IAdoptable> getParticipants() {
        return new ArrayList<>(participants);  // Returning a copy to avoid external modifications
    }

    // Host the event and get the participants
    public List<IAdoptable> hostEvent() {
        // You could add more logic here to manage the event
        return getParticipants();
    }
    @Override
    public String toString() {
        return "Event ID: " + eventID + ", Name: " + eventName + ", Date: " + eventDate;
    }


}
