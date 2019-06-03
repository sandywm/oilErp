package com.oil.module;

/**
 * Dba04Id entity. @author MyEclipse Persistence Tools
 */

public class Dba04 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jh;
	private String ny;
	private Double dym;

	// Constructors

	/** default constructor */
	public Dba04() {
	}

	/** minimal constructor */
	public Dba04(String jh) {
		this.jh = jh;
	}

	/** full constructor */
	public Dba04(String jh, String ny, Double dym) {
		this.jh = jh;
		this.ny = ny;
		this.dym = dym;
	}

	// Property accessors

	public String getJh() {
		return this.jh;
	}

	public void setJh(String jh) {
		this.jh = jh;
	}

	public String getNy() {
		return this.ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}


	
	public Double getDym() {
		return dym;
	}

	public void setDym(Double dym) {
		this.dym = dym;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Dba04))
			return false;
		Dba04 castOther = (Dba04) other;

		if (jh != null ? !jh.equals(castOther.jh) : castOther.jh != null) return false;
        if (ny != null ? !ny.equals(castOther.ny) : castOther.ny != null) return false;
        return true;
	}

	public int hashCode() {
		int result = jh != null ? jh.hashCode() : 0;
        result = 31 * result + (ny != null ? ny.hashCode() : 0);
		return result;
	}

}