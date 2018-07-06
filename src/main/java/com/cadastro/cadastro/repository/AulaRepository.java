package com.cadastro.cadastro.repository;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.cadastro.models.Aula;

public interface AulaRepository extends CrudRepository <Aula, String> {
	Aula findBycodigo(long codigo);

}
