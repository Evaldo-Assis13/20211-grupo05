package com.fatec.scc.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Reserva;
import com.fatec.scc.model.ReservaRepository;

@Service
public class ReservaServicoI implements ReservaServico {
	Logger logger = LogManager.getLogger(ReservaServicoI.class);
	@Autowired
	private ReservaRepository repository;

	public Iterable<Reserva> findAll() {
		return repository.findAll();
	}

	public Reserva findByIdReserva(Long idReserva) {
		return repository.findByIdReserva(idReserva);
	}

	public void deleteById(Long idReserva) {
		repository.deleteById(idReserva);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + idReserva);
	}

	public Reserva findById(Long idReserva) {
		return repository.findById(idReserva).get();
	}

	public ModelAndView saveOrUpdate(Reserva reserva) {
		ModelAndView modelAndView = new ModelAndView("consultarReserva");
		try {
				repository.save(reserva);
				logger.info(">>>>>> 4. comando save executado ");
				modelAndView.addObject("reservas", repository.findAll());
		} catch (Exception e) { // captura validacoes na camada de persistencia
			modelAndView.setViewName("cadastrarReserva");
			modelAndView.addObject("message", "Dados invalidos - " + e.getMessage());
			logger.error(">>>>>> 4. erro nao esperado ==> " + e.getMessage());
		}
		return modelAndView;
	}
	
}