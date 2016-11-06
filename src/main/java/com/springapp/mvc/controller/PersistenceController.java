package com.springapp.mvc.controller;

import com.springapp.mvc.persistence.PersistenceTemplate;
import com.springapp.mvc.persistence.VersioningTemplate;
import com.springapp.mvc.utils.JsonResult;
import com.springapp.mvc.document.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping(value = "/documento/")
public class PersistenceController {

	private final Logger logger = LoggerFactory.getLogger(PersistenceController.class);

	@Autowired
	private PersistenceTemplate persistenceTemplate;

	@Autowired
	VersioningTemplate versioningTemplate;

	/**
	 * Servicio para Crear o modificar un Documento
	 *
	 * @param model      Documento que se desea persistir
	 * @param collection Nombre de la coleccion en la que se desea persistir el documento
	 * @return Respuesta json con el documento persistido
	 */
	@RequestMapping(value = "{collection}", method = RequestMethod.POST, headers = "Content-Type=application/json", produces = "application/json")
	@ResponseBody
	public JsonResult create(
			@RequestBody Documento model,
			@PathVariable String collection,
			@RequestParam(value = "version", required = false, defaultValue = "true") Boolean version
	) {
		System.out.println("-----------------------> begin create .....");

		try {
			logger.info("Consume servicio: SPF/documento/" + collection);
			persistenceTemplate.save(model, collection, version);
			JsonResult jsonResult =new JsonResult(true, model);
			System.out.println("===============================> jsonResult.toString() = " + jsonResult.toString());
			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, e.getMessage());
		}
	}

	/**
	 * Servicio para listar todos los documentos de un coleccion
	 *
	 * @param collection Nombre de la coleccion donde se buscan los documentos
	 * @return Respuesta json con la lista de documentos encontrados
	 */
	@RequestMapping(value = "{collection}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult list(@PathVariable String collection) {

		try {
			logger.info("Consume servicio: SPF/documento/" + collection);

			//Si existen documentos, los retornamos
			List<Documento> list = persistenceTemplate.findAll(collection);
			if (list.size() > 0) {
				return new JsonResult(true, list);
			}

			return new JsonResult(false, "No se encontraron documentos");

		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

	/**
	 * Servicio para recuperar un documento por su id
	 *
	 * @param id         ID mongo del documento
	 * @param collection Nombre de la coleccion donde se busca el documento
	 * @return Respuesta json con el documento recuperado
	 */
	@RequestMapping(value = "{collection}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult get(@PathVariable String collection, @PathVariable String id) {

		try {
			logger.info("Consume servicio: SPF/documento/" + collection + "/" + id);

			//Si existe el documento, lo retornamos
			Documento documento = persistenceTemplate.findById(id, collection);
			if (documento != null) {
				return new JsonResult(true, documento);
			}

			return new JsonResult(false, "No se ha encontrado el documento");

		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

	/**
	 * Servicio para recuperar todas las versiones de un documento
	 *
	 * @param collection Coleccion donde esta el documento
	 * @param id         Id mongo del documento
	 * @return Respuesta json con la lista de versiones
	 */
	@RequestMapping(value = "{collection}/{id}/versions", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult versions(
			@PathVariable String collection,
			@PathVariable String id,
			@RequestParam(value = "version", required = false) Integer version
	) {
		try {
			logger.info("Consume servicio: SPF/documento/" + collection + "/" + id + "/versions");

			//Si existe el documento, lo retornamos
			Documento documento = persistenceTemplate.findById(id, collection);
			if (documento != null) {
				if (version != null) {
					//Si nos piden una version en particular
					return new JsonResult(true, versioningTemplate.findVersion(collection, id, version));
				} else {
					//Si nos piden la lista de todas las versiones
					return new JsonResult(true, versioningTemplate.findVersions(collection, id));
				}
			}

			return new JsonResult(false, "No se ha encontrado el documento");

		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

	/**
	 * Servicio para eliminar un documento de una coleccion, por el id del documento
	 *
	 * @param id         ID mongo del documento que se desea eliminar
	 * @param collection Nombre de la coleccion donde se busca el documento para su eliminacion
	 * @return Respuesta json con el mensaje de confirmacion de la eliminacion
	 */
	@RequestMapping(value = "{collection}/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public JsonResult delete(@PathVariable String id, @PathVariable String collection) {

		try {
			logger.info("Consume servicio: SPF/documento/" + collection + "/" + id);

			//Si el documento existe en la coleccion, lo eliminamos
			Documento documento = persistenceTemplate.findById(id, collection);
			if (documento != null) {
				persistenceTemplate.remove(documento, collection);
				Documento documento1 = persistenceTemplate.findById(id, collection);
				if (documento1 == null) {
					return new JsonResult(true, "Se ha eliminado el documento");
				}
			}

			return new JsonResult(false, "No se ha eliminado el documento");

		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

	/**
	 * Realiza una consulta con los parametros dados, en la coleccion dada
	 *
	 * @param collection       Nombre de la coleccion donde se realiza la consulta
	 * @param allRequestParams Parametros para la consulta
	 * @return Lista de documentos encontrados con la consulta
	 */
	@RequestMapping(value = "{collection}/query", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult query(@PathVariable String collection, @RequestParam(required = true) Map<String, String> allRequestParams) {
		try {
			logger.info("Consume servicio: SPF/documento/" + collection + "/query" + allRequestParams.toString());
			return new JsonResult(true, persistenceTemplate.findQuery(collection, allRequestParams));
		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

	/**
	 * Servicio con query soporte con paginador
	 *
	 * @param collection
	 * @param allRequestParams
	 * @param sortField
	 * @param sortDirection
	 * @param pageNumber
	 * @param nPerPage
	 * @return
	 */
	@RequestMapping(value = "{collection}/query_pagination", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult query(@PathVariable String collection, @RequestParam(required = true) Map<String, String> allRequestParams,
							@RequestParam(value = "sortField", required = false) String sortField,
							@RequestParam(value = "sortDirection", required = false) String sortDirection,
							@RequestParam("pageNumber") Integer pageNumber,
							@RequestParam("nPerPage") Integer nPerPage) {
		try {
			logger.info("Consume servicio: SPF/documento/" + collection + "/query_pagination" + allRequestParams.toString());
			return new JsonResult(true, persistenceTemplate.findQuery(collection, allRequestParams, pageNumber, nPerPage, sortField, sortDirection));
		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

	@RequestMapping(value = "{collection}/dataTable", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult queryDataTable(@PathVariable String collection, @RequestParam(required = true) Map<String, Object> allRequestParams) {
		try {
			logger.info("Consume servicio: SPF/documento/" + collection + "/dataTable?" + allRequestParams.toString());

			return new JsonResult(true, persistenceTemplate.findQueryDataTable(collection, allRequestParams));
		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

	/**
	 * Servicio para devolver cantidad de registros
	 *
	 * @param collection
	 * @param allRequestParams
	 * @return
	 */
	@RequestMapping(value = "{collection}/count", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult queryCount(@PathVariable String collection, @RequestParam(required = true) Map<String, String> allRequestParams) {
		try {
			logger.info("Consume servicio: SPF/documento/" + collection + "/count" + allRequestParams.toString());
			return new JsonResult(true, persistenceTemplate.getCountFindQuery(collection, allRequestParams));
		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

	/**
	 * Servicio para devolver cantidad de registros
	 *
	 * @param collection
	 * @param allRequestParams
	 * @return
	 */
	@RequestMapping(value = "{collection}/countDataTable", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult queryCountDataTable(@PathVariable String collection, @RequestParam(required = true) Map<String, Object> allRequestParams) {
		try {
			logger.info("Consume servicio: SPF/documento/" + collection + "/countDataTable" + allRequestParams.toString());
			return new JsonResult(true, persistenceTemplate.getCountFindDataTableQuery(collection, allRequestParams));
		} catch (Exception e) {
			return new JsonResult(false, e.getMessage());
		}
	}

    /**
     * Servicio para la busqueda de documentos por nro de Manifiesto
     *
     * @param nroManifiesto
     *
     * @return Cabecera de documento de embarque.
     */
    @RequestMapping(value = "/nroManifiesto/{nroManifiesto}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult findDocumentoByNroManifiesto(@PathVariable String nroManifiesto) {
        logger.debug("BUSCAR: {}", nroManifiesto);
        Map consulta=new HashMap();
        consulta.put("data.datosGenerales.identificacionManifiesto.numeroManifiesto",nroManifiesto);
        List<Documento> documentosList = persistenceTemplate.findQuery("impManifiestosCarga", consulta);
        if(!documentosList.isEmpty()){
            return new JsonResult(true, documentosList.get(0));
        }else{
            return new JsonResult(false, "No existe Documento");
        }
    }
}
