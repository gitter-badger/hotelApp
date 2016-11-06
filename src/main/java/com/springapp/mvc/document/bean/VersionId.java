package com.springapp.mvc.document.bean;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 02/07/2015
 * Time: 11:47 AM
 * Bean del ID de los documentos versionados.
 */
public class VersionId {

	private String _id;

	private Integer _version;

	public VersionId() {
	}

	public VersionId(String id, Integer version) {
		this._id = id;
		this._version = version;
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public Integer getVersion() {
		return _version;
	}

	public void setVersion(Integer version) {
		this._version = version;
	}

	@Override
	public String toString() {
		return "VersionId{" +
				"id='" + _id + '\'' +
				", version=" + _version +
				'}';
	}
}
