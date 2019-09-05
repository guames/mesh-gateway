package net.savantly.mesh.gateway.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.savantly.mesh.gateway.config.MeshGatewayConfig;

@RestController
@RequestMapping(MeshGateway.PATH)
public class MeshGateway {
	
	protected static final String PATH = "/mesh";
	private static final Logger log = LoggerFactory.getLogger(MeshGateway.class);

	private MeshGatewayConfig config;

	public MeshGateway(MeshGatewayConfig config) {
		this.config = config;
	}
	
	@GetMapping("/**")
	public ResponseEntity<?> get(ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(proxy)).get();
	}
	
	@PostMapping("/**")
	public ResponseEntity<?> post(ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(proxy)).post();
	}
	
	@PutMapping("/**")
	public ResponseEntity<?> put(ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(proxy)).put();
	}
	
	@RequestMapping(path = "/**", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> options(ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(proxy)).options();
	}
	
	@RequestMapping(path = "/**", method = RequestMethod.PATCH)
	public ResponseEntity<?> patch(ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(proxy)).patch();
	}
	
	@RequestMapping(path = "/**", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(proxy)).delete();
	}
	
	@RequestMapping(path = "/**", method = RequestMethod.HEAD)
	public ResponseEntity<?> head(ProxyExchange<byte[]> proxy) throws Exception {
		log.debug("doing GET: {}", proxy.path());
		return proxy.uri(getDestinationPath(proxy)).head();
	}
	
	private String getDestinationPath(ProxyExchange<byte[]> proxy) {
		String path = proxy.path(PATH);
		log.debug("with prefix removed: {}", path);
		return String.format("%s%s", config.getDestination(), path);
	}
}
