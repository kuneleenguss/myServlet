package com.example.myservlet.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Ivan", "Petrov", 120000));
        model.put(2, new User("Denis", "Ivanov", 20000));
        model.put(3, new User("Askhab", "Tamaev", 320000));
    }

    public void add(User user, int id) {

        model.put(id, user);
    }

    public void update(User user, int id) {

        model.replace(id, user);
    }

    public Map<Integer, User> getFromList() {

        return model;
    }

    public Map<Integer, User> getById(int id) {

        if (model.containsKey(id))
            return Map.of(id, model.get(id));
        else
            return null;
    }

    public Map<Integer, User> remove(int id) {

        return Map.of(id, model.remove(id));
    }
}
