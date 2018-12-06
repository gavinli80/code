package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 品牌
 * 
 * @author Administrator
 *
 */
public class TBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String bname;
	private String discribe;
	private String logopic;
	private String weburl;
	private int creator;
	private Date creatime;
	private int updator;
	private Date updatime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getDiscribe() {
		return discribe;
	}

	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}

	public String getLogopic() {
		return logopic;
	}

	public void setLogopic(String logopic) {
		this.logopic = logopic;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Date getCreatime() {
		return creatime;
	}

	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}

	public int getUpdator() {
		return updator;
	}

	public void setUpdator(int updator) {
		this.updator = updator;
	}

	public Date getUpdatime() {
		return updatime;
	}

	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}

}
