package com.ahmedislam.sensorinfo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;

import java.util.List;

@DesignerComponent(
        version = 4,
        versionName = "1.0Beta",
        description = "SensorInfo - A component to gather information about device sensors like Proximity, Gyroscope, Accelerometer, and Light. (Device Name, Availability, and Model)",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "icon.png"
)
@SimpleObject(external = true)
public class SensorInfo extends AndroidNonvisibleComponent {

    private final Context context;
    private final SensorManager sensorManager;

    public SensorInfo(ComponentContainer container) {
        super(container.$form());
        this.context = container.$context();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    // ================= EVENTS =================

    @SimpleEvent(description = "Triggered when Proximity sensor info is gathered")
    public void ProximitySensorInfoGathered(String deviceName, boolean isAvailable, String deviceModel) {
        EventDispatcher.dispatchEvent(this, "ProximitySensorInfoGathered", deviceName, isAvailable, deviceModel);
    }

    @SimpleEvent(description = "Triggered when Gyroscope sensor info is gathered")
    public void GyroscopeSensorInfoGathered(String deviceName, boolean isAvailable, String deviceModel) {
        EventDispatcher.dispatchEvent(this, "GyroscopeSensorInfoGathered", deviceName, isAvailable, deviceModel);
    }

    @SimpleEvent(description = "Triggered when Accelerometer sensor info is gathered")
    public void AccelerometerSensorInfoGathered(String deviceName, boolean isAvailable, String deviceModel) {
        EventDispatcher.dispatchEvent(this, "AccelerometerSensorInfoGathered", deviceName, isAvailable, deviceModel);
    }

    @SimpleEvent(description = "Triggered when Light sensor info is gathered")
    public void LightSensorInfoGathered(String deviceName, boolean isAvailable, String deviceModel) {
        EventDispatcher.dispatchEvent(this, "LightSensorInfoGathered", deviceName, isAvailable, deviceModel);
    }

    // ================= FUNCTIONS =================

    @SimpleFunction(description = "Get Proximity Sensor Info")
    public void GetProximitySensorInfo() {
        getSensorInfo(Sensor.TYPE_PROXIMITY, "Proximity");
    }

    @SimpleFunction(description = "Get Gyroscope Sensor Info")
    public void GetGyroscopeSensorInfo() {
        getSensorInfo(Sensor.TYPE_GYROSCOPE, "Gyroscope");
    }

    @SimpleFunction(description = "Get Accelerometer Sensor Info")
    public void GetAccelerometerSensorInfo() {
        getSensorInfo(Sensor.TYPE_ACCELEROMETER, "Accelerometer");
    }

    @SimpleFunction(description = "Get Light Sensor Info")
    public void GetLightSensorInfo() {
        getSensorInfo(Sensor.TYPE_LIGHT, "Light");
    }

    // ================= CORE LOGIC =================

    private void getSensorInfo(int sensorType, String sensorName) {
        Sensor sensor = sensorManager.getDefaultSensor(sensorType);

        boolean isAvailable = (sensor != null);
        String name = isAvailable ? sensor.getName() : "Not Available";
        String vendor = isAvailable ? sensor.getVendor() : "Unknown";

        switch (sensorName) {
            case "Proximity":
                ProximitySensorInfoGathered(name, isAvailable, vendor);
                break;

            case "Gyroscope":
                GyroscopeSensorInfoGathered(name, isAvailable, vendor);
                break;

            case "Accelerometer":
                AccelerometerSensorInfoGathered(name, isAvailable, vendor);
                break;

            case "Light":
                LightSensorInfoGathered(name, isAvailable, vendor);
                break;
        }
    }
}