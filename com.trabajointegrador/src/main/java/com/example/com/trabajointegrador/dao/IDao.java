package com.example.com.trabajointegrador.dao;

import java.util.List;

public interface IDao<T> {

    public T registrar(T t);
    public T buscar(int id);
    public  void eliminar(int id);
    public List<T> buscarTodos();
    public T modificar(T t);

}
