package com.solvd.service;



import com.solvd.serviceinterface.AirlineServiceInterface;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class AirlineService implements AirlineServiceInterface {

    private SqlSessionFactory sqlSessionFactory;
    public AirlineService() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml")) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }
    private AirlineRepository airlineRepository;
    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }
    @Override
    public void create(Airline airline) {

        airlineRepository.create(airline);

    }
    @Override
    public Optional<Airline> getById(Long id) {

        return airlineRepository.loadById(id);
    }
    @Override
    public List<Airline> getAll() {

        return airlineRepository.loadAll();
    }
}
