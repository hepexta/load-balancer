package com.hepexta.loadbalancer.scheduler;

import com.hepexta.loadbalancer.ex.ProvidersEmptyException;
import com.hepexta.loadbalancer.provider.URIProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class RandomProviderSchedulerTest {

    ProviderScheduler<String> ps = new RandomProviderScheduler<>();

    @Test
    public void getNextProvider() {
        Assert.assertNotNull(ps.getNextProvider(Arrays.asList(new URIProvider())));
        Assert.assertNotNull(ps.getNextProvider(Arrays.asList(new URIProvider(), new URIProvider(), new URIProvider())));
    }

    @Test(expected = ProvidersEmptyException.class)
    public void getNextProvider_emptyList() {
        Assert.assertNotNull(ps.getNextProvider(Collections.emptyList()));
    }

}