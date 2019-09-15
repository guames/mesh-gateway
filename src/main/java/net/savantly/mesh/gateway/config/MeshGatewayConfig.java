package net.savantly.mesh.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import net.savantly.gateway.autoconfigure.GatewayEventHandler;
import net.savantly.gateway.autoconfigure.GatewayEventHandlerAdapter;

@Configuration
@ConfigurationProperties("mesh")
public class MeshGatewayConfig {
	
	private static final Logger log = LoggerFactory.getLogger(MeshGatewayConfig.class);
	
	@Bean
	public GatewayEventHandler gatewayEventHandler() {
		return new GatewayEventHandlerAdapter() {
			@Override
			public ResponseEntity afterGet(String child, ResponseEntity<byte[]> response) throws Exception {
				final HttpHeaders headers = new HttpHeaders();

				response.getHeaders().forEach((h, l) -> {
					if (h.toUpperCase() != "TRANSFER-ENCODING") {
						headers.addAll(h, l);
					}
				});

				log.debug("Setting access-control-allow-origin header on response from: {}", child);
				headers.setAccessControlAllowOrigin("*");
				
				return ResponseEntity.status(response.getStatusCode())
						.headers(headers)
						.body(response.getBody());
			}
		};
	}

}
