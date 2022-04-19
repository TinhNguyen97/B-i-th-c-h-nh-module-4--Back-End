package com.example.dethithuchanhm4.controller;

import com.example.dethithuchanhm4.controller.model.City;
import com.example.dethithuchanhm4.serivce.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping()
    public ResponseEntity<Iterable<City>> findAll() {
        Iterable<City> cities = cityService.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cityOptional.get(), HttpStatus.CREATED);
    }


    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city) {
        City newCity = new City(city.getName(), city.getCountry());
        cityService.save(newCity);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        Optional<City> optionalCity = cityService.findById(id);
        if (!optionalCity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        City newCity = optionalCity.get();

        newCity.setId(id);
        newCity.setName(city.getName());
        newCity.setCountry(city.getCountry());
        cityService.save(newCity);
        return new ResponseEntity<>(newCity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id) {
        Optional<City> optionalCity = cityService.findById(id);
        if (!optionalCity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        cityService.removeById(id);
        return new ResponseEntity<>(optionalCity.get(), HttpStatus.OK);
    }
}
