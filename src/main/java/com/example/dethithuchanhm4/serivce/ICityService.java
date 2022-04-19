package com.example.dethithuchanhm4.serivce;

import java.util.Optional;

public interface ICityService<City> {
    Iterable<City> findAll();

    Optional<City> findById(Long id);

    City save(City city);

    void removeById(Long id);
}
