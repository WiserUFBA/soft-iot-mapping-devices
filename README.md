# FoT-Gateway-Mapping-Devices

## Introduction

This module is responsible for mapping devices connected with each FoT-Gateway. It is used by other modules to get access of basic information about devices and yours sensors/actuators.

It is the basic module of SOFT-IoT, so its installation need be previous to other modules.

It was developed using Apache ServiceMix 6.1.1

## Installation

To install this bundle using our custom maven support execute the following commands in Karaf Shell:

```sh
config:edit org.ops4j.pax.url.mvn 
config:property-append org.ops4j.pax.url.mvn.repositories ", https://github.com/WiserUFBA/wiser-mvn-repo/raw/master/releases@id=wiser"
config:update
bundle:install mvn:br.ufba.dcc.wiser.soft_iot/soft-iot-mapping-devices/1.0.0
```
Before install the module do you need copy the configuration file from src folder here in GitHub:
```
soft-iot-mapping-devices/src/main/resources/br.ufba.dcc.wiser.soft_iot.gateway.mapping_devices.cfg
```
to your local etc folder of ServiceMix:
```
_servicemix_directory_/etc
```

In this file you need configure what is the devices that is connected in your IoT system and the time (frequency) you want to collect data.

Syntax of different type of devices (it is important use this to match with enrichment of semantic description):
```
Accelerometer
AirPollutantSensor
AirThermometer
AlcoholLevelSensor
AtmosphericPressureSensor
BloodPressureSensor
BoardThermometer
BodyThermometer
CholesterolSensor
CloudCoverSensor
ConductivitySensor
DeltaDewPointSensor
DewPointSensor
DistanceSensor
ECG
ElectricalSensor
EnergyMeter
FallDetector
FrequencySensor
FuelLevel
GPSSensor
GasDetector
Glucometer
GyrometerSensor
GyroscopeSensor
HeartBeatSensor
HumiditySensor
Hydrophone
ImageSensor
LeafWetnessSensor
LightSensor
Magnetometer
OccupancyDetector
Odometer
PHSensor
Pedometer
PrecipitationSensor
PressureSensor
ProximitySensor
PulseOxymeter
RoadSurfaceThermometer
SaltMeter
Seismometer
ShakeSensor
SkinConductanceSensor
SmokeDetector
SoilHumiditySensor
SolarRadiationSensor
SoundSensor
SpeedSensor
SunPositionDirectionSensor
SunPositionElevationSensor
Thermometer
ThrottleSensor
TouchSensor
UltrasonicSensor
VehicleCountSensor
VisibilitySensor
VoltageSensor
WeightSensor
WindChillSensor
WindDirectionSensor
WindSpeedSensor
```
## Deploy to Maven Repo

To deploy this repo into our custom maven repo, change pom according to the new version and after that execute the following command. Please ensure that both wiser-mvn-repo and this repo are on the same folder.

```sh
mvn -DaltDeploymentRepository=release-repo::default::file:../wiser-mvn-repo/releases/ deploy
```

## Support and development

<p align="center">
	Developed by Leandro Andrade at </br>
  <img src="https://wiki.dcc.ufba.br/pub/SmartUFBA/ProjectLogo/wiserufbalogo.jpg"/>
</p>
