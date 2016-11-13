package com.springapp.mvc.service.impl;

import com.springapp.mvc.document.Documento;
import com.springapp.mvc.document.bean.FormularioHeader;
import com.springapp.mvc.document.bean.FormularioSecurity;
import com.springapp.mvc.document.enums.FormularioCodigo;
import com.springapp.mvc.document.enums.FormularioTipo;
import com.springapp.mvc.model.Habitacion;
import com.springapp.mvc.persistence.PersistenceTemplate;
import com.springapp.mvc.service.HabitacionService;
import com.springapp.mvc.utils.JsonResult;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by vsantos on 03/11/2016.
 */
@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Autowired
    private PersistenceTemplate persistenceTemplate;

    @Override
    public Habitacion save(Habitacion habitacion) throws Exception {
        try {
            Habitacion habitacion1 = new Habitacion();
            Documento model = new Documento();
            Documento modelRespuesta = new Documento();
            String id = UUID.randomUUID().toString();
            FormularioTipo tipoFormulario = FormularioTipo.HAB;
            FormularioHeader header = new FormularioHeader(FormularioCodigo.HABITACION, tipoFormulario, 1, true);
            FormularioSecurity security = new FormularioSecurity("vsantos", new Date());

            habitacion.setId(id);
            model.setId(id);
            model.setHeader(header);
            model.setData(habitacion);
            model.setSecurity(security);
            model.setBitacora(null);

            persistenceTemplate.save(model, "habitacion", true);
            JsonResult jsonResult = new JsonResult(true, model);
            if (jsonResult.getSuccess()) {
                ObjectMapper objectMapper = new ObjectMapper();
                modelRespuesta = objectMapper.convertValue(jsonResult.getResult(), Documento.class);

                habitacion1 = objectMapper.convertValue(modelRespuesta.getData(), Habitacion.class);
                return habitacion1;

            } else {
                throw new Exception(jsonResult.getResult().toString());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Habitacion update(Habitacion habitacion) throws Exception {
        try {
            Habitacion habitacion1 = new Habitacion();
            Documento modelRespuesta = new Documento();
            //Recuperar el archivo por el id
            Documento model = persistenceTemplate.findById(habitacion.getId(), "habitacion");
            if (model != null) {
                model.setData(habitacion);
            }else{
                throw new Exception("No se encontro el documento a modificarse: " + habitacion.getId());
            }
            persistenceTemplate.save(model, "habitacion", true);
            JsonResult jsonResult = new JsonResult(true, model);
            if (jsonResult.getSuccess()) {
                ObjectMapper objectMapper = new ObjectMapper();
                modelRespuesta = objectMapper.convertValue(jsonResult.getResult(), Documento.class);

                habitacion1 = objectMapper.convertValue(modelRespuesta.getData(), Habitacion.class);
                return habitacion1;

            } else {
                throw new Exception(jsonResult.getResult().toString());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Habitacion habitacion) throws Exception {
        try {
            Habitacion habitacion1 = new Habitacion();
            Documento modelRespuesta = new Documento();
            //Recuperar el archivo por el id
            Documento model = persistenceTemplate.findById(habitacion.getId(), "habitacion");
            if (model != null) {
                model.setData(habitacion);
            }else{
                throw new Exception("No se encontro el documento a eliminarse: " + habitacion.getId());
            }
            persistenceTemplate.remove(model, "habitacion");
            JsonResult jsonResult = new JsonResult(true, model);
            if (jsonResult.getSuccess()) {
                return true;
            } else {
                throw new Exception(jsonResult.getResult().toString());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Habitacion> findAll(Map<String, Object> map) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Habitacion> habitacionList = new ArrayList<Habitacion>();
        //Formamos el string con los campos y valores para la consulta
        Map<String, String> allRequestParams = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            allRequestParams.put(entry.getKey(), entry.getValue().toString());
        }
        List<Documento> documentos = persistenceTemplate.findQuery("habitacion", allRequestParams);
        for (Documento doc : documentos) {
            habitacionList.add(objectMapper.convertValue(doc.getData(), Habitacion.class));
        }
        return habitacionList;
    }
}
