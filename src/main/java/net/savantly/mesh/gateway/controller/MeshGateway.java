package net.savantly.mesh.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.savantly.mesh.gateway.config.MeshGatewayConfig;

@RestController
@RequestMapping(MeshGateway.PATH)
public class MeshGateway {
	
	protected static final String PATH = "/gateway";
	private static final Logger log = LoggerFactory.getLogger(MeshGateway.class);

	private MeshGatewayConfig config;

	public MeshGateway(MeshGatewayConfig config) {
		this.config = config;
	}
	
	@GetMapping("/{child}/**")
	public ResponseEntity<?> get(@PathVariable String child, ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(child, proxy)).get();
	}
	
	@PostMapping("/{child}/**")
	public ResponseEntity<?> post(@PathVariable String child, ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(child, proxy)).post();
	}
	
	@PutMapping("/{child}/**")
	public ResponseEntity<?> put(@PathVariable String child, ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(child, proxy)).put();
	}
	
	@RequestMapping(path = "/{child}/**", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> options(@PathVariable String child, ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(child, proxy)).options();
	}
	
	@RequestMapping(path = "/{child}/**", method = RequestMethod.PATCH)
	public ResponseEntity<?> patch(@PathVariable String child, ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(child, proxy)).patch();
	}
	
	@RequestMapping(path = "/{child}/**", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable String child, ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(child, proxy)).delete();
	}
	
	@RequestMapping(path = "/{child}/**", method = RequestMethod.HEAD)
	public ResponseEntity<?> head(@PathVariable String child, ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(child, proxy)).head();
	}
	
	private String getDestinationPath(String child, ProxyExchange<byte[]> proxy) {
		String destination = this.config.getRoutes().get(child);
		String path = proxy.path(String.format("%s/%s", PATH, child));
		log.debug("with prefix removed: {}", path);
		return String.format("%s%s", destination, path);
	}
}
