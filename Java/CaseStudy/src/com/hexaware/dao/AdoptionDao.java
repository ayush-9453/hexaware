package com.hexaware.dao;

import com.hexaware.entity.AdoptionEvent;
import com.hexaware.utils.DbConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdoptionDao implements IAdoptionDao{

    @Override
    public boolean hostEvent(AdoptionEvent event) {
        String query = "INSERT INTO adoption_events (event_name, event_date) VALUES (?, ?)";
        try (Connection connection = DbConn.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, event.getEventName());
            ps.setDate(2, java.sql.Date.valueOf(event.getEventDate()));  // Assuming eventDate is a LocalDate
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AdoptionEvent> getAllEvents() {
        List<AdoptionEvent> events = new ArrayList<>();
        String query = "SELECT * FROM adoption_events";
        try (Connection connection = DbConn.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                String eventName = rs.getString("event_name");
                Date eventDate = rs.getDate("event_date");
                AdoptionEvent event = new AdoptionEvent(eventName,eventDate.toLocalDate(),eventId);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

}
