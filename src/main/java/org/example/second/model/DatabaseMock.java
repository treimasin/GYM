package org.example.second.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseMock {

    public List<GymClient> loadClient(){
        List<GymClient> list = new ArrayList<>();
        list.add(new GymClient("Kristo", "Treiman", "12345"));
        list.add(new GymClient("Rauno", "Kurg", "54321"));
        list.add(new GymClient("Mauno", "Kaupo", "34512"));
        list.add(new GymClient("Markus", "Tarkus", "31254"));
        list.add(new GymClient("Ivo", "Tamm", "37435"));
        list.add(new GymClient("Jaanus", "Albert", "64332"));
        return list;
    }

}