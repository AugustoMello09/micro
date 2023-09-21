package io.github.AugustoMello09.mscartoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cartoes")
public class CartoesController {
	
	@GetMapping
	public String status() {
		return "ok";
	}

}
