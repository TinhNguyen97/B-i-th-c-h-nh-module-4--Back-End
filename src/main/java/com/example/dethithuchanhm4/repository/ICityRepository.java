package com.example.dethithuchanhm4.repository;

import com.example.dethithuchanhm4.controller.model.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends PagingAndSortingRepository<City,Long> {
}
