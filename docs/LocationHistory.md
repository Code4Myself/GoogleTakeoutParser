# ロケーション履歴 / Location history 
- Google takeout/location history 
- JSONファイルのデータ構造説明
- JSONデータのキーと値からの推測ですので誤っている可能性があります。利用される場合には御留意願います

## 1.概要（推測）
- スマートフォン（Android/iOS）の位置情報履歴（GoogleMapsのタイムライン）をONにすることで記録される
- 記録された位置情報だけではなく、交通手段や立ち寄り先などタイムラインで利用される分析結果も取得可能
- JSON（GeoJsonではない）またはKML形式でダウンロードが可能（以下の説明はJSON）

## 2.フォルダ階層
- **Takeout**
  - *ロケーション履歴.json* : 記録された位置情報の履歴一式。過去のデータが１ファイルにまとまるため、期間が長いと数百MBになる
  - *Semantic Location Hisotry* : タイムラインで表示される位置情報から推測された個人の活動情報。月別にJSONファイルが作成され、年毎のフォルダに記録される

## 3. ロケーション履歴.json

## 3.1. データ概要
|key1|key2|key3|key4|説明|
|:---:|:---:|:---:|:---:|:---|
|locations(array)||||ルートノード。以下は観測点オブジェクトの配列|
||timestampMs(sring)|||観測日時。unixtime ミリ秒|
||latitudeE7(long)|||緯度。10^7 倍|
||longitudeE7(long)|||経度。10^7倍|
||accuracy(integer)|||位置精度。Android SDKなどで得られる水平誤差に相当？|
||velocity(integer)|||速度(m/s) or (km/h)。オプションらしい|
||heading(inteer)|||進行方向。おそらく北を0とした角度（0-360度）。オプションらしい|
||altitude(integer)|||高度(おそらくm).オプションらしい|
||verticalAccuracy(integer)|||高さ方向の計測精度？単位不明。オプションらしい|
||activity(array)|||活動（交通手段等）の推定結果。オプションらしい|
|||timestampMs(string)||何かの日時（観測日時とは違う）。unixime ミリ秒|
|||activity(array)||推定内容|
||||type(string)|推定結果。分類多数（onFoot, walking, running 等）|
||||confidence(integer)|推定の確信度？　おそらく値が大きいほど確信度が高い|

 [Android SDK](https://developer.android.com/reference/android/location/Location.html#getAccuracy())
 
## 3.2. JSONサンプル
```json
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

## 4. Semantic Location History

## 4.1. データ概要
|key1|key2|key3|key4|key5|説明|
|:---:|:---:|:---:|:---:|:---:|:---|
|timelineObjects(array)||||ルートノード。以下はTimelineオブジェクトの配列|
||activitySegment(object)||||移動区間を表してそう|
|||startLocation(object)|||出発点|
||||latitudeE7(long)||緯度。10^7 倍|
||||longitudeE7(long)||経度。10^7 倍|
|||endLocation(object)||||
||||latitudeE7(long)|||
||||longitudeE7(long)|||
|||duration(object)|||時間範囲|
||||startTimestampMs(string)||開始日時。unixtime ミリ秒|
||||endTimestampMs(string)||終了日時。unixtime ミリ秒|
|||distance(integer?)|||移動距離(おそらくm)|
|||activityType(string)|||移動手段の推定結果。以下のactivitiesで確率の最も高いもの？|
|||confidence(string)|||推定結果の確信度。数値ではなくHIGH, MEDIUMなどの文字列表記|
|||activities(array)|||推測結果の一覧|
||||activityType(string)||移動手段の推定結果の候補|
||||probability(double)||移動手段の確率|
|||waypointPath(object)|||途中点経由点？|
||||waypoints(array)|||
|||||latE7(long)|緯度。10^7 倍|
|||||lngE7(long)|経度。10^7 倍|
|||simplifiedRawPath(object)|||？？？|
||||points(array)|||
|||||latE7(long)|緯度。10^7 倍|
|||||lngE7(long)|経度。10^7 倍|
|||||timesatmpMs(string)|何かの日時。unixtime ミリ秒|
|||||accuracyMeters(integer)|何かの誤差(メートル)|
|||transitPath(object)|||乗換地点？|
||||transitStops(array)|||
|||||latitudeE7(long)|緯度。10^7 倍|
|||||longitudeE7(long)|経度。10^7 倍|
|||||placeId(string)|位置識別子|
|||||name(string)|場所の名称|
||||name(string)||？？|
||||hexRgbColor(string)||色情報|
||placeVisit(object)||||立ち寄り先情報を表してそう|
|||location(object)|||場所の情報|
||||latitudeE7(long)||緯度。10^7 倍|
||||longitudeE7(long)||経度。10^7 倍|
||||placeId(string)||位置識別子|
||||address(string)||住所|
||||name(string)||地名|
||||sourceInfo(object)||？？？|
|||||deviceTag(integer)|？？？|
||||locationConfidence(double)||おそらく立ち寄り先推測の確信度|
|||duration(object)|||時間範囲|
||||startTimestampMs(string)||開始日時。unixtime ミリ秒|
||||endTimestampMs(string)||終了日時。unixtime ミリ秒|
|||placeConfidence(string)|||位置情報推定の確信度？。HIGH_CONFIDENCEなどの文字列表現|
|||childVisits(array)|||この場所（ビルなど？）の中の立ち寄り先。placeVisitオブジェクトの配列|
|||centerLatE7(long)|||代表点の緯度。10^7 倍|
|||centerLngE7(long)|||代表点の経度。10^7 倍|
|||visitConfidence(integer)|||おそらく立ち寄りの確信度|
|||otherCandidateLocations(array)|||立ち寄り先の候補値|
||||latitudeE7(long)||緯度。10^7 倍|
||||longitudeE7(long)||経度。10^7 倍|
||||placeId(string)||位置識別子|
||||locationConfidence(double)||おそらく立ち寄り先推測の確信度|
|||editConfirmationStatus(string)|||？？？|
|||simplifiedRawPath(object)|||？？？|
||||points(array)|||
|||||latE7(long)|緯度。10^7 倍|
|||||lngE7(long)|経度。10^7 倍|
|||||timesatmpMs(string)|何かの日時。unixtime ミリ秒|
|||||accuracyMeters(integer)|何かの誤差(メートル)||||||||

## 4.2. JSONサンプル
```json
{

}
```
