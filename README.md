Ninja Framework + Prometheus by Fizzed
============================================

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.fizzed/prometheus-plus/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.fizzed/prometheus-plus)

[Fizzed, Inc.](http://fizzed.com) (Follow on Twitter: [@fizzed_inc](http://twitter.com/fizzed_inc))

## Overview

Integration of [Prometheus](https://prometheus.io) with the
[Ninja Framework](https://github.com/ninjaframework/ninja).

## Setup

Add the prometheus-ninja-module dependency to your Maven pom.xml

```xml
<dependency>
    <groupId>com.fizzed</groupId>
    <artifactId>prometheus-ninja-module</artifactId>
    <version>0.0.5</version>
</dependency>
```

In your `conf/Module.java` file:

```java
package conf;

import com.fizzed.prometheus.ninja.NinjaPrometheusModule;
import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        install(new NinjaPrometheusModule());
    }

}
```

In your `conf/Routes.java` file:

```java
package conf;

import com.fizzed.prometheus.ninja.NinjaPrometheusRoutes;
import ninja.Results;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        
        NinjaPrometheusRoutes.init(router);

    }
    
}
```

## Demo 

There is a Ninja app in the `demo` folder that demonstrates all the functionality
this module provides and it's a simple way to see how it works.  This project 
uses [Blaze](https://github.com/fizzed/blaze) to help script tasks. Run the
following in your shell (from the root project directory, not in `demo`):

    java -jar blaze.jar demo

Once running, point your browser to http://localhost:8080/

## License

Copyright (C) 2020 Fizzed, Inc.

This work is licensed under the Apache License, Version 2.0. See LICENSE for details.