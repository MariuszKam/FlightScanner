package com.solvd.service;



import com.solvd.serviceinterface.AirportServiceInterface;
import com.solvd.serviceinterface.ServiceInterface;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class AirportService implements AirportServiceInterface {
    private SqlSessionFactory sqlSessionFactory;


    private  AirportRepository airportRepository;

    public AirportService() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml")) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }
    @Override
    public void create(Airport airport) {

        airportRepository.create(airport);

    }
    @Override
    public Optional<Airport> getById(Long id) {

        return airportRepository.loadById(id);
    }
    @Override
    public List<Airport> getAll() {

        return airportRepository.loadAll();
    }
}
