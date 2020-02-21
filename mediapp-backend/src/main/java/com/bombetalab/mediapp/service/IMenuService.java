package com.bombetalab.mediapp.service;

import java.util.List;

import com.bombetalab.mediapp.model.Menu;

public interface IMenuService extends ICRUD<Menu, Integer> {
	List<Menu> listarMenuPorUsuario(String nombre);
}
