package com.fatec.scc.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Reserva;
import com.fatec.scc.model.ReservaRepository;

@Service
public class ReservaServico {
	Logger logger = LogManager.getLogger(ReservaServico.class);
	@Autowired
	private ReservaRepository repository;

	public ModelAndView saveOrUpdate(Reserva reserva) {
		logger.info(">>>>>>>> 2. ReservaServico metodo save executado");
		ModelAndView modelAndView = new ModelAndView("registrarReserva");
		repository.save(reserva);
		return modelAndView;
	}
}