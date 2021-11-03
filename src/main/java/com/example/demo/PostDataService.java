package com.example.demo;


import org.springframework.http.ResponseEntity;

import java.util.*;

public class PostDataService {
    List<Attendee> emptyList = Collections.<Attendee>emptyList();  ; // use this for any empty attendees

    public Country processData(ResponseEntity<Partners> responseEntity) {
        HashMap<String, Integer> countAttendeesInCountries = new HashMap<>();
        HashMap<String, TreeSet<String>> countryAvailableDates = new HashMap<>();
        HashMap<String, List<String>> availableAttendees = new HashMap<>();
        String startDate = null;
        int counter = 0;
        boolean check2ConsecDays = false;
        Country country = new Country();
        HashSet<Country> countries = new HashSet<>();
        for (PartnerObject partnerObject : responseEntity.getBody().partners) {
            Collections.sort(partnerObject.getAvailableDates());

            if (countAttendeesInCountries.containsKey(partnerObject.getCountry()))
                check2ConsecDays = checkConsectiveDays(partnerObject.availableDates);

            else {
                countAttendeesInCountries.put(partnerObject.getCountry(), counter);
                check2ConsecDays = checkConsectiveDays(partnerObject.availableDates);
            }

            //a partner is available two consecutive days
            // meaning they are eligible to be a partner for a country
            if (check2ConsecDays) {

                // add + 1 to attendee hashmap count
                counter = countAttendeesInCountries.get(partnerObject.getCountry()) + 1;
                countAttendeesInCountries.put(partnerObject.getCountry(), counter);
            }

            // check for matching data
            if (countryAvailableDates.containsKey(partnerObject.getCountry())) {
                startDate = determineCountryStartDate(partnerObject.availableDates, countryAvailableDates, partnerObject.getCountry());
                country.setName(partnerObject.getCountry());
                country.setStartDate(startDate);
                countries.add(country);

            }
                else{
                countryAvailableDates.put(partnerObject.country, getSort((ArrayList) partnerObject.getAvailableDates()));
            }


                //check list of attendees
            if(availableAttendees.containsKey(partnerObject.getCountry()))

                // adding list of attendees to that country
            {
                List<String> attendees = availableAttendees.get(partnerObject.getCountry());
                attendees.add(partnerObject.getEmail());
                availableAttendees.put(partnerObject.getCountry(), attendees); // update hashmap with new attendees
            }
            else

            {//determine eligible attendees first
                determineEligibleAttendees(partnerObject.getAvailableDates(), countryAvailableDates, partnerObject.getCountry());
                List<String> attendees = new ArrayList<>();
                attendees.add(partnerObject.getEmail());
                availableAttendees.put(partnerObject.getCountry(), attendees);
            }



        }
        country.setAvailableAttendees(availableAttendees);
        country.setAttendessCountinCountry(countAttendeesInCountries);
        country.setAvailablecountries(countries);
        return country;

    }

    private boolean determineEligibleAttendees(List<String> availableDates, HashMap<String, TreeSet<String>> countryAvailableDates, String country) {


        // check here if available dates match country available dates consective days


        return false;
    }


    private String determineCountryStartDate(List<String> availableDates, HashMap<String, TreeSet<String>> countryAvailableDates, String country  ) {

         String startDate = null;
        for(String availableDate: availableDates) {
            if (countryAvailableDates.get(country).contains(availableDate))
                startDate = countryAvailableDates.get(country).first();
        }

        if(startDate==null) {
            startDate = availableDates.get(0); // first available start date
        }
        return startDate;

    }


    boolean checkConsectiveDays(List<String> availableDates) {
        ConsectiveDates consectiveDates = new ConsectiveDates();
        boolean isConsectiveDates = consectiveDates.isConsectiveDates(availableDates);

        return isConsectiveDates;

    }


    public void sendData(Country countryObj) {
        List<Country> countries = new ArrayList<>();  // this is the list to send

        ArrayList<Country> availablecontries
                = new ArrayList<Country>(countryObj.getAvailablecountries());

        for(Country country: availablecontries)
        {
            Country addedCountry = new Country();
            if(countryObj.getAttendeeCount()>0) {
                //lookup country in hashmaps
            addedCountry.setAttendees((List<Attendee>) countryObj.getAttendessCountinCountry().get(country));
            addedCountry.setAttendeeCount((Integer) countryObj.getAttendessCountinCountry().get(country));
            addedCountry.setName(country.getName());
            addedCountry.setStartDate(country.getStartDate());
            //add country to list
            }
        else
        {
            addedCountry.setAttendees(emptyList); // make this empty list
            addedCountry.setAttendeeCount((Integer) countryObj.getAttendessCountinCountry().get(country));
            addedCountry.setName(country.getName());
            addedCountry.setStartDate(null);
            //add country to list
        }
            countries.add(addedCountry);

            // post data here



        }


    }


    private static TreeSet getSort (ArrayList list){
        TreeSet set =new TreeSet(list);
        return set;
    }

}
