package com.example.demo;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Country {
    Integer attendeeCount;
    List<Attendee> attendees;
    String name;
    String startDate;
    HashMap<String, Integer> attendessCountinCountry;
    HashMap availableAttendees;
    HashSet<Country> availablecountries;


    public HashSet<Country> getAvailablecountries() {
        return availablecountries;
    }

    public void setAvailablecountries(HashSet<Country> availablecountries) {
        this.availablecountries = availablecountries;
    }


    public HashMap getAttendessCountinCountry() {
        return attendessCountinCountry;
    }

    public void setAttendessCountinCountry(HashMap attendessCountinCountry) {
        this.attendessCountinCountry = attendessCountinCountry;
    }

    public HashMap getAvailableAttendees() {
        return availableAttendees;
    }

    public void setAvailableAttendees(HashMap availableAttendees) {
        this.availableAttendees = availableAttendees;
    }

    public Integer getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount(Integer attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return name == country.name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
