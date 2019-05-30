package com.oil.module;

/**
 * Dba01Id entity. @author MyEclipse Persistence Tools
 */

public class Dba01 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jh;
	private String rq;
	private String db;
	private Double yz;
	private Double pl;
	private Short bj;
	private Double cc;
	private Double cc1;
	private Double scsj;
	private Double rcyl1;
	private Double rcyl;
	private Double hs;
	private Integer rcql;
	private Double bx;
	private Integer qyb;
	private Double bs;
	private String bz;

	// Constructors

	/** default constructor */
	public Dba01() {
	}

	/** full constructor */
	
	
	// Property accessors

	public String getJh() {
		return this.jh;
	}

	public Dba01(String jh, String rq, String db, Double yz, Double pl,
			Short bj, Double cc, Double cc1, Double scsj,Double rcyl1, Double rcyl,Double hs,
			Integer rcql, Double bx, Integer qyb, Double bs, String bz) {
		super();
		this.jh = jh;
		this.rq = rq;
		this.db = db;
		this.yz = yz;
		this.pl = pl;
		this.bj = bj;
		this.cc = cc;
		this.cc1 = cc1;
		this.scsj = scsj;
		this.rcyl1 = rcyl1;
		this.rcyl = rcyl;
		this.hs = hs;
		this.rcql = rcql;
		this.bx = bx;
		this.qyb = qyb;
		this.bs = bs;
		this.bz = bz;
	}

	public void setJh(String jh) {
		this.jh = jh;
	}

	public Short getBj() {
		return this.bj;
	}

	public void setBj(Short bj) {
		this.bj = bj;
	}

	public Double getBx() {
		return this.bx;
	}

	public void setBx(Double bx) {
		this.bx = bx;
	}

	public Double getPl() {
		return this.pl;
	}

	public void setPl(Double pl) {
		this.pl = pl;
	}

	public Double getYz() {
		return this.yz;
	}

	public void setYz(Double yz) {
		this.yz = yz;
	}

	public Double getCc() {
		return this.cc;
	}

	public void setCc(Double cc) {
		this.cc = cc;
	}

	public Double getCc1() {
		return this.cc1;
	}

	public void setCc1(Double cc1) {
		this.cc1 = cc1;
	}


	public Double getRcyl1() {
		return this.rcyl1;
	}

	public void setRcyl1(Double rcyl1) {
		this.rcyl1 = rcyl1;
	}

	public Integer getRcql() {
		return this.rcql;
	}

	public void setRcql(Integer rcql) {
		this.rcql = rcql;
	}


	public Integer getQyb() {
		return this.qyb;
	}

	public void setQyb(Integer qyb) {
		this.qyb = qyb;
	}

	public Double getHs() {
		return this.hs;
	}

	public void setHs(Double hs) {
		this.hs = hs;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDb() {
		return this.db;
	}

	public void setDb(String db) {
		this.db = db;
	}


	public Double getBs() {
		return this.bs;
	}

	public void setBs(Double bs) {
		this.bs = bs;
	}


	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public Double getScsj() {
		return scsj;
	}

	public void setScsj(Double scsj) {
		this.scsj = scsj;
	}

	public Double getRcyl() {
		return rcyl;
	}

	public void setRcyl(Double rcyl) {
		this.rcyl = rcyl;
	}

	public boolean equals(Object o) {
		 if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Dba01 empEntity = (Dba01) o;

	        if (jh != null ? !jh.equals(empEntity.jh) : empEntity.jh != null) return false;
	        if (rq != null ? !rq.equals(empEntity.rq) : empEntity.rq != null) return false;
	        return true;
	}

	public int hashCode() {
		int result = jh != null ? jh.hashCode() : 0;
        result = 31 * result + (rq != null ? rq.hashCode() : 0);
		return result;
	}

}