package com.springapp.mvc.service;

import com.springapp.mvc.model.Habitacion;

import java.util.List;
import java.util.Map;

/**
 * Created by vsantos on 03/11/2016.
 */
public interface HabitacionService {

    public Habitacion save(Habitacion habitacion) throws Exception;

    public Habitacion update(Habitacion habitacion) throws Exception;

    public boolean delete(Habitacion habitacion) throws Exception;

    public List<Habitacion> findAll(Map<String, Object> map) throws Exception;
}
