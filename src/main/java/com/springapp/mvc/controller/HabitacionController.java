package com.springapp.mvc.controller;

import com.springapp.mvc.model.Habitacion;

import com.springapp.mvc.service.HabitacionService;
import com.springapp.mvc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vsantos on 03/11/2016.
 */
@Controller
@RequestMapping(value = "/rest/")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @RequestMapping(value = "habitacion", method = RequestMethod.POST, headers = "Content-Type=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<JsonResult> insert(@RequestBody Habitacion habitacion) {
        try{
            return new ResponseEntity<>(new JsonResult(true, habitacionService.save(habitacion)), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(new JsonResult(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "habitacion", method = RequestMethod.PUT, headers = "Content-Type=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<JsonResult> update(@RequestBody Habitacion habitacion) {
        try{
            return new ResponseEntity<>(new JsonResult(true, habitacionService.update(habitacion)), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(new JsonResult(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
