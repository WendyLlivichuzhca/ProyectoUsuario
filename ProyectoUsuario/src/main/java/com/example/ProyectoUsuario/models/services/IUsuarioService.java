package com.example.ProyectoUsuario.models.services;

import java.util.List;

import com.example.ProyectoUsuario.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public Usuario save(Usuario usuario);

	public Usuario findById(Long id);

	public void delete(Long id);

}
