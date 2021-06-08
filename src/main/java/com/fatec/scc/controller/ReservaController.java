package com.fatec.scc.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.servico.ReservaServico;
import com.fatec.scc.model.Reserva;

@Controller
@RequestMapping(path = "/sig")
public class ReservaController {
	Logger logger = LogManager.getLogger(ReservaController.class);
	@Autowired
	ReservaServico servico;

	@GetMapping("/reservas")
	public ModelAndView retornaFormDeConsultaTodosReservas() {
		ModelAndView modelAndView = new ModelAndView("consultarReserva");
		modelAndView.addObject("reservas", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/reserva")
	public ModelAndView retornaFormDeCadastroDe(Reserva reserva) {
		ModelAndView mv = new ModelAndView("cadastrarReserva");
		mv.addObject("reserva", reserva);
		return mv;
	}

	@GetMapping("/reservas/{idReserva}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarReserva(@PathVariable("idReserva") Long idReserva) {
		ModelAndView modelAndView = new ModelAndView("atualizarReserva");
		modelAndView.addObject("reserva", servico.findByIdReserva(idReserva)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/reserva/{idReserva}")
	public ModelAndView excluirNoFormDeConsultaReserva(@PathVariable("idReserva") Long idReserva) {
		servico.deleteById(idReserva);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + idReserva);
		ModelAndView modelAndView = new ModelAndView("consultarReserva");
		modelAndView.addObject("reservas", servico.findAll());
		return modelAndView;
	}

	@PostMapping("/reservas")
	public ModelAndView save(@Valid Reserva reserva, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarReserva");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarReserva");
		} else {
			modelAndView = servico.saveOrUpdate(reserva);
		}
		return modelAndView;
	}

	@PostMapping("/reservas/{idReserva}")
	public ModelAndView atualizaReserva(@PathVariable("idReserva") Long idReserva, @Valid Reserva reserva,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarReserva");
		if (result.hasErrors()) {
			reserva.setIdReserva(idReserva);
			return new ModelAndView("atualizarReserva");
		}
		// programacao defensiva - deve-se verificar se o Cliente existe antes de
		// atualizar
		Reserva umReserva = servico.findByIdReserva(idReserva);
		umReserva.setCpf(reserva.getCpf());
		umReserva.setNumQuarto(reserva.getNumQuarto());
		umReserva.setDataEntrada(reserva.getDataEntrada());
		umReserva.setDataSaida(reserva.getDataSaida());
		modelAndView = servico.saveOrUpdate(umReserva);
		return modelAndView;
	}

}