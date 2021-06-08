package com.fatec.scc.servico;

import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Reserva;

public interface ReservaServico {
	public Iterable<Reserva> findAll();

	public Reserva findByIdReserva(Long idReserva);

	public void deleteById(Long idReserva);

	public Reserva findById(Long idReserva);

	public ModelAndView saveOrUpdate(Reserva reserva);


}
