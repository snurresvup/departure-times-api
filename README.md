# Departure times API
This repository provides a small middleware API for the [Transport for London Unified API](https://api.tfl.gov.uk/) API.
The API is built with [Dropwizard.io](http://www.dropwizard.io/).

The API is currently running at: http://178.62.31.37:8080

## Documentation
All of the available API endpoints only respond to *GET* requests.

### Arrival predictions
Responds with a list of arrival predictions.
- URL: /arrival-predictions
- Query parameters:
  - *id* – The station id to fetch arrival predictions for.
  - *station* - The station name to fetch arrival predictions for. (If the *id* parameter is provided the *station* parameter will be ignored.)
- Example:
```shell
$ curl -k https://178.62.31.37:8080/arrival-predictions\?id\=490005183E
```
- Response sample:
```json
[
  {
    "id": "string",
    "operationType": 0,
    "vehicleId": "string",
    "naptanId": "string",
    "stationName": "string",
    "lineId": "string",
    "lineName": "string",
    "platformName": "string",
    "direction": "string",
    "bearing": "string",
    "destinationNaptanId": "string",
    "destinationName": "string",
    "timestamp": "2017-08-20T10:23:37.455Z",
    "timeToStation": 0,
    "currentLocation": "string",
    "towards": "string",
    "expectedArrival": "2017-08-20T10:23:37.455Z",
    "timeToLive": "2017-08-20T10:23:37.455Z",
    "modeName": "string",
    "timing": {
      "countdownServerAdjustment": "string",
      "source": "2017-08-20T10:23:37.455Z",
      "insert": "2017-08-20T10:23:37.455Z",
      "read": "2017-08-20T10:23:37.455Z",
      "sent": "2017-08-20T10:23:37.455Z",
      "received": "2017-08-20T10:23:37.455Z"
    }
  }
]
```

### Clostst station
Responds with the closest station to the provided position.
- URL: /closest-station
- Query parameters:
  - *lat* - Latitude
  - *lng* - Longitude
- Example
```shell
$ curl -k https://178.62.31.37:8080/closest-station\?lat\=51.517351\&lng\=-0.127758
```
- Response sample:
```json
{
  "name":"Tottenham Ct Rd Stn  / New Oxford St",
  "loc":{
    "type":"Point",
    "coordinates":[-0.128321,51.516761]
  }
  ,"description":"N/A",
  "id":"490G000030",
  "latitude":51.516761,
  "longitude":-0.128321
}
```

### Stations
Responds with a list of stations within the defined circle, or all stations if no circle is defined. The circle is defined by the center point (*lat*, *lng*) and the *radius*.
- URL: /stations
- Query parameters:
  - *lat* - Latitude
  - *lng* - Longitude
  - *radius* - Maximum distance to (*lat*, *lng*)
- Example
```shell
$ curl -k https://178.62.31.37:8080/stations\?lat\=51.517351\&lng\=-0.127758\&radius\=80
```
- Response sample:
```json
[
  {
    "name":"Tottenham Ct Rd Stn  / New Oxford St",
    "loc":{
      "type":"Point",
      "coordinates":[-0.128321,51.516761]
    },
    "description":"N/A","id":"490G000030",
    "latitude":51.516761,
    "longitude":-0.128321
  },
  {
    "name":"Tottenham Ct Rd Stn  / New Oxford St",
    "loc":{
      "type":"Point",
      "coordinates":[-0.128321,51.516761]
    },
    "description":"N/A",
    "id":"490000235P",
    "latitude":51.516761,
    "longitude":-0.128321
  },
  {
    "name":"Tottenham Court Road Station",
    "loc":{
      "type":"Point",
      "coordinates":[-0.128122,51.516686]
    },
    "description":"N/A",
    "id":"490000235X",
    "latitude":51.516686,
    "longitude":-0.128122
  }
]
```

### Transportation modes
Responds with a list of available transportation modes.
- URL: /mode
- Query parameters: NONE
- Example:
```shell
$ curl -k https://178.62.31.37:8080/mode
```
- Response sample:
```json
[
  "bus",
  "cable-car",
  "coach",
  "cycle",
  "cycle-hire",
  "dlr","interchange-keep-sitting",
  "interchange-secure",
  "national-rail",
  "overground",
  "replacement-bus",
  "river-bus",
  "river-tour",
  "taxi",
  "tflrail",
  "tram",
  "tube",
  "walking"
]
```
