package com.springapp.mvc.service;

import com.springapp.mvc.model.Habitacion;

/**
 * Created by vsantos on 03/11/2016.
 */
public interface HabitacionService {

    public Habitacion save(Habitacion habitacion) throws Exception;

    public Habitacion update(Habitacion habitacion) throws Exception;
}
