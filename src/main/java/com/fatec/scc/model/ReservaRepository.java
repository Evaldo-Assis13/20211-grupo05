package com.fatec.scc.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
	public Reserva findByIdReserva(@Param("idReserva") Long idReserva);
}