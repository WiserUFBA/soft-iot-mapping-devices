package br.ufba.dcc.wiser.soft_iot.mapping_devices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufba.dcc.wiser.soft_iot.entities.*;

public class ControllerImpl implements Controller{
	
	private List<Device> listDevices; 
	private String strJsonDevices;
	private boolean debugModeValue;
	
	public void init(){
		printlnDebug("Starting mapping of connected devices...");
		loadConnectedDevices(this.strJsonDevices);
	}
	
	private void loadConnectedDevices(String strDevices){
		List<Device> listDevices = new ArrayList<Device>();
		try {
			printlnDebug("JSON load:");
			printlnDebug(strDevices);
			JSONArray jsonArrayDevices = new JSONArray(strDevices);
			for (int i = 0; i < jsonArrayDevices.length(); i++){
				JSONObject jsonDevice = jsonArrayDevices.getJSONObject(i);
				ObjectMapper mapper = new ObjectMapper();
				Device device = mapper.readValue(jsonDevice.toString(), Device.class);
				listDevices.add(device);
				
				List<Sensor> listSensors = new ArrayList<Sensor>();
				try{
					JSONArray jsonArraySensors = jsonDevice.getJSONArray("sensors");
					for (int j = 0; j < jsonArraySensors.length(); j++){
						JSONObject jsonSensor = jsonArraySensors.getJSONObject(j);
						Sensor sensor = mapper.readValue(jsonSensor.toString(), Sensor.class);
						listSensors.add(sensor);
					}
					device.setSensors(listSensors);
				} catch (Exception e){	
				}
				
				List<Actuator> listActuators = new ArrayList<Actuator>();
				try {
					JSONArray jsonArrayActuators = jsonDevice.getJSONArray("actuators");
					for (int j = 0; j < jsonArrayActuators.length(); j++){
						JSONObject jsonActuator = jsonArrayActuators.getJSONObject(j);
						Actuator actuator = mapper.readValue(jsonActuator.toString(), Actuator.class);
						listActuators.add(actuator);
					}
					device.setActuators(listActuators);
				} catch (Exception e){	
				}
			}
		} catch (JsonParseException e) {
			System.out.println("Verify the correct format of 'DevicesConnected' property in configuration file."); 
		} catch (JsonMappingException e) {
			System.out.println("Verify the correct format of 'DevicesConnected' property in configuration file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.listDevices = listDevices;
	}
	
	public Device getDeviceById(String deviceId){
		for (Device device : listDevices ){
			if(device.getId().contentEquals(deviceId))
				return device;
		}	
		return null;		
	}
	
	private void printlnDebug(String str){
		if (debugModeValue)
			System.out.println(str);
	}

	public void setStrJsonDevices(String strJsonDevices) {
		this.strJsonDevices = strJsonDevices;
	}

	public List<Device> getListDevices() {
		return listDevices;
	}

	public void setDebugModeValue(boolean debugModeValue) {
		this.debugModeValue = debugModeValue;
	}

}
