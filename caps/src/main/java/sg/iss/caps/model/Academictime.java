package sg.iss.caps.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the academictime database table.
 * 
 */
@Entity
@NamedQuery(name="Academictime.findAll", query="SELECT a FROM Academictime a")
@Data
@AllArgsConstructor
public class Academictime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int year;

	@Temporal(TemporalType.TIMESTAMP)
	private Date term1ExmResRlsDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date term1RegEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date term1RegStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date term2ExmResRlsDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date term2RegEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date term2RegStartDate;

	public Academictime() {
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getTerm1ExmResRlsDate() {
		return this.term1ExmResRlsDate;
	}

	public void setTerm1ExmResRlsDate(Date term1ExmResRlsDate) {
		this.term1ExmResRlsDate = term1ExmResRlsDate;
	}

	public Date getTerm1RegEndDate() {
		return this.term1RegEndDate;
	}

	public void setTerm1RegEndDate(Date term1RegEndDate) {
		this.term1RegEndDate = term1RegEndDate;
	}

	public Date getTerm1RegStartDate() {
		return this.term1RegStartDate;
	}

	public void setTerm1RegStartDate(Date term1RegStartDate) {
		this.term1RegStartDate = term1RegStartDate;
	}

	public Date getTerm2ExmResRlsDate() {
		return this.term2ExmResRlsDate;
	}

	public void setTerm2ExmResRlsDate(Date term2ExmResRlsDate) {
		this.term2ExmResRlsDate = term2ExmResRlsDate;
	}

	public Date getTerm2RegEndDate() {
		return this.term2RegEndDate;
	}

	public void setTerm2RegEndDate(Date term2RegEndDate) {
		this.term2RegEndDate = term2RegEndDate;
	}

	public Date getTerm2RegStartDate() {
		return this.term2RegStartDate;
	}

	public void setTerm2RegStartDate(Date term2RegStartDate) {
		this.term2RegStartDate = term2RegStartDate;
	}

}