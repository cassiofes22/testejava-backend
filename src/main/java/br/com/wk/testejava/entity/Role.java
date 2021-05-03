package br.com.wk.testejava.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_role")
	private Integer idRole;

	@Column(name="nm_role")
	private String nmRole;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="tb_profile_role"
		, joinColumns={
			@JoinColumn(name="id_role")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_profile")
			}
		)
	private List<Profile> profiles;

	public Role() {
	}

	public Integer getIdRole() {
		return this.idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getNmRole() {
		return this.nmRole;
	}

	public void setNmRole(String nmRole) {
		this.nmRole = nmRole;
	}

	public List<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
}
