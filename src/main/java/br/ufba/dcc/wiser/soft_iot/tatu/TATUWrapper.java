package br.ufba.dcc.wiser.soft_iot.tatu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.ufba.dcc.wiser.soft_iot.entities.Device;
import br.ufba.dcc.wiser.soft_iot.entities.Sensor;
import br.ufba.dcc.wiser.soft_iot.entities.SensorData;

//{"method":"flow", "device":"sc01", "sensor":"TemperatureSensor", time:{"collect":10000,"publish":20000}}

public final class TATUWrapper {
	
	public static String topicBase = "/dev/";
	
	//{\"method\":\"post\", \"actuator\":\"irrigation_actuator\", \"value\":\"30\"}"
	public static String getTATUPost(String actuatorId, String value){
		String msgStr = "{\"method\":\"post\", \"actuator\":\"" + actuatorId + "\", \"value\":\"" + value + "\"}";
		
		return msgStr;
	}
	
	public static String getTATUFlowInfo(String sensorId, int collectSeconds, int publishSeconds){
		//String msgStr = "FLOW " + "INFO " + sensorId + " {\"collect\":" + collectSeconds + ",\"publish\":" + publishSeconds + "}";
		String msgStr = "{\"method\":\"flow\"," +   "\"sensor\":\"" + sensorId + "\", \"time\":{\"collect\":" + collectSeconds + ",\"publish\":" + publishSeconds + "}}";
		
		return msgStr;
	}
	
	public static String getTATUFlowValue(String sensorId, int collectSeconds, int publishSeconds){
		String msgStr = "{\"method\":\"flow\"," +   "\"sensor\":\"" + sensorId + "\", \"time\":{\"collect\":" + collectSeconds + ",\"publish\":" + publishSeconds + "}}";
		//String msgStr = "FLOW " + "VALUE " + sensorId + " {\"collect\":" + collectSeconds + ",\"publish\":" + publishSeconds + "}";
		
		return msgStr;
	}
	
	//{"code":"post","method":"flow","header":{"sensor":"luminositySensor","device":"sc01","time":{"collect":5000,"publish":10000}},"data":["0","0"]}
	//{"CODE":"POST","METHOD":"FLOW","HEADER":{"NAME":"ufbaino04"},"BODY":{"temperatureSensor":["36","26"],"FLOW":{"publish":10000,"collect":5000}}}
	public static boolean isValidTATUAnswer(String answer){
		try{
			JSONObject json = new JSONObject(answer);
			if ((json.get("code").toString().contentEquals("post"))
					&& !json.isNull("data")) {
				return true;
			}
		} catch (org.json.JSONException e) {
		}
		return false;
	}
	
	public static boolean isValidTATUActuatorRequest(String answer){
		try{
			JSONObject json = new JSONObject(answer);
			if ((json.get("method").toString().contentEquals("post"))
					&& !json.isNull("value")) {
				return true;
			}
		} catch (org.json.JSONException e) {
		}
		return false;
	}
	
	public static String getDeviceIdByTATUAnswer(String answer){
		JSONObject json = new JSONObject(answer);
		String deviceId = json.getJSONObject("header").getString("device");
		
		return deviceId;
	}
	
	public static String getSensorIdByTATUAnswer(String answer){
		JSONObject json = new JSONObject(answer);
		String sensorId = json.getJSONObject("header").getString("sensor");
		return sensorId;
	}
	
	public static String getActuatorIdByTATURequest(String answer){
		JSONObject json = new JSONObject(answer);
		String actuatorId = json.getString("actuator");
		return actuatorId;
	}
	
	public static String getValueByTATURequest(String answer){
		JSONObject json = new JSONObject(answer);
		String value = json.getString("value");
		return value;
	}
	
	public static String getDeviceIdByTopic(String topic){
		return topic.split("topicBase")[0];
	}
	
	//{"code":"post","method":"flow","header":{"sensor":"luminositySensor","device":"sc01","time":{"collect":5000,"publish":10000}},"data":["0","0"]}
	public static List<SensorData> parseTATUAnswerToListSensorData(String answer,Device device, Sensor sensor, Date baseDate){
		List<SensorData> listSensorData = new ArrayList<SensorData>();
		try{
			JSONObject json = new JSONObject(answer);
			JSONArray sensorValues = json.getJSONArray("data");
			int collectTime = json.getJSONObject("header").getJSONObject("time").getInt("collect");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(baseDate);
			for (int i = 0; i < sensorValues.length(); i++) {
				if(!sensorValues.isNull(i)){
					String value = sensorValues.getString(i);
					SensorData sensorData = new SensorData(device, sensor,value,calendar.getTime(),calendar.getTime());
					listSensorData.add(sensorData);
					calendar.add(Calendar.MILLISECOND, collectTime);
				}
			}
		}catch(org.json.JSONException e){
		}
		return listSensorData;
	}

}