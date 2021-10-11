# BICustomConfig

[![Youtube](https://img.shields.io/badge/youtube-북잇-red?logo=youtube)](https://www.youtube.com/channel/UCllpUa_1bga-GTtgL3A-40Q)
### 스피곳 플러그인을 위한 커스텀 콘피그 플러그인입니다.
### Custom Config Plugin for Spigot Plugins



## Usages
* 임포트 ( import )
  ```java
  import com.bookit.BICustomConfig.BICustomConfig;
  ```


* 콘피그 로드 ( loadConfig )
    * 콘피그를 로드합니다. 만약 파일이 존재하지 않는다면 새로 생성합니다.
    * ( Load config file. if not exists, create new one. )
  ```java
  BICustomConfig.loadConfig(JavaPlugin plugin, String FileName);
  ```


* 기본 콘피그 복사 ( CopyDefaultConfig )
    * 콘피그를 resources 폴더에서 복사해옵니다. 만약 파일이 존재한다면, 새로 복사하지는 않습니다.
    * ( Copy config from resources folder. If the file exists, not be newly copied. )
  ```java
  BICustomConfig.copyDefaultConfig(JavaPlugin plugin, String fileName);
  ```

* 콘피그 로드 확인 ( isLoaded )
    * 콘피그가 로드되었는지 확인합니다. 로드되었을 경우 true를 반환합니다.
    * ( Return whether config already loaded. )
  ```java
  BICustomConfig.isLoaded(JavaPlugin plugin, String fileName);
  ```

* 콘피그 리로드 ( reloadConfig )
    * 콘피그를 다시 로드합니다.
    * ( Reload config. )
  ```java
  BICustomConfig.reloadConfig(JavaPlugin plugin, String fileName);
  ```

* 콘피그 저장 ( saveConfig )
    * 콘피그 변경 사항을 파일에 저장합니다.
    * ( Save changes to file. )
  ```java
  BICustomConfig.saveConfig(JavaPlugin plugin, String fileName);
  ```

* 콘피그 가져오기 ( getConfig )
    * 콘피그를 가져옵니다.
    * ( Get config. )
  ```java
  BICustomConfig.saveConfig(JavaPlugin plugin, String fileName);
  ```