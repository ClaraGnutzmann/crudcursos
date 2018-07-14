package com.cadastro.cadastro.repository;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.cadastro.models.Aula;
import com.cadastro.cadastro.models.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByAula(Aula aula);
	Convidado findByRg (String rg);

}
