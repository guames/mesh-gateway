package net.savantly.mesh.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("gateway.mesh")
public class MeshGatewayConfig {

	private String destination;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String host) {
		this.destination = host;
	}
}
