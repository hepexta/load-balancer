package com.hepexta.loadbalancer.provider;

import com.hepexta.loadbalancer.ex.ProviderInitializeException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class URIProvider implements Provider<URI> {

    private URI uri;

    public URIProvider() {
        try {
            this.uri = new URI(UUID.randomUUID().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new ProviderInitializeException();
        }
    }

    @Override
    public URI get() {
        return uri;
    }
}
