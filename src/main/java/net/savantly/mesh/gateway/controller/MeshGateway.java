package net.savantly.mesh.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class MeshGateway {
	
	@RequestMapping("/")
	public String test() {
		return "test";
	}
}
