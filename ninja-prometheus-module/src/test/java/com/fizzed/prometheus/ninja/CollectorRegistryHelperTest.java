/*
 * Copyright 2020 Fizzed, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fizzed.prometheus.ninja;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Summary;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author jjlauer
 */
public class CollectorRegistryHelperTest {
 
    @Test
    public void registerIfAbsent() {
        Summary collector = Summary.build()
            .name("test")
            .help("help")
            .create();
        
        boolean registered;
        
        registered = CollectorRegistryHelper.registerIfAbsent(CollectorRegistry.defaultRegistry, collector);
        
        assertThat(registered, is(true));
        
        // 2nd time should fail to register, but no exception...
        registered = CollectorRegistryHelper.registerIfAbsent(CollectorRegistry.defaultRegistry, collector);
        
        assertThat(registered, is(false));
        
    }
    
}