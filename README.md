# GoogleTakeoutParser
Java Parser Library for Google Takeout data

## 0. summary
- Google provides the *takeout* service to archive and download own data stored through Google services
- Since takeout data is segmented and is provided in each service respectively, data formats are different and their specifications/definitions are not provided officially. 
- This library provides simple java parser for some kinds of takeout data (e.g. Location, Query, Photo and fit)    

## 1. Location (JSON) 
- location logs with estimated activities
- Takeout provides location logs as JSON or KML format.  

```json: locatoin.json
{
  "locations": [
    {
      "timestampMs": "1486110600090",
      "latitudeE7": 356617253,
      "longitudeE7": 1396787215,
      "accuracy": 8,
    },
    {
      "timestampMs": "1486105895000",
      "latitudeE7": 356617327,
      "longitudeE7": 1396785506,
      "accuracy": 8,
      "velocity": 0,
      "heading": 145,
      "altitude": 98,
      "activity": [
        {
          "timestampMs": "1486105892533",
          "activity": [
            {
              "type": "still",
              "confidence": 75
            },
            {
              "type": "onFoot",
              "confidence": 10
            },
            {
              "type": "inVehicle",
              "confidence": 5
            },
            {
              "type": "onBicycle",
              "confidence": 5
            },
            {
              "type": "unknown",
              "confidence": 5
            },
            {
              "type": "walking",
              "confidence": 5
            },
            {
              "type": "running",
              "confidence": 5
            }
          ]
        }
      ]
    }
  ]
}
```
- *locations* : (array/object) location point array
- *timestampMs* : (string) timestamp in msec
- *latitudeE7* : (integer) latitude value in integer. latitudeE7 / 10,000,000 = latitude in degree
- *longitudeE7* : (integer) longitude value in integer. longitudeE7 / 10,000,000 = longitude in degree
- *accuracy* : (integer) accuracy value defined in Android SDK?  [Android SDK](https://developer.android.com/reference/android/location/Location.html#getAccuracy())
- *velocity* : (integer) velocity value (m/s or km/h?). sometimes missing
- *heading* : (integer) heading direction ? (0 - 360 deg?)
- *altitude* : (integer) altitude value (m?). sometimes missing
- *activity* : (array/object) estimated activity or transportation mode. (JSON key was updated from activitys to activity)
  - *timestampMs* : (string) timestamp in msec. 
  - *activity* : (array/object) activity contents (JSON key was updated from activities to activity)
    - *type* : (string) activity type. (e.g. still, onFoot, walking, running, inVehicle, onBicycle, unknown, ...) 
    - *confidence* : (integer) confidence value of estimation ?   

## 2. Query (JSON)
- query logs on Google search engine. 

```json:
{
  "event": [
    {
      "query": {
        "id": [
          {
            "timestamp_usec": "1485845767211578"
          }
        ],
        "query_text": "android NEXUS5X"
      }
    }
  ]
}
```
- *event* : (array/object) query event 
- *query* : (object) query contents
  - *id* : (array/object) query id ? 
    - *timestamp_usec* : (string) timestamp in nano second
  - *query_text* : (string) query text


## 3. photo (JSON)
- There are 2 types of JSON files
	- **metadata.json** contains album data
	- **photo_file_name.json** contains individual photo information associating to photo image file. 

### Metadata: data content description(temporary)  
```json: metadata
{
  "albumData": {
    "title": "2016/05/07",
    "date": "May 6, 2016 11:45:15 PM",
    "access": "protected",
    "description": "",
    "location": "",
    "geoInfo": null
  },
  "media": []
}
```
- *albumData* : (object) metadata of album data
  - *title* : (string) album title
  - *date* : (string) creation date of this album ("MMM dd, yyyy hh:mm:ss a")
  - *access* : (string) access limitation. (__protected__, ...)
  - *description* : (string) description about this album.
  - *location* : (string) location name ?
  - *geoInfo* : (object) geo information such as longitude and latitude. 
- *media* : (array) ??


### PHOTO data: data content description(temporary)
```json: photo_file_name.json
{
  "tags": [],
  "description": "",
  "title": "photo_file_name.jpg",
  "people": [],
  "creationTime": 1463144190,
  "modificationTime": 1481420964,
  "comments": [],
  "url": "https://xxxxx/photo_file_name.jpg",
  "geoInfo": {
    "latitude_": 36.225364444444445,
    "longitude_": 140.10670861111112,
    "altitude_": 889.0,
    "latitude_span_": 0.0,
    "longitude_span_": 0.0,
    "uninterpreted": null,
    "optional_0_": 7,
    "isMutable": true,
    "cachedSize": -1
  },
  "geoInfoExif": {
    "latitude_": 36.225364444444445,
    "longitude_": 140.10670861111112,
    "altitude_": 889.0,
    "latitude_span_": 0.0,
    "longitude_span_": 0.0,
    "uninterpreted": null,
    "optional_0_": 7,
    "isMutable": true,
    "cachedSize": -1
  },
  "imageViews": 0,
  "license": null,
  "locale": "ja"
}
```
- *tags* : (array/string) tags associated with this photo 
- *description* : (string) description about this photo  
- *title* : (string) photo title( file name ) 
- *people* : (array/??) people who shared with 
- *creationTime* : (long) creation time in sec 
- *modificationTime* : (long) creation time in sec
- *comments* : (array) comments to this photo
- *url* : (string) accessible URL of this photo
- *imageViews* : (integer) ?
- *license* : (object) license description ?
- *locale* : (string) locale. (e.g. jp)
- *geoInfo* : (object) Geo information associated with this photo
  - *latitude_* : (double) latitude value in degree
  - *longitude_* : (double) longitude value in degree
  - *altitude_* : (double) altitude value in meter (?)
  - *latitude_span_* : (double) latitude span (for google map api?)
  - *longitude_span_* : (double) longitude span (for google map api?)
  - *uninterpreted* : ?
  - *optional_0_* : ?
  - *isMutable* : (boolean) mutable flag? 
  - *cachedSize* : (int) ?
- *geoInfoExif* : (object) Geo information from EXIF. 
  - __object contents are same as geoInfo__



## 4. Fit (xml)
(TBA)