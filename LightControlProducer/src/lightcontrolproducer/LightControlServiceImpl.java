package lightcontrolproducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightControlServiceImpl implements LightControlService {

	private Map<String, Boolean> lightStatus;
	private Map<String, Integer> lightBrightness;
	private Map<String, String> lightColor;
	private Map<String, Map<String, Object>> presets;

	public LightControlServiceImpl() {
		lightStatus = new HashMap<>();
		lightBrightness = new HashMap<>();
		lightColor = new HashMap<>();
		presets = new HashMap<>();

		// Initialize light status, brightness, and color
		
		addLight("kitchen", false, 100, "white");
        addLight("living_room", false, 100, "white");

		// Create presets
		createPresets();
	}

	private void createPresets() {
		// Create presets and add them
		Map<String, Object> preset1 = createPreset("Evening Ambiance", true, 50, "warm", true, 75, "blue");
		addPreset("Evening Ambiance", preset1);

		Map<String, Object> preset2 = createPreset("Daytime Brightness", false, 0, "white", false, 0, "white");
		addPreset("Daytime Brightness", preset2);
	}

	private Map<String, Object> createPreset(String presetName, boolean kitchenStatus, int kitchenBrightness,
			String kitchenColor, boolean livingRoomStatus, int livingRoomBrightness, String livingRoomColor) {
		Map<String, Object> preset = new HashMap<>();
		preset.put("kitchen", createLightSettings(kitchenStatus, kitchenBrightness, kitchenColor));
		preset.put("living_room", createLightSettings(livingRoomStatus, livingRoomBrightness, livingRoomColor));
		return preset;
	}

	private Map<String, Object> createLightSettings(boolean status, int brightness, String color) {
		Map<String, Object> settings = new HashMap<>();
		settings.put("status", status);
		settings.put("brightness", brightness);
		settings.put("color", color);
		return settings;
	}
	
	@Override
    public void addLight(String lightId, boolean status, int brightness, String color) {
        lightStatus.put(lightId, status);
        lightBrightness.put(lightId, brightness);
        lightColor.put(lightId, color);
        System.out.println("Light " + lightId + " added");
    }

	@Override
	public void turnOnLight(String lightId) {
		if (lightStatus.containsKey(lightId)) {
			lightStatus.put(lightId, true);
			System.out.println("Light " + lightId + " turned on");
		} else {
			System.out.println("Light " + lightId + " not found");
		}
	}

	@Override
	public void turnOffLight(String lightId) {
		if (lightStatus.containsKey(lightId)) {
			lightStatus.put(lightId, false);
			System.out.println("Light " + lightId + " turned off");
		} else {
			System.out.println("Light " + lightId + " not found");
		}
	}

	@Override
    public String getLightStatus() {
        StringBuilder summary = new StringBuilder("Light Status Summary:\n");
        for (Map.Entry<String, Boolean> entry : lightStatus.entrySet()) {
            summary.append(entry.getKey()).append(": ").append(entry.getValue() ? "On" : "Off").append("\n");
        }
        return summary.toString();
    }

	@Override
	public void dimLight(String lightId, int brightness) {
		if (lightBrightness.containsKey(lightId)) {
			if (brightness >= 0 && brightness <= 100) {
				lightBrightness.put(lightId, brightness);
				System.out.println("Light " + lightId + " brightness set to " + brightness + "%");
			} else {
				System.out.println("Invalid brightness level");
			}
		} else {
			System.out.println("Light " + lightId + " not found");
		}
	}

	@Override
	public void changeLightColor(String lightId, String color) {
		if (lightColor.containsKey(lightId)) {
			lightColor.put(lightId, color);
			System.out.println("Light " + lightId + " color changed to " + color);
		} else {
			System.out.println("Light " + lightId + " not found");
		}
	}

	@Override
	public void activatePreset(String presetName) {
		if (presets.containsKey(presetName)) {
			Map<String, Object> preset = presets.get(presetName);
			for (Map.Entry<String, Object> entry : preset.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (key.equals("lightId") && value instanceof String) {
					String lightId = (String) value;
					if (presets.containsKey(lightId)) {
						Map<String, Object> lightSettings = presets.get(lightId);
						if (lightSettings.containsKey("status")) {
							boolean status = (boolean) lightSettings.get("status");
							if (status) {
								// Turn on light
								turnOnLight(lightId);
							} else {
								// Turn off light
								turnOffLight(lightId);
							}
						}
						if (lightSettings.containsKey("brightness")
								&& lightSettings.get("brightness") instanceof Integer) {
							int brightness = (int) lightSettings.get("brightness");
							// Dim light
							dimLight(lightId, brightness);
						}
						if (lightSettings.containsKey("color") && lightSettings.get("color") instanceof String) {
							String color = (String) lightSettings.get("color");
							// Change light color
							changeLightColor(lightId, color);
						}
					}
				}
			}
			System.out.println("Preset " + presetName + " activated");
		} else {
			System.out.println("Preset " + presetName + " not found");
		}
	}

	@Override
	public void addPreset(String presetName, Map<String, Object> preset) {
		presets.put(presetName, preset);
	}
	
	@Override
    public Map<String, Object> getPresetDetails(String presetName) {
        return presets.get(presetName);
    }

	
	@Override
    public List<String> getAvailablePresets() {
        return new ArrayList<>(presets.keySet());
    }
	
	@Override
	public Map<String, String> getAllLights() {
	    Map<String, String> allLights = new HashMap<>();
	    for (Map.Entry<String, Boolean> entry : lightStatus.entrySet()) {
	        String lightId = entry.getKey();
	        boolean status = entry.getValue();
	        String statusString = status ? "On" : "Off";
	        allLights.put(lightId, statusString);
	    }
	    return allLights;
	}
}
