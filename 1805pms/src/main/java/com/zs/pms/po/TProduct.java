package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

public class TProduct implements Serializable {

	/**
	 * 商品表
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String pname;
	private Integer brand; // 关联
	private double weight;
	private Integer isnew;
	private Integer ishot;
	private Integer isrecommend;
	private Integer ptype;
	private Integer creator;
	private Date creatime;
	private Integer chktor;
	private Date chktime;
	private Integer updator;
	private Date updatime;
	private Integer status;
	private String fromarea;
	private String discribe;
	private String packlist;
	private String features;
	private String colors;
	private String sizes;
	private String picurl;
	

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getBrand() {
		return brand;
	}

	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Integer getIsnew() {
		return isnew;
	}

	public void setIsnew(Integer isnew) {
		this.isnew = isnew;
	}

	public Integer getIshot() {
		return ishot;
	}

	public void setIshot(Integer ishot) {
		this.ishot = ishot;
	}

	public Integer getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(Integer isrecommend) {
		this.isrecommend = isrecommend;
	}

	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreatime() {
		return creatime;
	}

	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}

	public Integer getChktor() {
		return chktor;
	}

	public void setChktor(Integer chktor) {
		this.chktor = chktor;
	}

	public Date getChktime() {
		return chktime;
	}

	public void setChktime(Date chktime) {
		this.chktime = chktime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	public Date getUpdatime() {
		return updatime;
	}

	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFromarea() {
		return fromarea;
	}

	public void setFromarea(String fromarea) {
		this.fromarea = fromarea;
	}

	public String getDiscribe() {
		return discribe;
	}

	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}

	public String getPacklist() {
		return packlist;
	}

	public void setPacklist(String packlist) {
		this.packlist = packlist;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

}
