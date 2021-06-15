package com.fatec.scc.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Reserva;
import com.fatec.scc.servico.ClienteServico;
import com.fatec.scc.servico.ReservaServico;

@Controller
@RequestMapping(path = "/sig")
public class ReservaController {
	Logger logger = LogManager.getLogger(ReservaController.class);
	@Autowired
	ReservaServico reservaServico;

	@Autowired
	ClienteServico servico;

	@GetMapping("/reserva")
	public ModelAndView retornaFormDeRegistroDeReserva(Reserva reserva) {
		logger.info("1. Controller = abrir reserva selecionado no menu");
		ModelAndView mv = new ModelAndView("abrirReserva");
		mv.addObject("clientes", servico.findAll());
		return mv;
	}

	@GetMapping("/reserva/{cpf}")
	public ModelAndView registrarReserva(Reserva reserva) {
		logger.info("2. Controller cadastrar reserva");
		ModelAndView mv = new ModelAndView("registrarReserva");
		mv.addObject("reserva", reserva);
		return mv;
	}

	@PostMapping("/reservas")
	public ModelAndView save(@Valid Reserva reserva, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("registrarReserva");
		if (result.hasErrors()) {
			logger.info(">>>>>> 3. controller cadastrar reserva com rerro ");
			modelAndView.addObject("message", "Dados invalidos!");
			modelAndView.setViewName("cadastrarCliente");
		} else {
			logger.info(">>>>>> 1. controller cadastrar reserva chamado pela view");
			modelAndView = reservaServico.saveOrUpdate(reserva);
			modelAndView.addObject("message", "Reserva cadastrada!");
		}
		return modelAndView;
	}
}