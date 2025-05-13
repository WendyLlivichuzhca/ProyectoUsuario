package com.example.ProyectoUsuario.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.ProyectoUsuario.models.entity.Usuario;

public interface IUsuariosDao extends CrudRepository<Usuario, Long>{

}
