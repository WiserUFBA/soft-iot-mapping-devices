# FoT-Gateway-Mapping-Devices

This module is responsible for mapping devices connected with each FoT-Gateway. It is used by other modules to get access of basic information about devices and yours sensors/actuators.

It is the basic module of SOFT-IoT, so its installation need be previous to other modules.

It was developed using Apache ServiceMix 6.1.1


Before install the module do you need copy the configuration file in:
```
fot-gateway-mapping-devices/src/main/resources/br.ufba.dcc.wiser.soft_iot.gateway.mapping_devices.cfg
```
to:
```
_servicemix_directory_/etc
```

In this file you need configure what is the devices that is connected in your IoT system and the time (frequency) what you want to collect data

<p align="center">
	Developed by </br>
  <img src="https://wiki.dcc.ufba.br/pub/SmartUFBA/ProjectLogo/wiserufbalogo.jpg"/>
</p>


