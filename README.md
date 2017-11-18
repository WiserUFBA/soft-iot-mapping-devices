# FoT-Gateway-Mapping-Devices

## Introduction

This module is responsible for mapping devices connected with each FoT-Gateway. It is used by other modules to get access of basic information about devices and yours sensors/actuators.

It is the basic module of SOFT-IoT, so its installation need be previous to other modules.

It was developed using Apache ServiceMix 6.1.1

## Development

Before install the module do you need copy the configuration file in:
```
fot-gateway-mapping-devices/src/main/resources/br.ufba.dcc.wiser.soft_iot.gateway.mapping_devices.cfg
```
to:
```
_servicemix_directory_/etc
```

In this file you need configure what is the devices that is connected in your IoT system and the time (frequency) what you want to collect data

## Deploy to Maven Repo

To deploy this repo into our custom maven repo, change pom according to the new version and after that execute the following command. Please ensure that both wiser-mvn-repo and this repo are on the same folder.

```sh
mvn -DaltDeploymentRepository=release-repo::default::file:../wiser-mvn-repo/releases/ deploy
```

## Installation

To install this bundle using our custom maven support execute the following commands in Karaf Shell:

```sh
config:edit org.ops4j.pax.url.mvn 
config:property-append org.ops4j.pax.url.mvn.repositories ", https://github.com/WiserUFBA/wiser-mvn-repo/raw/master/releases@id=wiser"
config:update
mvn:br.ufba.dcc.wiser.soft_iot/fot-gateway-mapping-devices/1.0.0
```

## Support and development

<p align="center">
	Developed by Leandro Andrade at </br>
  <img src="https://wiki.dcc.ufba.br/pub/SmartUFBA/ProjectLogo/wiserufbalogo.jpg"/>
</p>
