package io.github.patpatchpatrick.alphapigeon.resources;

public class SettingsManager {


    //Class to manage settings and updates to settings via mobile device database/preferences

    public static DatabaseAndPreferenceManager databaseAndPreferenceManager;

    //Booleans for Settings
    public static boolean musicSettingIsOn = true;
    public static boolean gameSoundsSettingIsOn = true;
    public static boolean touchSettingIsOn = true;
    public static boolean accelerometerSettingIsOn = true;

    //Floats for Sliders
    //--All slider values are between 0 and 1
    public static final float MINIMUM_SLIDER_VALUE = 0f;
    public static final float MAXIMUM_SLIDER_VALUE = 1f;
    public static float musicVolume = 0.5f;
    public static float gameVolume = 0.5f;
    public static float touchSensitivity = 0.5f;
    public static float accelSensitivity = 0.5f;

    public static void updateSettings(){

        //Fetch current settings from the mobile device database/preferences

        if (databaseAndPreferenceManager != null){

            //Buttons
            musicSettingIsOn = databaseAndPreferenceManager.isMusicOn();
            gameSoundsSettingIsOn = databaseAndPreferenceManager.isGameSoundsOn();
            touchSettingIsOn = databaseAndPreferenceManager.isTouchControlsOn();
            accelerometerSettingIsOn = databaseAndPreferenceManager.isAccelButtonOn();

            //Sliders
            musicVolume = databaseAndPreferenceManager.getMusicVolumeSliderValue();
            gameVolume = databaseAndPreferenceManager.getGameVolumeSliderValue();
            touchSensitivity = databaseAndPreferenceManager.getTouchSensitivitySliderValue();
            accelSensitivity = databaseAndPreferenceManager.getAccelSensitivitySliderValue();

            //If the game sounds setting is off, set game volume to 0
            if (!gameSoundsSettingIsOn){
                gameVolume = 0;
            }

        }

    }

    public static void toggleMusicSetting(Boolean isOn){

        //If the setting changed, update the mobile device preferences

        if (musicSettingIsOn != isOn && databaseAndPreferenceManager != null){
            databaseAndPreferenceManager.toggleMusicOnOff(isOn);
            musicSettingIsOn = isOn;
            Sounds.toggleBackgroundMusic(isOn);
        }
    }

    public static void toggleGameSoundsSetting(Boolean isOn){

        //If the setting changed, update the mobile device preferences

        if (gameSoundsSettingIsOn != isOn && databaseAndPreferenceManager != null){
            databaseAndPreferenceManager.toggleGameSoundsOnOff(isOn);
            gameSoundsSettingIsOn = isOn;
        }

    }

    public static void toggleTouchSetting(Boolean isOn){

        //If the setting changed, update the mobile device preferences

        if (touchSettingIsOn != isOn && databaseAndPreferenceManager != null){
            databaseAndPreferenceManager.toggleTouchControlsOnOff(isOn);
            touchSettingIsOn = isOn;
        }

    }

    public static void toggleAccelerometerSetting(Boolean isOn){

        //If the setting changed, update the mobile device preferences

        if (accelerometerSettingIsOn != isOn && databaseAndPreferenceManager != null){
            databaseAndPreferenceManager.toggleAccelButtonOnOff(isOn);
            accelerometerSettingIsOn = isOn;
        }

    }

    public static void toggleMusicVolumeSetting(float value){

        //If the setting changed, update the mobile device preferences

        if (musicVolume != value && databaseAndPreferenceManager != null){
            databaseAndPreferenceManager.toggleMusicVolumeSlider(value);
            musicVolume = value;
            Sounds.setBackgroundMusicVolume(value);
        }

    }

    public static void toggleGameVolumeSetting(float value){

        //If the setting changed, update the mobile device preferences

        if (gameVolume != value && databaseAndPreferenceManager != null){
            databaseAndPreferenceManager.toggleGameVolumeSlider(value);
            gameVolume = value;
        }

    }

    public static void toggleTouchSensitivitySetting(float value){

        //If the setting changed, update the mobile device preferences

        if (touchSensitivity != value && databaseAndPreferenceManager != null){
            databaseAndPreferenceManager.toggleTouchSensitivity(value);
            touchSensitivity = value;
        }

    }

    public static void toggleAccelSensitivitySetting(float value){

        //If the setting changed, update the mobile device preferences

        if (accelSensitivity != value && databaseAndPreferenceManager != null){
            databaseAndPreferenceManager.toggleAccelSensitivity(value);
            accelSensitivity = value;
        }

    }



}
