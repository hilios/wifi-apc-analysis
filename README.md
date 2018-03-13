Wifi APC Python Notebooks
===

The analysis algorithms for the WiFi and GPS logs to perform the Automatic Passenger Count (APC).

## Start

Starts the Jupyter notebook at [localhost:8888](http://localhost:8888)

```
$ make
```

## Data

All data collected by the WiFi APC prototype during the field experiments and stored in log files at `data` folder as tab-separeted values.

#### WiFi

Data from the WiFi sniffer with the media access control address (MAC address) of the devices

```python
>>> log.info("{mac_address}\t{ssid}\t{rssi}".format(**info))
'2017-11-29 16:39:53,828	INFO    	30:07:4D:84:6B:7D	TIM Wi-Fi SIM	-49'
```

|  #  | Label | Type  | Description |
|:---:| ----- | :---: | ----------- |
| 0 | Timestamp  | `datetime` | Log timestamp |
| 1 | Level      | `string`   | Log level |
| 2 | MACAddress | `string`   | Standard MAC-48 addresses (IEEE 802) in six groups of two hexadecimal digits separated by colons |
| 3 | SSID       | `string`   | Service set identifier the human readable name used by a wireless network. |
| 4 | RSSI       | `int`      | Received signal strength indicator a measurement of the power present in a received radio signal. |

#### GPS

Data provided by the GPSd interface.

```python
>>> log.info("{lat}\t{lon}\t{alt}\t{speed}\t{satellites}\t{mode}\t{time}".format(**info))
'2017-11-29 20:41:01,376	INFO 	-23.617675333	-46.664935	53.0	0.077	11	3	2017-11-29T23:34:56.000Z'
```

|  #  | Label | Type  | Description |
|:---:| ----- | :---: | ----------- |
| 0 | Timestamp  | `datetime` | Log timestamp |
| 1 | Level      | `string`   | Log level |
| 2 | Lat        | `double`   | Latitude in degrees: +/- signifies North/South |
| 3 | Long       | `double`   | Longitude in degrees: +/- signifies East/West |
| 4 | Altitude   | `double`   | Orthometric height in meters |
| 5 | Speed      | `double`   | Speed over ground, meters per second |
| 6 | Satellites | `int`      | Number of satellites available at the time |
| 7 | Mode       | `int`      | The NMEA mode (1 = GPS fix, 2 = DGPS fix or 3 = PPS fix) |
| 8 | Time       | `datetime` | Time/date stamp in ISO8601 format, UTC |

#### Survey

Data from the visual survey used for validation.

```
0,6500-10,2017-12-13T12:53:26.868Z,1,2017-12-13T14:56:19.938Z
```

|  #  | Label | Type  | Description |
|:---:| ----- | :---: | ----------- |
| 0 | ID          | `int`      | Unique ID of a ride |
| 1 | BusNumber   | `string`   | Bus line number |
| 2 | Date        | `date`     | Date of the ride |
| 3 | Occupation  | `integer`  | Relative occupation of the vehicle (1 to 8) |
| 4 | Timestamp   | `datetime` | Time/date stamp in ISO8601 format, UTC |
