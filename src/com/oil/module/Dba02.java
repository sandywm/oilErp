package com.oil.module;

/**
 * Dba02Id entity. @author MyEclipse Persistence Tools
 */

public class Dba02 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jh;//井号
	private String rq;//日期
	private Double scsj;//时间
	private String zsfs;//方
	private Double gxyl;//泵压
	private Double yy;//油压
	private Double ty;//套压
	private Integer rpzsl;//配注
	private Integer rzsl;//日注
	private String bz;//备注
	private String db;//队别

	// Constructors

	/** default constructor */
	public Dba02() {
	}

	/** minimal constructor */
	public Dba02(String jh, String rq) {
		this.jh = jh;
		this.rq = rq;
	}

	/** full constructor */
	public Dba02(String jh, String rq, Double scsj, String zsfs,
			Double gxyl, Double yy, Double ty, Integer rpzsl, Integer rzsl,String bz,String db) {
		this.jh = jh;
		this.rq = rq;
		this.scsj = scsj;
		this.zsfs = zsfs;
		this.gxyl = gxyl;
		this.yy = yy;
		this.ty = ty;
		this.rpzsl = rpzsl;
		this.rzsl = rzsl;
		this.bz = bz;
		this.db = db;
	}

	// Property accessors

	public String getJh() {
		return this.jh;
	}

	public void setJh(String jh) {
		this.jh = jh;
	}

	public String getRq() {
		return this.rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public Double getScsj() {
		return this.scsj;
	}

	public void setScsj(Double scsj) {
		this.scsj = scsj;
	}

	public String getZsfs() {
		return this.zsfs;
	}

	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
	}

	public Double getGxyl() {
		return this.gxyl;
	}

	public void setGxyl(Double gxyl) {
		this.gxyl = gxyl;
	}

	public Double getYy() {
		return this.yy;
	}

	public void setYy(Double yy) {
		this.yy = yy;
	}

	public Double getTy() {
		return this.ty;
	}

	public void setTy(Double ty) {
		this.ty = ty;
	}

	

	public Integer getRpzsl() {
		return rpzsl;
	}

	public void setRpzsl(Integer rpzsl) {
		this.rpzsl = rpzsl;
	}

	public Integer getRzsl() {
		return rzsl;
	}

	public void setRzsl(Integer rzsl) {
		this.rzsl = rzsl;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dba02 empEntity = (Dba02) o;

        if (jh != null ? !jh.equals(empEntity.jh) : empEntity.jh != null) return false;
        if (rq != null ? !rq.equals(empEntity.rq) : empEntity.rq != null) return false;
//        if (scsj != null ? !scsj.equals(empEntity.scsj) : empEntity.scsj != null) return false;
//        if (zsfs != null ? !zsfs.equals(empEntity.zsfs) : empEntity.zsfs != null) return false;
//        if (gxyl != null ? !gxyl.equals(empEntity.gxyl) : empEntity.gxyl != null) return false;
//        if (yy != null ? !yy.equals(empEntity.yy) : empEntity.yy != null) return false;
//        if (ty != null ? !ty.equals(empEntity.ty) : empEntity.ty != null) return false;
//
//        if (rpzsl != null ? !rpzsl.equals(empEntity.rpzsl) : empEntity.rpzsl != null) return false;
//        if (rzsl != null ? !rzsl.equals(empEntity.rzsl) : empEntity.rzsl != null) return false;
//        if (bz != null ? !bz.equals(empEntity.bz) : empEntity.bz != null) return false;
        
        return true;
    }

	public int hashCode() {
        int result = jh != null ? jh.hashCode() : 0;
        result = 31 * result + (rq != null ? rq.hashCode() : 0);
//        result = 31 * result + (scsj != null ? scsj.hashCode() : 0);
//        result = 31 * result + (zsfs != null ? zsfs.hashCode() : 0);
//        result = 31 * result + (gxyl != null ? gxyl.hashCode() : 0);
//        result = 31 * result + (yy != null ? yy.hashCode() : 0);
//        result = 31 * result + (ty != null ? ty.hashCode() : 0);
//        result = 31 * result + (rpzsl != null ? rpzsl.hashCode() : 0);
//        result = 31 * result + (rpzsl != null ? rpzsl.hashCode() : 0);
//        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        return result;
    }

}