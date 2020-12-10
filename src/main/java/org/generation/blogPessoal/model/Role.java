package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
//Role: Grau de permissão/previçégio, restringe acesso a determinados niveis de usuários
public class Role implements GrantedAuthority {
	
	
	@Id
	private String nomeRole;

//ManyToMany: Muitos para muitos/ muitos usuários podem ter muitas permissões
	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;

//Override: Ele é uma forma de garantir que você está sobrescrevendo um método e não criando um novo
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nomeRole;

	}
	//GETTERS AND SETTERS

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
