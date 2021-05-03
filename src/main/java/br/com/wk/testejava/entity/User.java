package br.com.wk.testejava.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.wk.testejava.tools.StringUtil;


@Entity
@Table(name="tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_user")
	private Integer idUser;

	@Pattern(message = "Email must be a valid email address", regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
	@Email
	@NotBlank
	@Size(max = 255)
	@Column(name="ds_email")
	private String dsEmail;

	@Size(max = 500)
	@Column(name="ds_password")
	private String dsPassword;

	@NotBlank
	@Size(max = 255)
	@Column(name="nm_user")
	private String nmUser;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_profile")
	private Profile profile;
	

	public User() {
	}
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getDsEmail() {
		return this.dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = StringUtil.lowerCase(dsEmail);
	}

	@JsonIgnore
	public String getDsPassword() {
		return this.dsPassword;
	}

	@JsonProperty
	public void setDsPassword(String dsPassword) {
		this.dsPassword = dsPassword;
	}

	public String getNmUser() {
		return nmUser;
	}

	public void setNmUser(String nmUser) {
		this.nmUser = nmUser;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
