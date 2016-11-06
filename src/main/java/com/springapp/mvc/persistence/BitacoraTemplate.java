package com.springapp.mvc.persistence;

import com.springapp.mvc.document.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class BitacoraTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Documento> findBitacoras(String collection, String id) {

		//Creamos el query
		Query query = new Query();

		//Agregamos el criterio para la consulta
		query.addCriteria(Criteria.where("_id").is(id));
		query.fields().include("bitacora.estado");

		//Retornamos todos los documentos que cumplan el criterio
		return mongoTemplate.find(query, Documento.class, collection);
	}
}
