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

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;

public class CollectorRegistryHelper {
 
    static public <T extends Collector> T registerIfAbsent(
            CollectorRegistry registry,
            T collector) {
        
        try {
            registry.register(collector);
        }
        catch (IllegalArgumentException iae) {
            // Collector already registered that provides name: jet_crashed_windows_total
            if (!iae.getMessage().contains("already registered")) {
                throw iae;
            }
        }
        
        return collector;
    }
    
}