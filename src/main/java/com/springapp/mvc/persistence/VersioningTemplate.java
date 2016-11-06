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
public class VersioningTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * Busca todas las versiones de un documento dado en una coleccion dada
	 *
	 * @param collection Nombre de la coleccion donde esta el documento original
	 * @param id         ID del documento original
	 * @return Lista de versiones del documento
	 */
	public List<DocumentoVersion> findVersions(String collection, String id) {

		//Creamos el query
		Query query = new Query();

		//Agregamos el criterio para la consulta
		query.addCriteria(Criteria.where("_id._id").is(id));
		query.fields().include("_id._version");

		//Retornamos todos los documentos que cumplan el criterio
		return mongoTemplate.find(query, DocumentoVersion.class, collection.concat(Constants.VERSION_SUFFIX));
	}

	/**
	 * Busca una version especifica de un documento dado en una coleccion dada
	 *
	 * @param collection Nombre de la coleccion donde se busca el documento
	 * @param id         ID del documento
	 * @param version    Numero de version que se desea recuperar
	 * @return Documento cuyo id y version son iguales a los buscados
	 */
	public DocumentoVersion findVersion(String collection, String id, Integer version) {

		//Creamos el query
		Query query = new Query();

		//Agregamos el criterio para la consulta
		query.addCriteria(Criteria.where("_id._id").is(id));
		query.addCriteria(Criteria.where("_id._version").is(version));

		//Retornamos todos los documentos que cumplan el criterio
		List<DocumentoVersion> versions = mongoTemplate.find(
				query, DocumentoVersion.class, collection.concat(Constants.VERSION_SUFFIX)
		);
		return versions.size() > 0 ? versions.get(0) : null;
	}
}
