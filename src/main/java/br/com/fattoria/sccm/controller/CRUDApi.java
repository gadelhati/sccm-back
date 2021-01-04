package br.com.fattoria.sccm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

public interface CRUDApi<T, ID> {

	List<T> getAll();

	Optional<T> getById(ID id);

	T save(@Valid T t);

	void delete(ID id);

}
