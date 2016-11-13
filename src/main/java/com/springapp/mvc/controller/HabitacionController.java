package com.springapp.mvc.controller;

import com.springapp.mvc.model.Habitacion;

import com.springapp.mvc.service.HabitacionService;
import com.springapp.mvc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "habitacion", method = RequestMethod.DELETE, headers = "Content-Type=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<JsonResult> delete(@RequestBody Habitacion habitacion) {
        try{
            String respuestaMensaje = "";
            if(habitacionService.delete(habitacion)){
                respuestaMensaje = "Se elimino la habitacion correctamente";
            }
            return new ResponseEntity<>(new JsonResult(true, respuestaMensaje), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(new JsonResult(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/habitacion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public JsonResult findAll(@RequestParam Map<String, Object> map) {
        try {
            List<Habitacion> habitacionList = habitacionService.findAll(map);
            if (habitacionList.size() > 0) {
                return new JsonResult(true, habitacionList);
            } else {
                return new JsonResult(false, "No se encontraron registros wiio wiiiooo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }
}
