package com.springapp.mvc.utils;

import java.util.*;

/**
 *
 * Clase que representa los parametros enviados por el plugin DataTable de jquery
 */
public class DataTableParams {

    private Integer iDisplayStart;
    private Integer iDisplayLength;
    private Integer iColumns;
    private Integer iSortingCols;
    private String sSearch;
    private String sEcho;
    private Boolean bRegex;
    private List<String> sColumns;
    private Map<String,Boolean> bSearchable;
    private List<String> sSearch_;
    private List<Boolean> bRegex_;
    private List<Boolean> bSortable;
    private List<Integer> iSortCol;
    private List<String> sSortDir;
    private List<String> mDataProp;

    public DataTableParams() {
    }

    //Constructor que parsea el hash map de parametros
    public DataTableParams(Map<String, Object> map) {
        this.bSearchable = new HashMap<>();
        this.sSearch_ = new ArrayList<>();
        //this.bRegex_ = new ArrayList<>();
        this.bSortable = new ArrayList<>();
        this.iSortCol = new ArrayList<>();
        this.sSortDir = new ArrayList<>();
        this.sColumns = new ArrayList<>();
        //this.mDataProp = new ArrayList<>();

        if (map.containsKey("iDisplayStart")) {
            this.iDisplayStart = Integer.parseInt((String) map.get("iDisplayStart"));
        }

        if (map.containsKey("iDisplayLength")) {
            this.iDisplayLength = Integer.parseInt((String) map.get("iDisplayLength"));
        }

        if (map.containsKey("iColumns")) {
            this.iColumns = Integer.parseInt((String) map.get("iColumns"));
        }

        if (map.containsKey("iSortingCols")) {
            this.iSortingCols = Integer.parseInt((String) map.get("iSortingCols"));
        }

        if (map.containsKey("sSearch")) {
            this.sSearch = (String) map.get("sSearch");
        }

        if (map.containsKey("sEcho")) {
            this.sEcho = (String) map.get("sEcho");
        }

        if (map.containsKey("bRegex")) {
            this.bRegex = Boolean.valueOf((String) map.get("bRegex"));
        }

        if (map.containsKey("sColumns")) {//atributo de las columnas en un solo string por eso se parsea a una lista
            this.sColumns.addAll(Arrays.asList(map.get("sColumns").toString().split(",")));
        }

        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> e : set) {

            if (e.getKey().contains("bSearchable_")) {
                this.bSearchable.put(e.getKey(), Boolean.valueOf((String) e.getValue()));
            } else if (e.getKey().contains("bSortable_")) {
                this.bSortable.add(Boolean.valueOf((String) e.getValue()));
            } else if (e.getKey().contains("iSortCol_")) {
                this.iSortCol.add(Integer.valueOf((String) e.getValue()));
            } else if (e.getKey().contains("sSortDir_")) {
                this.sSortDir.add((String) e.getValue());
            }
        }

    }

    public Integer getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(Integer iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public Integer getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(Integer iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public Integer getiColumns() {
        return iColumns;
    }

    public void setiColumns(Integer iColumns) {
        this.iColumns = iColumns;
    }

    public Integer getiSortingCols() {
        return iSortingCols;
    }

    public void setiSortingCols(Integer iSortingCols) {
        this.iSortingCols = iSortingCols;
    }

    public String getsSearch() {
        return sSearch;
    }

    public void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public Boolean getbRegex() {
        return bRegex;
    }

    public void setbRegex(Boolean bRegex) {
        this.bRegex = bRegex;
    }

    public Map<String, Boolean> getbSearchable() {
        return bSearchable;
    }

    public void setbSearchable(Map<String, Boolean> bSearchable) {
        this.bSearchable = bSearchable;
    }

    public List<String> getsSearch_() {
        return sSearch_;
    }

    public void setsSearch_(List<String> sSearch_) {
        this.sSearch_ = sSearch_;
    }

    public List<Boolean> getbRegex_() {
        return bRegex_;
    }

    public void setbRegex_(List<Boolean> bRegex_) {
        this.bRegex_ = bRegex_;
    }

    public List<Boolean> getbSortable() {
        return bSortable;
    }

    public void setbSortable(List<Boolean> bSortable) {
        this.bSortable = bSortable;
    }

    public List<Integer> getiSortCol() {
        return iSortCol;
    }

    public void setiSortCol(List<Integer> iSortCol) {
        this.iSortCol = iSortCol;
    }

    public List<String> getsSortDir() {
        return sSortDir;
    }

    public void setsSortDir(List<String> sSortDir) {
        this.sSortDir = sSortDir;
    }

    public List<String> getmDataProp() {
        return mDataProp;
    }

    public void setmDataProp(List<String> mDataProp) {
        this.mDataProp = mDataProp;
    }

    public List<String> getsColumns() {
        return sColumns;
    }

    public void setsColumns(List<String> sColumns) {
        this.sColumns = sColumns;
    }

    /**
     * Obtiene el numero de pagina correspondiente
     *
     * @return
     */
    public Integer getPageNumber() {
        //calculando numero de pagina
        Integer pageNumber = null;

        if (iDisplayStart != null && iDisplayLength != null && iDisplayLength > 0) {

            if (iDisplayStart <= 0) {
                pageNumber = 1;
            } else {
                pageNumber = (iDisplayStart / iDisplayLength) + 1;
            }
        }

        return pageNumber;
    }

    /**
     * Obtiene el campo por la que se realiza el sort
     *
     * @return
     */
    public String getSortColumn() {
        //Procesando la columna que para el sort
        String sortCol = null;

        if (iSortCol.size()>0 && iSortCol.get(0) != null && sColumns != null && sColumns.size()>0) {

            sortCol = sColumns.get(iSortCol.get(0));
        }

        return sortCol;
    }

    /**
     * Obtiene la lista de los campos por la que se realiza la busqueda
     *
     * @return campos de busqueda en Map
     */
    public Map<String, Object> getSearchFields() {

        if (sSearch != null && !sSearch.isEmpty()) {
            Map<String, Object> m = new HashMap<>();

            for (int i = 0; i < sColumns.size(); i++) {

                if (bSearchable.get("bSearchable_" + i) != null && bSearchable.get("bSearchable_" + i)) {
                    m.put(sColumns.get(i), sSearch);
                }
            }

            return m;
        }

        return null;
    }
}
