package br.com.titans.appchallengescania.scania.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.titans.appchallengescania.scania.dto.OperadorDto;
import br.com.titans.appchallengescania.scania.model.Operador;
import br.com.titans.appchallengescania.scania.repositories.OperadorRepository;

@Controller
public class OperadorController {

	@Autowired
	private OperadorRepository operadorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/operador")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("operador/index");

		List<Operador> listarOperador = operadorRepository.findAll();
		model.addObject("listarOperador", listarOperador);

		return model;
	}

	@GetMapping("operador/criar")
	public ModelAndView criar(OperadorDto model) {
		return new ModelAndView("operador/criar");
	}

	@PostMapping("/operador")
	public ModelAndView salvar(@Valid OperadorDto model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelView = new ModelAndView("operador/criar");
			return modelView;
		}

		Operador operador = modelMapper.map(model, Operador.class);
		operadorRepository.save(operador);
		return new ModelAndView("redirect:/operador");
	}

	@GetMapping("operador/{id}")
	public ModelAndView mostrar(@PathVariable Long id) {
		Optional<Operador> operador = operadorRepository.findById(id);

		if (operador.isPresent()) {
			Operador operadorGet = operador.get();
			ModelAndView modelView = new ModelAndView("operador/infos");
			modelView.addObject("operador", operadorGet);
			return modelView;
		}
		System.out.println("Não foi possível encontrar esta tarefa!");
		return new ModelAndView("redirect:/operador");
	}
	
	@GetMapping("/operador/{id}/editar")
	public ModelAndView editar(@PathVariable Long id, OperadorDto request) {
		Optional<Operador> optionalOperador = operadorRepository.findById(id);
		
		if (optionalOperador.isPresent()) {
			Operador operador = optionalOperador.get();
			request.fromOperador(operador);
			ModelAndView model = new ModelAndView("operador/editar");
			model.addObject("operadorId", operador.getId());
			return model;
		}
		
		return new ModelAndView("redirect:/operador");
	}
	
	@PostMapping("/operador/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid OperadorDto request, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("operador/editar");
			model.addObject("operadorId", id);
			return model;
		}
		
		Optional<Operador> optionalOperador = operadorRepository.findById(id);
		
		if (optionalOperador.isPresent()) {
			Operador operador = modelMapper.map(request, Operador.class);
			operador.setId(id);
			operadorRepository.save(operador);
			return new ModelAndView("redirect:/operador/".concat(operador.getId().toString()));
		}
		return new ModelAndView("redirect:/operador");
	}
	
	@GetMapping("/operador/{id}/delete")
	public ModelAndView delete (@PathVariable Long id) {
		ModelAndView model = new ModelAndView("redirect:/operador");
		
		this.operadorRepository.deleteById(id);
		model.addObject("mensagem", "Operador Removido.");
		
		return model;
	}

}
