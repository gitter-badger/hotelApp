package com.springapp.mvc.persistence;

import com.springapp.mvc.document.*;
import com.springapp.mvc.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 02-12-14
 * Time: 10:22 AM
 * Persiste documentos en MongoDB a una base de datos y una coleccion dadas por el usuario.
 */

@Service
public class PersistenceTemplate {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(PersistenceTemplate.class);

    /**
     * Guardar un objeto en una coleccion
     *
     * @param model      Objeto que se desea guardar
     * @param collection Nombre de la coleccion donde se desea guardarlo
     * @param version    Indica si se desea versionar el cambio o no
     */
    public void save(Documento model, String collection, Boolean version) {

        //Vemos si el documento que se desea persistir ya existe
        Documento documento = findById(model.getId(), collection);
        if (documento == null) {

            //Si el documento es nuevo, iniciamos la version en "1"
            model.getHeader().setVersion(1);

        } else {

            //Si queremos versionamiento, creamos la nueva version y aumentamos el contador de versiones
            if (version) {

                //Si el documento ya existe, creamos la version
                mongoTemplate.save(DocumentoVersion.mergeFromDocument(documento), collection.concat(Constants.VERSION_SUFFIX));

                //Y aumentamos la version del original en 1
                model.getHeader().setVersion(documento.getHeader().getVersion() + 1);

            } else {

                //Si no queremos versionamiento, mantenemos el numero de version actual sin modificar
                model.getHeader().setVersion(documento.getHeader().getVersion());
            }
        }

        //Persistimos documento original
        mongoTemplate.save(model, collection);
    }

    /**
     * Listar todos los elementos de una coleccion
     *
     * @param collection Coleccion donde se buscan los documentos
     * @return Lista de documentos encontrados
     */
    public List<Documento> findAll(String collection) {
        return mongoTemplate.findAll(Documento.class, collection);
    }

    /**
     * Busca un documento por su id-mongo en una coleccion
     *
     * @param id         Id MongoDB
     * @param collection Nombre de la coleccion
     * @return Documento encontrado
     */
    public Documento findById(String id, String collection) {
        return mongoTemplate.findById(id, Documento.class, collection);
    }

    /**
     * Elimina un documento de una coleccion
     *
     * @param documento  Dcomento que se desea eliminar
     * @param collection Nombre de la coleccion
     */
    public void remove(Documento documento, String collection) {
        mongoTemplate.remove(documento, collection);
    }

    /**
     * Encuentra y retorna una lista de documentos que cumplan con el query
     *
     * @param collection       Nombre de la coleccion contra la que se ejecuta el query
     * @param allRequestParams Criterios de busqueda para el query, en un <>Map(campo, valor)</>
     * @return Lista de documentos que cumplan con el query
     */
    public List<Documento> findQuery(String collection, Map<String, String> allRequestParams) {

        Query query = createQuery(allRequestParams);

        //Retornamos todos los documentos que cumplan el criterio
        logger.info("---> " + query.toString());
        return mongoTemplate.find(query, Documento.class, collection);
    }

    /**
     * added by kenji
     * Encuentra y retorna una lista de documentos que cumplan con el query con soporte paginador
     *
     * @param collection       Nombre de la coleccion contra la que se ejecuta el query
     * @param allRequestParams Criterios de busqueda para el query, en un <>Map(campo, valor)</>
     * @param pageNumber       Numero de pagina
     * @param numPerPage       Cantidad de elementos por pagina
     * @param sortField        campo por la que se ordena las respuestas
     * @param sortDirection    direccion del orden 'ASC' o 'DESC'
     * @return Lista de documentos que cumplan con el query
     */
    @Deprecated
    public List<Documento> findQuery(String collection, Map<String, String> allRequestParams, Integer pageNumber, Integer numPerPage,
                                     String sortField, String sortDirection) {

        Query query = createQuery(allRequestParams);
        //cursor = getCollection().find().skip(pageNumber > 0 ? ((pageNumber-1)*nPerPage) : 0).limit(nPerPage);
        query.skip(pageNumber > 0 ? ((pageNumber - 1) * numPerPage) : 0);
        query.limit(numPerPage);

        if (sortField != null) {
            Sort.Direction dir = Sort.Direction.DESC;

            if (sortDirection != null) {

                if (sortDirection.equalsIgnoreCase("ASC")) {
                    dir = Sort.Direction.ASC;
                }
            }

            Sort s = new Sort(new Sort.Order(dir, sortField));
            query.with(s);
        }

        //Retornamos todos los documentos que cumplan el criterio
        return mongoTemplate.find(query, Documento.class, collection);
    }

    /**
     * added by kenji
     * Encuentra y retorna una lista de documentos que cumplan con el query con soporte paginador y buscador para DataTable
     *
     * @param collection       Nombre de la coleccion contra la que se ejecuta el query
     * @param allRequestParams Criterios de busqueda para el query, en un <>Map(campo, valor)</>
     * @return Lista de documentos que cumplan con el query
     */
    public List<Documento> findQueryDataTable(String collection, Map<String, Object> allRequestParams) {

        DataTableParams param = new DataTableParams(allRequestParams);
        Map map = removeDataTableParams(allRequestParams);
        Query query = createQuery(map);

        //Armando el objeto para la busqueda
        Map<String, Object> searchF = param.getSearchFields();

        if (searchF != null) {
            Set<Map.Entry<String, Object>> e = searchF.entrySet();
            Criteria cr = new Criteria();
            Criteria[] cond = new Criteria[e.size()];

            int i = 0;
            for (Map.Entry<String, Object> en : e) {
                //cond[i] = Criteria.where(en.getKey()).regex(java.util.regex.Pattern.compile((String) en.getValue(), "i"));
                cond[i] = Criteria.where(en.getKey()).regex((String) en.getValue(), "i");
                i++;
            }

            cr.orOperator(cond);
            query.addCriteria(cr);
        }

        query.skip(param.getPageNumber() > 0 ? ((param.getPageNumber() - 1) * param.getiDisplayLength()) : 0);
        query.limit(param.getiDisplayLength());

        if (param.getiSortCol().get(0) != null) {
            Sort.Direction dir = Sort.Direction.DESC;

            if (param.getsSortDir().get(0) != null) {

                if (param.getsSortDir().get(0).equalsIgnoreCase("ASC")) {
                    dir = Sort.Direction.ASC;
                }
            }

            Sort s = new Sort(new Sort.Order(dir, param.getSortColumn()));
            query.with(s);
        }

        logger.info("-*****************************------------------Query dataTable " + query.toString());

        //Retornamos todos los documentos que cumplan el criterio
        return mongoTemplate.find(query, Documento.class, collection);
    }

    /**
     * added by kenji
     * Obtiene la cantidad de registros que cumplan con el query
     *
     * @param collection       Nombre de la coleccion contra la que se ejecuta el query
     * @param allRequestParams Criterios de busqueda para el query, en un <>Map(campo, valor)</>
     * @return
     */
    @Deprecated
    public Long getCountFindQuery(String collection, Map<String, String> allRequestParams) {
        Query query = createQuery(allRequestParams);

        //Retornamos todos los documentos que cumplan el criterio
        return mongoTemplate.count(query, collection);
    }

    /**
     * added by kenji
     * Obtiene la cantidad de registros que cumplan con el query para usar con dataTable
     *
     * @param collection       Nombre de la coleccion contra la que se ejecuta el query
     * @param allRequestParams Criterios de busqueda para el query, en un <>Map(campo, valor)</>
     * @return
     */
    public Long getCountFindDataTableQuery(String collection, Map<String, Object> allRequestParams) {

        DataTableParams param = new DataTableParams(allRequestParams);
        Query query = createQuery(removeDataTableParams(allRequestParams));

        //Armando el objeto para la busqueda
        Map<String, Object> searchF = param.getSearchFields();

        if (searchF != null) {
            Set<Map.Entry<String, Object>> e = searchF.entrySet();
            Criteria cr = new Criteria();
            Criteria[] cond = new Criteria[e.size()];

            int i = 0;
            for (Map.Entry<String, Object> en : e) {
                //cond[i] = Criteria.where(en.getKey()).regex(java.util.regex.Pattern.compile((String) en.getValue(), "i"));
                cond[i] = Criteria.where(en.getKey()).regex((String) en.getValue(), "i");
                i++;
            }

            cr.orOperator(cond);
            query.addCriteria(cr);
        }

        logger.info("-*****************************------------------query contador dataTable " + query.toString());
        //Retornamos todos los documentos que cumplan el criterio
        return mongoTemplate.count(query, collection);
    }

    /**
     * Crea el Objeto query a partir de los criterios de busqueda
     *
     * @param allRequestParams Criterios de busqueda para el query, en un <>Map(campo, valor)</>
     * @return
     */
    public Query createQuery(Map<String, String> allRequestParams) {
        Map<String, String> paraAnd = new HashMap<String, String>();
        Map<String, List<String>> paraIn = new HashMap<String, List<String>>();
        Map<String, String> paraOperators = new HashMap<>();//Operadores mongo
        Map<String, Map<String, String>> paraElementMatchAnd = new HashMap<String, Map<String, String>>();

        //Procesamos el map
        for (Map.Entry<String, String> entry : allRequestParams.entrySet()) {
            Boolean shouldGoToIn = false;//Determina si este entry del map es parte de un array para IN
            Boolean shouldGoToElementMatchAnd = false; //para busquedas dentro de Array
            Boolean shouldGoToOperators = false; //para busquedas dentro de Array

            //Campo y valor
            String campo = entry.getKey();
            String valor = entry.getValue();

            //Si son campos de paginacion se retira del query
            if (campo.equalsIgnoreCase("pageNumber") || campo.equalsIgnoreCase("nPerPage")
                    || campo.equalsIgnoreCase("sortField") || campo.equalsIgnoreCase("sortDirection")) {
                continue;
            }

            //El key termina en "_X" donde X es numerico? --> Va a IN
            if (campo.contains("_")) {
                int endIndex = campo.lastIndexOf("_");
                //Sacamos lo que esta antes y despues del ultimo "_"
                String preCampo = campo.substring(0, endIndex);
                String postCampo = campo.substring(endIndex + 1);
                if (ModelStringUtils.containsOnlyNumbers(postCampo)) {
                    //Si solo hay numeros despues del ultimo "_", agregamos el key-value pair al map de campos para IN
                    List<String> list = paraIn.get(preCampo);
                    if (list != null) {
                        list.add(valor);
                        paraIn.put(preCampo, list);
                    } else {
                        List<String> list1 = new ArrayList<String>();
                        list1.add(valor);
                        paraIn.put(preCampo, list1);
                    }

                    shouldGoToIn = true;
                }
            } else if (campo.contains("*")) {//El key en medio tiene un X* --> Va en un $elemMatch para busquedas dentro de Array
                int endIndex = campo.lastIndexOf("*");
                //Sacamos lo que esta antes y despues del ultimo "*"
                String preCampo = campo.substring(0, endIndex);
                String postCampo = "";
                logger.info("endIndex " + endIndex);
                logger.info("campo.length() " + campo.length());
                if (campo.length() != endIndex + 1)
                    postCampo = campo.substring(endIndex + 2);
                /*System.out.println("preCampo " + preCampo);
                System.out.println("postCampo " + postCampo);*/
                Map<String, String> aux = paraElementMatchAnd.get(preCampo);

                if (aux != null)
                    aux.put(postCampo, valor);
                else {
                    aux = new HashMap<String, String>();
                    aux.put(postCampo, valor);
                }
                paraElementMatchAnd.put(preCampo, aux);
                shouldGoToElementMatchAnd = true;
            } else if (valor.contains("$")) {//Si en el valor encontramos $ es un operador
                shouldGoToOperators = true;
                paraOperators.put(campo, valor);
            }

            //Si no es para IN, asumimos que es para AND
            if (!shouldGoToIn && !shouldGoToElementMatchAnd && !shouldGoToOperators) {
                paraAnd.put(campo, valor);
            }

        }

        //Creamos el query
        Query query = new Query();

        //Agregamos los criterios para las consultas AND
        for (Map.Entry<String, String> entry : paraAnd.entrySet()) {

            if(entry.getValue().contains("-/")){
                query.addCriteria(Criteria.where(entry.getKey()).regex(entry.getValue().replace("-/", "").replace("+", " ")));
            }else if(entry.getValue().contains("-*")){
                entry.setValue(entry.getValue().replace("-*",""));
                String[] fechas = entry.getValue().split(";");
                SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", new Locale("es","ar"));
                try {
                    query.addCriteria(Criteria.where(entry.getKey()).gte(ISO8601DATEFORMAT.parse(fechas[0])).lte(ISO8601DATEFORMAT.parse(fechas[1])));
                }catch (Exception e){
                    logger.info("ERROR AL PARSEAR :"+e.getMessage());
                    query.addCriteria(Criteria.where(entry.getKey()).gte(fechas[0]).lte(fechas[1]));
                }
            }else if (entry.getValue().equalsIgnoreCase("false") || entry.getValue().equalsIgnoreCase("true")) {
                    query.addCriteria(Criteria.where(entry.getKey()).is(Boolean.valueOf(entry.getValue())));
                } else {
                    query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
                }
        }

        //Agregamos los criterios para los operadores
        for (Map.Entry<String, String> entry : paraOperators.entrySet()) {


            Integer val = Integer.valueOf(entry.getValue().substring(entry.getValue().indexOf(":") + 1));

            if (entry.getValue().contains("$gt")) {
                query.addCriteria(Criteria.where(entry.getKey()).gt(val));
            } else if (entry.getValue().contains("$gte")) {
                query.addCriteria(Criteria.where(entry.getKey()).gte(val));
            } else if (entry.getValue().contains("$lt")) {
                query.addCriteria(Criteria.where(entry.getKey()).lt(val));
            } else if (entry.getValue().contains("$lte")) {
                query.addCriteria(Criteria.where(entry.getKey()).lte(val));
            } else if (entry.getValue().contains("$ne")) {
                query.addCriteria(Criteria.where(entry.getKey()).ne(val));
            }
        }

        //Agregamos los criterios para las consultas IN
        for (Map.Entry<String, List<String>> entry : paraIn.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).in(entry.getValue()));
        }

        //Agregamos los criterios para las consultas elementMatch AND
        paraElementMatchAnd.entrySet();
        for (Map.Entry<String, Map<String, String>> entryMain : paraElementMatchAnd.entrySet())

            for (Map.Entry<String, String> entry : entryMain.getValue().entrySet()) {

                if (entry.getKey().length() > 0) {

                    String val = entry.getValue();

                    if (!val.contains("$or")) {
                        query.addCriteria(Criteria.where(entryMain.getKey()).elemMatch(Criteria.where(entry.getKey()).is(entry.getValue())));
                    } else {
                        Criteria elemMa = new Criteria();
                        List<Criteria> criterias = new ArrayList();
                        String exp1 = val.substring(val.indexOf(":") + 1);
                        String[] exp = exp1.split(";");

                        for (String expT : exp) {
                            Object valD = expT;

                            if (expT.equalsIgnoreCase("null")) {
                                valD = null;
                            }

                            criterias.add(Criteria.where(entry.getKey()).is(valD));
                        }

                        Criteria[] criArray = criterias.toArray(new Criteria[0]);
                        query.addCriteria(Criteria.where(entryMain.getKey()).elemMatch(
                                elemMa.orOperator(criArray)));
                    }
                } else
                    query.addCriteria(Criteria.where(entryMain.getKey()).in(entry.getValue()));
            }
        logger.info("Query" + query.toString());
        return query;
    }

    /**
     * Extrae de un mapa de parametros los parametros que le corresponden al dataTable
     *
     * @param map
     * @return mapa sin parametros del dataTable
     */
    private Map<String, String> removeDataTableParams(Map map) {
        Map<String, String> res = map;
        PropertyDescriptor[] propD = BeanUtils.getPropertyDescriptors(DataTableParams.class);

        for (PropertyDescriptor des : propD) {

            if (des.getPropertyType() != List.class && des.getPropertyType() != Map.class) {
                res.remove(des.getName());
            } else {

                if (!des.getName().equalsIgnoreCase("sColumns")) {
                    String field = des.getName();

                    if (!field.contains("_")) {
                        field = des.getName() + "_";
                    }

                    int index = 0;

                    while (res.containsKey(field + index)) {//Eliminando recursivamente
                        res.remove(field + index);
                        index++;
                    }
                } else {
                    res.remove(des.getName());
                }
            }
        }

        return res;
    }
}
