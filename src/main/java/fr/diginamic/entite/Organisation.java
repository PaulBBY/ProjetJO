package fr.diginamic.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "organisation", uniqueConstraints = @UniqueConstraint(columnNames = { "CIO" }))
public class Organisation {
	
	/**
	 * Cette classe est une entité JPA, regroupant les codes CIO et ISO,
	 * ainsi que les noms en Anglais et Français des Organisations,
	 * ainsi que leurs associations avec les différentes participations (voir Participe)
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "CIO")
	private String cio;

	@Column(name = "PAYS_FR")
	private String paysFr;

	@Column(name = "PAYS_ENG")
	private String paysEng;

	@Column(name = "ISO")
	private String iso;

	@Column(name = "OBSOLETE")
	private Boolean obsolete;

	@OneToMany(mappedBy = "organisation")
	private List<Participe> participe;

	public Organisation() {
	}

	public Organisation(String cio) {
		this.cio = cio;

	}

	@Override
	public String toString() {
		return "Organisation [id=" + id + ", cio=" + cio + ", paysFr=" + paysFr + ", paysEng=" + paysEng + ", iso="
				+ iso + ", obsolete=" + obsolete + ", participe=" + participe + "]";
	}

	public String getCio() {
		return cio;
	}

	public void setCio(String cio) {
		this.cio = cio;
	}

	public String getPaysFr() {
		return paysFr;
	}

	public void setPaysFr(String paysFr) {
		this.paysFr = paysFr;
	}

	public String getPaysEng() {
		return paysEng;
	}

	public void setPaysEng(String paysEng) {
		this.paysEng = paysEng;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public Boolean getObsolete() {
		return obsolete;
	}

	public void setObsolete(Boolean obsolete) {
		this.obsolete = obsolete;
	}

	public List<Participe> getParticipe() {
		return participe;
	}

	public void setParticipe(List<Participe> participe) {
		this.participe = participe;
	}

	public int getId() {
		return id;
	}

}
