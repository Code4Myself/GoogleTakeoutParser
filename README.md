# GoogleTakeoutParser
Parser Library for Google Takeout data

## Summary
- Googleサービスの利用に伴って生成されたパーソナルデータを *takeout* 機能でダウンロードできます
- takeoutのデータはサービス毎に個別に分割されているため、フォーマットも個々に異なっています
- 一方でtakeoutデータの仕様や値の定義などは提供されていません。
- このリポジトリでは、データ仕様の整理とデータ変換用の実装を有志により整備しオープンソースで提供しています
---
- Google provides the *takeout* service to archive and download own data stored through Google services
- Since takeout data is segmented and is provided in each service respectively, data formats are different
- Data specifications/definitions are not provided officially. so this package may not support all data contents  
- This library provides estimated data specification and parsers as open source by some volunteers

## License
- This package is licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)