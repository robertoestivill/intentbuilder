YTS Api Client
=========

Android Intent wrapper with fluid API and argument validations.

![Travis build status](https://travis-ci.org/robertoestivill/intentbuilder.svg "Travis build status")
![Maven central](https://maven-badges.herokuapp.com/maven-central/com.robertoestivill.intentbuilder/intentbuilder/badge.svg "Maven central")



---
Get the code
-- 

```sh
git clone https://github.com/robertoestivill/intentbuilder.git
```

Maven
--

```xml
<dependency>
  <groupId>com.robertoestivill.intentbuilder</groupId>
  <artifactId>intentbuilder</artifactId>
  <version>0.1.0</version>
</dependency>
```

Gradle
--

```groovy
dependencies {
    compile 'com.robertoestivill.intentbuilder:intentbuilder:0.1.0'
}
```


---
Usage
--
The main class of the library is `intentbuilder.IntentBuilder`.

```java
Intent intent = new IntentBuilder()
            .context(this)
            .activity(MyActivity.class)
            .extra("my_key", "my_value")
            .build();
```

---
License
----

    Copyright 2015 Roberto Estivill

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
