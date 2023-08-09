package com.example.validator.repository;


import com.example.validator.model.CurrencyConverter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConverterRepository extends CrudRepository<CurrencyConverter, Long> {

    Boolean existsByCurrencyCode(String currencyCode);

}