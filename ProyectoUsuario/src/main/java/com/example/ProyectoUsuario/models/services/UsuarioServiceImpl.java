package com.example.ProyectoUsuario.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProyectoUsuario.models.dao.IUsuariosDao;
import com.example.ProyectoUsuario.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuariosDao IUsuarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) IUsuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return IUsuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		System.out.print(usuario);
		return IUsuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		IUsuarioDao.deleteById(id);
	}

}
