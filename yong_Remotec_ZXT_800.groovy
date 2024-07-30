/*
 * Remotec ZXT-800 driver for Hubitat
 *
 *
 * Copyright 2024 Yong Guy(yong_huang)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Note: 1.Default device has no AV ir data , it should use app or ir learning to get data
 *     2.Learning function is a copy operation , which means it learn any IR what you want to learn and  command is a trigger function
 *     3.AV endpoint is EP2 EP3 EP4, the first line is ep and the second is key function . 
 * release version: 1.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import groovy.transform.Field
metadata
{
  definition (
    name      : "Remotec ZXT-800" ,
    namespace : "yong"       ,
    author    : "Yong Guy"
  )
  {
    //////////////////
    // CAPABILITIES //
    //////////////////
    capability "Thermostat"
    capability "Configuration"
    capability "Polling"
    capability "Sensor"
    capability "Battery"
    capability "Switch"
    capability "Refresh"
    capability "Actuator"
    capability "Temperature Measurement"
    command "AcCodeSelection", [[name:"aclocation",type:"STRING", description:"local IR code selection"]]
    command "BleAdvertising", [[description:"Trigger ble advertising"]]
      
    command "ZAcLearning", [[name:"learninglocation",type:"ENUM", description:"location for IR code learning", constraints:["OFF","ON" ,"17°C COOL","18°C COOL","19°C COOL","20°C COOL","21°C COOL","22°C COOL","23°C COOL","24°C COOL","25°C COOL","26°C COOL","27°C COOL","28°C COOL","29°C COOL","30°C COOL","17°C HEAT","18°C HEAT","19°C HEAT","20°C HEAT","21°C HEAT","22°C HEAT","23°C HEAT","24°C HEAT","25°C HEAT","26°C HEAT","27°C HEAT","28°C HEAT","29°C HEAT","30°C HEAT","DRY MODE"  ,"AUTO MODE" ,"FAN MODE" ]]] 
    
      command "ZAvControl", [[name:"avchannel",type:"ENUM", description:"select av channel",constraints:["2", "3","4"]],[name:"avbutton",type:"ENUM", description:"select av button", constraints:["Power","Input","Menu","Smart","Guide","Back","Up","Down","OK","Left","Right","VOL+","VOL-","Mute","Home","CH+","CH-","Skip-","Stop","Skip+","Play","Pause","Rewind","Record","FastForward","Red","Green","Yellow","Blue","0","1","2","3","4","5","6","7","8","9","Info","Text"]]]
    
      command "ZAvLearning", [[name:"avlearningchannel",type:"ENUM", description:"select av channel",constraints:["2", "3","4"]],[name:"avlearningbutton",type:"ENUM", description:"select av button", constraints:["Power","Input","Menu","Smart","Guide","Back","Up","Down","OK","Left","Right","VOL+","VOL-","Mute","Home","CH+","CH-","Skip-","Stop","Skip+","Play","Pause","Rewind","Record","FastForward","Red","Green","Yellow","Blue","0","1","2","3","4","5","6","7","8","9","Info","Text"]]]
     
     command "ZChannelDown", [[name:"channeldown",type:"ENUM", description:"select av channel",constraints:["2", "3","4"]]]
     command "ZChannelUp", [[name:"channelup",type:"ENUM", description:"select av channel",constraints:[ "2", "3","4"]]]
     command "ZVolUp", [[name:"volup",type:"ENUM", description:"select av channel",constraints:["2", "3","4"]]]
     command "ZVolDown", [[name:"voldown",type:"ENUM", description:"select av channel",constraints:[ "2", "3","4"]]]
     command "ZTvOK", [[name:"ok",type:"ENUM", description:"select av channel",constraints:["2", "3","4"]]]
      
    //////////////
    // COMMANDS //
    //////////////                  
    command "fanLow"
    command "fanMedium"
    command "fanHigh"

    command "swingModeOn"
    command "swingModeOff"

    ////////////////
    // ATTRIBUTES //
    ////////////////

    attribute "coolingSetpoint"    , "number"
    attribute "heatingSetpoint"    , "number"
    attribute "thermostatSetpoint" , "number"
    attribute "temperature"        , "number"
    attribute "swingMode"          , "enum" , [ "true" , "false" ]
    attribute "remoteCode"         , "number"
    attribute "reportTempLevel"    , "string"
    attribute "reportTime"         , "string"
    attribute "internalInfrared"   , "boolean"
    attribute "temperatureOffset"  , "enum"
    attribute "schedule"           , "json_object"

    attribute "supportedThermostatModes"    , "dynamic_enum"
    attribute "supportedThermostatFanModes" , "dynamic_enum"

    attribute "thermostatMode" , "enum" ,
      [
        "on"           ,
        "auto"           ,
        "off"            ,
        "heat"           ,
        "emergency heat" ,
        "cool"
      ]

    attribute "thermostatFanMode" , "enum" ,
      [
        "on"        ,
        "circulate" ,
        "auto"      ,
        "low"       ,
        "medium"    ,
        "high"
      ]

    /////////////////
    // FINGERPRINT //
    /////////////////

    fingerprint (
      mfr            : "5254" ,
      prod           : "0100" ,
      deviceId          : "8493" ,
      deviceJoinName : "Remotec ZXT-800"
    )
     fingerprint (
      mfr            : "5254" ,
      prod           : "0101" ,
      deviceId          : "8493" ,
      deviceJoinName : "Remotec ZXT-800"
    )
    fingerprint (
      mfr            : "5254" ,
      prod           : "0102" ,
      deviceId          : "8493" ,
      deviceJoinName : "Remotec ZXT-800"
    )
     fingerprint (
      mfr            : "5254" ,
      prod           : "0103" ,
      deviceId          : "8493" ,
      deviceJoinName : "Remotec ZXT-800"
    )
    fingerprint (
      mfr            : "5254" ,
      prod           : "0104" ,
      deviceId          : "8493" ,
      deviceJoinName : "Remotec ZXT-800"
    )
     fingerprint (
      mfr            : "5254" ,
      prod           : "0109" ,
      deviceId          : "8493" ,
      deviceJoinName : "Remotec ZXT-800"
    )
    fingerprint (
      mfr            : "5254" ,
      prod           : "010A" ,
      deviceId          : "8493" ,
      deviceJoinName : "Remotec ZXT-800"
    )
    fingerprint (
      mfr            : "5254" ,
      prod           : "0004" ,
      deviceId          : "8492" ,
      deviceJoinName : "Remotec ZXT-800"
    )
  }

  /////////////////
  // PREFERENCES //
  /////////////////
  preferences
  {
    /////////////
    // LOGGING //
    /////////////

    input (
      name         : "debugLogEnable"       ,
      type         : "bool"                 ,
      title        : "Enable Debug Logging" ,
      defaultValue : true
    )

    input (
      name         : "infoLogEnable"       ,
      type         : "bool"                ,
      title        : "Enable Info Logging" ,
      defaultValue : true
    )


    ///////////////////////////
    // ZXT-600 CONFIGURATION //
    ///////////////////////////
      
    input (
      name         : "temperatureOffset"  ,
      type         : "enum"             ,
      options      : TEMP_VALUES.keySet().toList() ,
      title        : "Calibrate Temperature Reading" ,
      defaultValue : "0°C"
    )
      
    input (
      name         : "internalInfrared"   ,
      type         : "bool"               ,
      title        : "Set Built-in IR Emitter Control" ,
      defaultValue : true
    )

    input (
      name         : "reportTempLevel" ,
      type         : "enum"            ,
      options      : REPORT_TEMP_VALUES.keySet().toList()     ,
      title        : "Temperature change reporting threshold" ,
      defaultValue : "0°C"
    )

    input (
      name         : "reportTime" ,
      type         : "enum"       ,
      options      : REPORT_TIME_VALUES.keySet().toList() ,
      title        : "Temperature and Humidity Auto Report" ,
      defaultValue : "Disable"
    ) 
  }
}

private Integer getREMOTE_CODE_PARAM() { 0x1B }
private Integer getREMOTE_CODE_SIZE()  {    2 }

private Integer getTEMP_OFFSET_PARAM() { 0x25 }
private Integer getTEMP_OFFSET_SIZE()  {    1 }
private Map    getTEMP_VALUES()
 {[
 "0°C"    : 0x00,
 "0.5°C"  : 0x01,
 "1°C"    : 0x02,
 "1.5°C"  : 0x03,
 "2°C"    : 0x04,
 "2.5°C"  : 0x05,
 "3°C"    : 0x06,
 "3.5°C"  : 0x07,
 "4°C"    : 0x08,
 "4.5°C"  : 0x09,
 "5°C"    : 0x0A,
 "-0.5°C" : 0xFF,
 "-1°C"   : 0xFE,
 "-1.5°C" : 0xFD,
 "-2°C"   : 0xFC,
 "-2.5°C" : 0xFB,
 "-3°C"   : 0xFA,
 "-3.5°C" : 0xF9,
 "-4°C"   : 0xF8,
 "-4.5°C" : 0xF7,
 "-5°C"   : 0xF6  
 ]}


private Integer getSWING_MODE_PARAM()  { 0x21 }
private Integer getSWING_MODE_SIZE()   {    1 }
private Map     getSWING_MODE_VALUES()
{[
  "true"  : 0x01 ,
  "false" : 0x00
]}

private Integer getINTERNAL_IR_PARAM() { 0x20 }
private Integer getINTERNAL_IR_SIZE()  {    1 }
private Map     getINTERNAL_IR_VALUES()
{[
  "true"  : 0xFF ,
  "false" : 0x00
]}

private Integer getREPORT_TEMP_PARAM() { 0x1E }
private Integer getREPORT_TEMP_SIZE()  {    1 }
private Map     getREPORT_TEMP_VALUES()
{[
  "off" : 0x00 ,
  "0.5" : 0x01 ,
  "1.0" : 0x02 ,
  "1.5" : 0x03 ,
  "2.0" : 0x04 ,
  "2.5" : 0x05 ,
  "3.0" : 0x06 ,
  "3.5" : 0x07 ,
  "4.0" : 0x08
]}

private Integer getREPORT_TIME_PARAM() { 0x22 }
private Integer getREPORT_TIME_SIZE()  {    1 }
private Map     getREPORT_TIME_VALUES()
{[
   "Disable":0x00,
   "15 minutes":0x01, 
   "30 minutes":0x02,
   "1 hour"  :  0x03,
   "2 hours" :  0x04,
   "3 hours" :  0x05,
   "4 hours" :  0x06,
   "8 hours" :  0x07,
]}

private Integer[] getALL_PARAMS()
{[
  REMOTE_CODE_PARAM ,
  TEMP_OFFSET_PARAM ,
  SWING_MODE_PARAM  ,
  INTERNAL_IR_PARAM ,
  REPORT_TEMP_PARAM ,
  REPORT_TIME_PARAM
]}

private Map getZWAVE_COMMAND_VERSIONS()
{[
  0x20 : 2 , // Basic
  0x27 : 1 , // Switch All
  0x31 : 2 , // Sensor Multilevel
  0x40 : 2 , // Thermostat Mode
  0x43 : 2 , // Thermostat Setpoint
  0x44 : 2 , // Thermostat Fan Mode
  0x70 : 4 , // Configuration
  0x72 : 1 , // Manufacturer Specific
  0x80 : 1 , // Battery
  0x86 : 2 ,   // Version
  0x8E : 2 , //COMMAND_CLASS_MULTI_CHANNEL_ASSOCIATION 
  0x9F : 1,   // COMMAND_CLASS_SECURITY_2
  0x94: 1,    // simple av 
  0x60: 4,
  0x87: 4, //COMMAND_CLASS_INDICATOR
  0x7A: 4,
  
]}

private Map getTHERMOSTAT_MODE_MAP()
{[
  off            : hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_OFF            ,
  heat           : hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_HEAT           ,
  emergency_heat : hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_AUXILIARY_HEAT ,
  cool           : hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_COOL           ,
  auto           : hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_AUTO           ,
  fan            : hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_FAN_ONLY       ,
  dry            : hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_DRY_AIR				,
  resume			  : hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_RESUME 
]}

private Map getTHERMOSTAT_FAN_MODE_MAP()
{[
  auto      : hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_AUTO_LOW    ,
  circulate : hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_CIRCULATION ,
  low       : hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_LOW         ,
  medium    : hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_MEDIUM      ,
  high      : hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_HIGH
]}
 
@Field static Map SET_AC_LEARNING =[
 "OFF"            :0,     
 "ON"             :1,     
 "17°C COOL"        :2,    
 "18°C COOL"        :3,    
 "19°C COOL"        :4,    
 "20°C COOL"        :5,    
 "21°C COOL"        :6,    
 "22°C COOL"        :7,    
 "23°C COOL"        :8,    
 "24°C COOL"        :9,    
 "25°C COOL"        :10,   
 "26°C COOL"        :11,   
 "27°C COOL"        :12,   
 "28°C COOL"        :13,   
 "29°C COOL"        :14,   
 "30°C COOL"        :15,   
 "17°C HEAT"        :16,   
 "18°C HEAT"        :17,   
 "19°C HEAT"        :18,   
 "20°C HEAT"        :19,   
 "21°C HEAT"        :20,   
 "22°C HEAT"        :21,   
 "23°C HEAT"        :22,   
 "24°C HEAT"        :23,   
 "25°C HEAT"        :24,   
 "26°C HEAT"        :25,   
 "27°C HEAT"        :26,   
 "28°C HEAT"        :27,   
 "29°C HEAT"        :28,   
 "30°C HEAT"        :29,   
 "DRY MODE"         :30,    
 "AUTO MODE"         :31,    
 "FAN MODE"         :32,
 ]

 @Field static Map SET_TV=[
 "Power"            :0x0027,
 "Input"            :0x0026,
 "Menu "            :0x001D,
 "Smart"            :0x0185,
 "Guide"            :0x001C,
 "Back"             :0x004B,
 "Up"               :0x001E,
 "Down"             :0x001F,
 "OK"               :0x0024,
 "Left"             :0x0020,
 "Right"            :0x0021,
 "VOL+"             :0x0003,
 "VOL-"             :0x0002,
 "Mute"             :0x0001,
 "Home"             :0x00AF,
 "CH+"              :0x0004,
 "CH-"              :0x0005,
 "Skip-"            :0x011C,
 "Stop"             :0x0014,
 "Skip+"            :0x011B,
 "Play"             :0x0013,
 "Pause"            :0x0015,
 "Rewind"           :0x0017,
 "Record"           :0x0019,
 "Fast Forward"     :0x0016,
 "Red"              :0x009D,
 "Green"            :0x009B,
 "Yellow"           :0x009F,
 "Blue"             :0x009A,
 "Info"             :0x0011,
 "Text"             :0x013F 
 ]
@Field static Map TV_LEARNING=[
  "Power"             :0,   
  "Input"             :1,   
  "Menu "             :2,  
  "Smart"             :3,  
  "Guide"             :4,  
  "Back"              :5,  
  "Up"                :6,  
  "Down"              :7,  
  "OK"                :8,  
  "Left"              :9,  
  "Right"             :10, 
  "VOL+"              :11, 
  "VOL-"              :12, 
  "Mute"              :13, 
  "Home"              :14, 
  "CH+"               :15, 
  "CH-"               :16, 
  "Skip-"             :17, 
  "Stop"              :18, 
  "Skip+"             :19, 
  "Play"              :20, 
  "Pause"             :21, 
  "Rewind"            :22, 
  "Record"            :23, 
  "Fast Forward"      :24, 
  "Red"               :25, 
  "Green"             :26, 
  "Yellow"            :27, 
  "Blue"              :28, 
  "0"								  :29, 
	"1"								  :30, 
	"2"								  :31, 
	"3"								  :32, 
	"4"								  :33,
	"5"								  :34,
	"6"								  :35,
	"7"								  :36,
	"8"								  :37,
	"9"								  :38,
  "Info"              :39,
  "Text"              :40
 ]
 
 
def Integer coolingSetpointMin = 16
def Integer coolingSetpointMax = 31

def Integer heatingSetpointMin = 16
def Integer heatingSetpointMax = 31
def Integer setpointStepSize = 1

private void debugLog ( message )
{
  if ( debugLogEnable )
    log.debug "${ device.displayName } DEBUG : ${ message }"
}

private void infoLog ( message )
{
  if ( infoLogEnable )
    log.info "${ device.displayName } INFO : ${ message }"
}

void sendToDevice(hubitat.zwave.Command cmd) {
    sendHubCommand(new hubitat.device.HubAction(cmd.format(), hubitat.device.Protocol.ZWAVE))
}

void BleAdvertising()
{
    debugLog( "BleAdvertising" )
    List<hubitat.zwave.Command> cmds=[]
    Integer codeBytes = 0xFF
    cmds.add(zwave.configurationV1.configurationSet(parameterNumber: 0x3c, size:1, configurationValue:  [codeBytes]))
    sendToDevice(cmds)
}
// select ac ir code 
void AcCodeSelection(aclocation) {
     debugLog( "AcCodeSelection:  (${ aclocation.toInteger() })" )
    List<hubitat.zwave.Command> cmds=[]
      Short[] codeBytes = parseRemoteCode(aclocation.toInteger() ) 
     cmds.add(zwave.configurationV1.configurationSet(parameterNumber: 0x1B, size:2, configurationValue:  (List) codeBytes))
    sendToDevice(cmds)
}

// ac learning
void ZAcLearning(learninglocation) {    
     debugLog( "ZAcLearning:  (${ SET_AC_LEARNING[learninglocation] })" )
     List<hubitat.zwave.Command> cmds=[]
     Integer val = (Integer)SET_AC_LEARNING[learninglocation]
     cmds.add(zwave.configurationV1.configurationSet(parameterNumber: 25, size:1, configurationValue:(List)[val]))
    sendToDevice(cmds)
} 

private EncapCmd(cmd, ep) {
  String hexString = device.deviceNetworkId
  Short sep =(Short) Integer.parseInt(hexString, 16);
  List<hubitat.zwave.Command> cmds=[]
  cmds.add(zwave.multiChannelV3.multiChannelCmdEncap(destinationEndPoint:ep, sourceEndPoint : sep).encapsulate(cmd))
  sendToDevice(cmds)  
}

private simpleAvControlSetCmd(keyHex, ep) {
	state.sequence = (state.sequence ?: 0) + 1
   EncapCmd(zwave.simpleAvControlV1.simpleAvControlSet(commands :[keyHex], itemId: 0x0000, keyAttributes: 0x00, sequenceNumber: state.sequence), ep)
}

// av control
void ZAvControl(avchannel, avbutton) {   
  debugLog( "ZAvControl:  (${ TV_LEARNING[avlearningbutton] })" )
  simpleAvControlSetCmd(SET_TV[avbutton],  avchannel.toInteger())
}

// av learning 
void ZAvLearning(avlearningchannel, avlearningbutton) { 
    debugLog( "ZAvLearning:  (${ TV_LEARNING[avlearningbutton] })" )
    Integer val = (Integer)TV_LEARNING[avlearningbutton]
    EncapCmd(configureCommands(26, 1,  [ val ]),avlearningchannel.toInteger() ) 
}

void ZChannelDown(channeldown) {   
  debugLog( "ZChannelDown)" )
  simpleAvControlSetCmd(SET_TV["CH-"],  channeldown.toInteger())
}

void ZChannelUp(channelup) {   
  debugLog( "ZChannelDown)" )
  simpleAvControlSetCmd(SET_TV["CH+"],  channelup.toInteger())
}

void ZVolUp(volup) {   
  debugLog( "ZVolUp)" )
  simpleAvControlSetCmd(SET_TV["VOL+"],  volup.toInteger())
}

void ZVolDown(voldown) {   
  debugLog( "ZVolDown)" )
  simpleAvControlSetCmd(SET_TV["VOL-"],  voldown.toInteger())
}  

void ZTvOK(ok) {   
  debugLog( "ZTvOK)" )
  simpleAvControlSetCmd(SET_TV["OK"],  ok.toInteger())
} 

List refresh ()
{
  infoLog( "REFRESH" )
 
  List commands = [
    *ALL_PARAMS.collect { zwave.configurationV1.configurationGet( parameterNumber : it ) } ,
    zwave.sensorMultilevelV6.sensorMultilevelGet(sensorType: 0x01) ,
    zwave.sensorMultilevelV6.sensorMultilevelGet(sensorType: 0x05) ,
    zwave.batteryV1.batteryGet() ,
    zwave.thermostatModeV2.thermostatModeSupportedGet(),
    zwave.thermostatFanModeV3.thermostatFanModeSupportedGet(),
    zwave.thermostatModeV2.thermostatModeGet()       ,
    zwave.thermostatFanModeV3.thermostatFanModeGet() ,
    zwave.thermostatSetpointV2.thermostatSetpointGet (
    setpointType: hubitat.zwave.commands.thermostatsetpointv1.ThermostatSetpointSet.SETPOINT_TYPE_COOLING_1
    ) ,
    zwave.thermostatSetpointV2.thermostatSetpointGet (
      setpointType: hubitat.zwave.commands.thermostatsetpointv1.ThermostatSetpointSet.SETPOINT_TYPE_HEATING_1
    ) ,
   // zwave.basicV1.basicGet()
  ]

  debugLog( "REFRESH : commands : ${ commands.inspect() }" )
  delayBetween( commands.collect { it.format() } , 1000 ) 
}

private Map getCONFIG_STATE()
{[
  "temperatureOffset" : ( (String) temperatureOffset   ) == ( (String) device.currentState( "temperatureOffset" )?.value ) ,
  "remoteCode"        : ( (String) remoteCode          ) == ( (String) device.currentState( "remoteCode"        )?.value ) ,
  "swingMode"         : ( (String) state.lastSwingMode ) == ( (String) device.currentState( "swingMode"         )?.value ) ,
  "internalInfrared"  : ( (String) internalInfrared    ) == ( (String) device.currentState( "internalInfrared"  )?.value ) ,
  "reportTempLevel"   : ( (String) reportTempLevel     ) == ( (String) device.currentState( "reportTempLevel"   )?.value ) ,
  "reportTime"        : ( (String) reportTime          ) == ( (String) device.currentState( "reportTime"        )?.value ) ,
]}

List configure ()
{
  infoLog( "configure" )  
  List commands    = []
  Map  configState = CONFIG_STATE
  if ( !CONFIG_STATE.temperatureOffset ) commands.addAll(*[ configureTemperatureOffset() ]) 
  if ( !CONFIG_STATE.internalInfrared  ) commands.addAll(*[ configureInternalInfrared()  ])
  if ( !CONFIG_STATE.reportTempLevel  ) commands.addAll(*[ configureReportTempLevel()   ])
  if ( !CONFIG_STATE.reportTime     ) commands.addAll(*[ configureReportTime()        ])
  if (!state.initialized)
  {
    infoLog( "initialized" ) 
    sensorPoll()
    setSupportedThermostatModes()
    setSupportedThermostatFanModes()    
    state.initialized = true
    commands.size = 0
  }
  if ( commands.size < 1 )
  {
    debugLog( "CONFIGURE : no commands to run" )
  }
  else
  {
    debugLog( "CONFIGURE COMMANDS : ${ commands.inspect() }" )
    delayBetween ( commands.collect{ it.format() } , 2500 )
  }
}

List updated ()
{
  infoLog( "UPDATED" )
  refresh()
  configure()
}

Map parse ( String description )
{
//  infoLog( "PARSE : description : ${ description }" )

  def command = zwave.parse( description , ZWAVE_COMMAND_VERSIONS )
 // infoLog( "PARSE : command : ${ command.inspect() }" )

  if ( command ) zwaveCommand( command )
    else [:]
}

void setSchedule ()
{
  infoLog( "setSchedule unsupported" )
}

void sensorPoll()
{
  List commands = [
    zwave.sensorMultilevelV6.sensorMultilevelGet(sensorType: 0x01) ,
    zwave.sensorMultilevelV6.sensorMultilevelGet(sensorType: 0x05) 
   ]
  delayBetween( commands.collect { it.format() } , 2500 )
}

void healthPoll()
{
  infoLog( "healthPoll" )
  sensorPoll()
}

private Map zwaveCommand ( hubitat.zwave.commands.indicatorv3.IndicatorReport command ){
     infoLog( "IndicatorReport" )
}
private Map zwaveCommand ( hubitat.zwave.commands.sensormultilevelv1.SensorMultilevelReport command )
{   
  if ( command.sensorType != 1 )
  {
     data = [
         name  : "humidity" ,
         value : command.scaledSensorValue + "%" 
       ]
  }
  else
  {
      data = [
        name  : "temperature" ,
        value : command.scaledSensorValue + "°C"
      ]
  }
  sendEvent data
  data
 
}

private Map zwaveCommand ( hubitat.zwave.commands.batteryv1.BatteryReport command )
{
  Map data = [
    name      : "battery" ,
    displayed : false     ,
    unit      : '%'
  ]

  if ( command.batteryLevel == 0xFF )
  {
    data.value           = "0"
    data.descriptionText = "${ device.displayName } LOW BATTERY"
  }

  else 
  {
    data.value = (String) command.batteryLevel
    data.descriptionText = "${ device.displayName } BATTERY : ${data.value}"
  }

  sendEvent data
  data
}

private Map zwaveCommand ( hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport command )
{
  Map data = [
    name  : "thermostatMode" ,
    value : THERMOSTAT_MODE_MAP.find { it.value == command.mode }?.key
  ]

  sendEvent data
  data
}

private Map zwaveCommand ( hubitat.zwave.commands.thermostatfanmodev2.ThermostatFanModeReport command )
{
  Map data = [
    name  : "thermostatFanMode" ,
    value : THERMOSTAT_FAN_MODE_MAP.find { it.value == command.fanMode }?.key
  ]

  sendEvent data
  data
}

private Map getSETPOINT_TYPE_MAP()
{[
  cooling : hubitat.zwave.commands.thermostatsetpointv1.ThermostatSetpointReport.SETPOINT_TYPE_COOLING_1 ,
  heating : hubitat.zwave.commands.thermostatsetpointv1.ThermostatSetpointReport.SETPOINT_TYPE_HEATING_1
]}

private Map zwaveCommand ( hubitat.zwave.commands.thermostatsetpointv1.ThermostatSetpointReport command )
{
  debugLog( "ThermostatSetpointReport : ${ command.inspect() }" )

  Map data = [
    value : command.scaledValue ,
    unit  : "C"
  ]

  switch ( SETPOINT_TYPE_MAP.find{ it.value == command.setpointType }?.key )
  {

    case "cooling" :
      data.name = "coolingSetpoint"
      break

    case "heating" :
      data.name = "heatingSetpoint"
      break

    default :
      log.warn( "Received unknown ThermostSetpointReport setpointType : ${ command.inspect() }" )
      return []

  }

  sendEvent data
  data
}

private Map zwaveCommand ( hubitat.zwave.commands.configurationv1.ConfigurationReport command )
{
  Map data = [:]

  debugLog( "getPayload : ${ command.getPayload() }" )

  switch ( command.parameterNumber )
  {
    case REMOTE_CODE_PARAM :
      data.name      = "remoteCode"
      data.displayed = false
      data.value     = parseRemoteBytes( (Short[]) command.configurationValue )
      break

    case TEMP_OFFSET_PARAM :
      data.name      = "temperatureOffset"
      data.displayed = false
      data.value     = command.configurationValue[ 0 ]
      break

    case SWING_MODE_PARAM :
      data.name      = "swingMode"
      data.displayed = false
      data.value     = command.configurationValue[ 0 ] == 1 ? "true" : "false"
      break

    case INTERNAL_IR_PARAM :
      data.name      = "internalInfrared"
      data.displayed = false
      data.value     = INTERNAL_IR_VALUES.find { it.value == command.configurationValue[ 0 ] }?.key
      break

    case REPORT_TIME_PARAM :
      data.name      = "reportTime"
      data.displayed = false
      data.value     = REPORT_TIME_VALUES.find { it.value == command.configurationValue[ 0 ] }?.key
      break

    case REPORT_TEMP_PARAM :
      data.name      = "reportTempLevel"
      data.displayed = false
      data.value     = REPORT_TEMP_VALUES.find { it.value == command.configurationValue[ 0 ] }?.key
      break

    default :
      debugLog( "Unhandled command : ${ command.inspect() }" )
  }

  debugLog( "zwaveCommand : data : ${ data.inspect() }" )

  if ( data != [:] ) sendEvent data
  data
}

 
private void setSupportedThermostatModes ()
{
    List<hubitat.zwave.Command> cmds=[]
    cmds.add(zwave.thermostatModeV2.thermostatModeSupportedGet())
    sendToDevice(cmds)
}
private void setSupportedThermostatFanModes ()
{
    List<hubitat.zwave.Command> cmds=[]
    cmds.add(zwave.thermostatFanModeV2.thermostatFanModeSupportedGet())
    sendToDevice(cmds)
}


private void setThermostatModesGet ()
{
    List<hubitat.zwave.Command> cmds=[]
    cmds.add(zwave.thermostatFanModeV2.thermostatFanModeSupportedGet())
    sendToDevice(cmds)
}
private Map zwaveCommand (hubitat.zwave.commands.thermostatmodev1.ThermostatModeSupportedReport command )
{    
  List modes = []
  if (command.auto) modes << "auto"
  if ( command.cool) modes << "cool"
  if ( command.heat) modes << "heat"
  if ( command.off) modes << "off"
  if ( command.dryAir) modes << "dry"
  if ( command.resume) modes << "resume"
  if ( command.fanOnly)modes << "fanOnly"
  sendEvent( name : "supportedThermostatModes" , value : modes )
}
  
private Map zwaveCommand (hubitat.zwave.commands.thermostatfanmodev2.ThermostatFanModeSupportedReport command )
{  
  List modes = []
  if (command.auto) modes << "auto"
  if ( command.autoHigh) modes << "autoHigh"
  if ( command.high) modes << "high"
  if ( command.low) modes << "low"  
  if ( command.medium ) modes << "medium"  
  sendEvent( name : "supportedThermostatFanModes" , value : modes ) 
}

private List configureTemperatureOffset ()
{
   debugLog( "configureTemperatureOffset" )
   String  opt = temperatureOffset == null ? "0°C" : temperatureOffset
   Integer val = temperatureOffset == null ? 0 : (Integer) TEMP_VALUES[ opt ]
   debugLog( "setTemperatureOffset : ${ temperatureOffset } (${ val })" )
   state.lastTemperatureOffset = temperatureOffset
   sendEvent( name : "temperatureOffset" , value : temperatureOffset )
   configureCommands( TEMP_OFFSET_PARAM , TEMP_OFFSET_SIZE , [ val ] )
}

private List configureRemoteCode ()
{
  if ( remoteCode == null )
  {
    log.warn "remoteCode not set, not setting on device"
    return []
  }

  Short[] codeBytes = parseRemoteCode( (Integer) remoteCode )
  debugLog( "setRemoteCode : ${ remoteCode } (${ codeBytes.inspect() })" )

  state.lastRemoteCode = remoteCode
  sendEvent( name : "remoteCode" , value : remoteCode )

  configureCommands( REMOTE_CODE_PARAM , REMOTE_CODE_SIZE , (List) codeBytes )
}

private List configureSwingMode ()
{
  String cur = device.currentState( "swingMode" )?.value
  if ( cur == null ) cur = "false"

  Integer val = SWING_MODE_VALUES[ cur ]

  if ( val == null )
  {
    log.warn "invalid value '${ cur }' for swingMode"
    return []
  }

  debugLog( "setSwingMode : ${ cur } (${ val })" )

  state.lastSwingMode = cur
  sendEvent( name : "swingMode" , value : cur )

  configureCommands( SWING_MODE_PARAM , SWING_MODE_SIZE , [ val ] )
}

List swingModeOn  () { setSwingMode( "true"  ) }
List swingModeOff () { setSwingMode( "false" ) }

private List setSwingMode ( String val )
{
  sendEvent( name : "swingMode" , value : val )
  pauseExecution( 500 )
  configure()
}

private List configureInternalInfrared ()
{
  String  opt = internalInfrared == null ? "true" : internalInfrared
  Integer val = INTERNAL_IR_VALUES[ opt ]

  if ( val == null )
  {
    log.warn "Invalid value for internalInfrared : ${ opt }"
    return []
  }

  sendEvent( name : "internalInfrared" , value : opt , displayed : false )
  pauseExecution( 500 )

  debugLog( "setInternalInfrared: ${ opt } (${ val })" )
  state.lastInternalInfrared = internalInfrared

  configureCommands( INTERNAL_IR_PARAM , INTERNAL_IR_SIZE , [ val ] )
}

private List configureReportTempLevel ()
{
  String  opt = reportTempLevel == null ? "true" : reportTempLevel
  Integer val = REPORT_TEMP_VALUES[ opt ]

  if ( val == null )
  {
    log.warn "Invalid value for reportTempLevel : ${ opt }"
    return []
  }

  sendEvent( name : "reportTempLevel" , value : opt , displayed : false )
  pauseExecution( 500 )

  debugLog( "setReportTempLevel: ${ opt } (${ val })" )
  state.lastReportTempLevel = reportTempLevel

  configureCommands( REPORT_TEMP_PARAM , REPORT_TEMP_SIZE , [ val ] )
}

private List configureReportTime ()
{
  String  opt = reportTime == null ? "true" : reportTime
  Integer val = REPORT_TIME_VALUES[ opt ]

  if ( val == null )
  {
    log.warn "Invalid value for reportTime : ${ opt }"
    return []
  }

  sendEvent( name : "reportTime" , value : opt , displayed : false )
  pauseExecution( 500 )

  debugLog( "setReportTime: ${ opt } (${ val })" )
  state.lastReportTime = reportTime

  configureCommands( REPORT_TIME_PARAM , REPORT_TIME_SIZE , [ val ] )
}

List setCoolingSetpoint ()
{
  setCoolingSetpoint( new BigDecimal( device.currentState( "coolingSetpoint" )?.value ) )
}

List setCoolingSetpoint ( BigDecimal value )
{
    debugLog( "setCoolingSetpoint:  (${ value })" )
    setpointStepSize = 1 
    coolingSetpointMin = 16
    coolingSetpointMax = 31
    
  if ( value.remainder( (BigDecimal) setpointStepSize ) != 0 )
  {
    if ( value < state.lastCoolingSetpoint )
      value = state.lastCoolingSetpoint - setpointStepSize

    else if ( value > state.lastCoolingSetpoint )
      value = state.lastCoolingSetpoint + setpointStepSize
  }

  value = Math.max( (BigDecimal) coolingSetpointMin , value )
  value = Math.min( (BigDecimal) coolingSetpointMax , value )
  state.lastCoolingSetpoint = value
  debugLog( "setCoolingSetpoint:  (${ value })" )
  sendEvent( name : "coolingSetpoint" , value : value , unit : "C" )
  pauseExecution( 500 )
 List commands = [
  zwave.thermostatSetpointV2.thermostatSetpointSet (setpointType : 2 ,scale  : 0 ,precision: 0 ,scaledValue  : value )
     ]
  delayBetween( commands.collect { it.format() } , 2500 )
}


List setHeatingSetpoint ()
{
  setHeatingSetpoint( new BigDecimal( device.currentState( "heatingSetpoint" )?.value ) )
}

List setHeatingSetpoint ( BigDecimal value )
{ 
 heatingSetpointMin = 16
 heatingSetpointMax = 31
 setpointStepSize = 1
    
  if ( value.remainder( (BigDecimal) setpointStepSize ) != 0 )
  {
    if ( value < state.lastheatingSetpoint )
      value = state.lastheatingSetpoint - setpointStepSize

    else if ( value > state.lastheatingSetpoint )
      value = state.lastheatingSetpoint + setpointStepSize
  }

  value = Math.max( (BigDecimal) heatingSetpointMin , value )
  value = Math.min( (BigDecimal) heatingSetpointMax , value )
  state.lastHeatingSetpoint = value
  sendEvent( name : "heatingSetpoint" , value : value , unit : "C" )
 pauseExecution( 500 )
 List commands = [
  zwave.thermostatSetpointV2.thermostatSetpointSet (setpointType : 1 ,scale  : 0 ,precision: 0 ,scaledValue  : value )
     ]
  delayBetween( commands.collect { it.format() } , 2500 )   
}

List fanOn        () { setThermostatFanMode( "on"        ) }
List fanOff       () { setThermostatFanMode( "off"       ) }
List fanLow       () { setThermostatFanMode( "low"       ) }
List fanMedium    () { setThermostatFanMode( "medium"    ) }
List fanHigh      () { setThermostatFanMode( "high"      ) }
List fanAuto      () { setThermostatFanMode( "auto"      ) }
List fanCirculate () { setThermostatFanMode( "circulate" ) }

List setThermostatFanMode () { fanOn() }

List setThermostatFanMode ( String mode )
{
  mode = mode.trim()

  if ( !device.currentState( "supportedThermostatFanModes" )?.value?.contains( mode ) )
  {
    debugLog( "Unsupported thermostat fan mode : ${ mode }" )
    return []
  }

  if ( mode == "on" && state.lastThermostatFanMode != null )
    mode = state.lastThermostatFanMode

  List commands = [
    zwave.thermostatFanModeV2.thermostatFanModeSet (
      fanMode: THERMOSTAT_FAN_MODE_MAP[ mode ]
    ) ,
    zwave.thermostatFanModeV2.thermostatFanModeGet()
  ]

  debugLog( "setThermostatFanMode : ${ commands.inspect() }" )
  state.lastThermostatFanMode = mode
  delayBetween( commands.collect { it.format() } , 2500 )
}

List on            () { setThermostatMode( "on"             ) }
List off           () { setThermostatMode( "off"            ) }
List auto          () { setThermostatMode( "auto"           ) }
List fanOnly       () { setThermostatMode( "fan"            ) }
List dry           () { setThermostatMode( "dry"            ) }
List emergencyHeat () { setThermostatMode( "emergency_heat" ) }
List heat          () { setThermostatMode( "heat"           ) }
List cool          () { setThermostatMode( "cool"           ) }

List setThermostatMode () { on() }

List setThermostatMode ( String mode )
{
   debugLog( "setThermostatMode mode : ${ mode }" ) 
  if(mode == "on") mode = "resume"
  if ( !device.currentState( "supportedThermostatModes" )?.value?.contains( mode ) )
  {
    debugLog( "Unsupported thermostat mode : ${ mode }" )
    return []
  }

  List commands = []
  BigDecimal setTemp = null
  def setpointMode = null
  def setpointValue
  switch ( mode )
  {
    case "cool" :
       setpointValue = device.currentState("coolingSetpoint")?.value
      if(setpointValue == null)
      {
          setpointValue = 25
      }
      else
      {
           setpointValue =  ( new BigDecimal( device.currentState( "coolingSetpoint" )?.value ) ).setScale( 0 )
      }
      setTemp      =    setpointValue
      setpointMode = hubitat.zwave.commands.thermostatsetpointv2.ThermostatSetpointSet.SETPOINT_TYPE_COOLING_1
      break

    case "heat" :
      setpointValue = device.currentState("heatingSetpoint")?.value
      if(setpointValue == null)
      {
          setpointValue = 25
      }
      else
      {
        setpointValue = ( new BigDecimal( device.currentState( "heatingSetpoint" )?.value ) ).setScale( 0 )
      }
      setTemp = setpointValue
      setpointMode = hubitat.zwave.commands.thermostatsetpointv2.ThermostatSetpointSet.SETPOINT_TYPE_HEATING_1
      break

    case "emergency_heat" :
    case "dry"  :
    case "fan"  :
    case "auto" :
    case "off"  :  
    case "resume"   :
      break
      
    default :
      debugLog ( "Unknown thermostatMode : ${ mode }" )
      return 
  }

  commands.addAll ([
    zwave.thermostatModeV2.thermostatModeSet( mode: THERMOSTAT_MODE_MAP[ mode ] ) ,    
    zwave.thermostatModeV2.thermostatModeGet() 
  ])

  if ( setTemp != null )
    commands.addAll ([
      zwave.thermostatSetpointV2.thermostatSetpointSet (
        setpointType : setpointMode      ,
        scale        : setTemp.scale     ,
        precision    : setTemp.precision ,
        scaledValue  : setTemp
      ) ,
      zwave.thermostatSetpointV2.thermostatSetpointGet (
        setpointType: setpointMode
      )
    ])

  if ( alwaysSendSwingMode && mode != "off" )
    commands.addAll( configureSwingMode() )

  debugLog( "setThermostatMode : ${ commands.inspect() }" )
  state.lastThermostatMode = mode
  delayBetween( commands.collect { it.format() } , 2500 )
}

private List configureCommands ( Integer param , Integer size , List val )
{[
  zwave.configurationV1.configurationSet (
    configurationValue : val   ,
    parameterNumber    : param ,
    size               : size
  )// ,
  //zwave.configurationV1.configurationGet( parameterNumber : param )
]}


private Map zwaveCommand ( hubitat.zwave.commands.applicationstatusv1.ApplicationBusy command )
{
  debugLog( "ApplicationBusy : ${ command.inspect() }" )
  [:]
}

private Map zwaveCommand ( hubitat.zwave.commands.zipnamingv1.ZipNamingNameReport command )
{
  debugLog( "ZipNamingNameReport : ${ command.inspect() }" )
  [:]
}

private Map zwaveCommand ( hubitat.zwave.commands.basicv1.BasicReport command )
{
  debugLog( "BasicReport : ${ command.inspect() }" )
  [:]
}

private Integer parseRemoteBytes ( Short[] codeBytes )
{
  ( ( (Integer) codeBytes[ 0 ] ) << 8 ) + ( (Integer) codeBytes[ 1 ] )
}

private Short[] parseRemoteCode ( Integer code )
{
  Short[] codeBytes = [ null , null ]
  codeBytes[ 1 ] = code & 0xFF
  codeBytes[ 0 ] = ( code >> 8 ) & 0xFF

  codeBytes
}
