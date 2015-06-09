Intent Builder
=========

Android Intent wrapper with fluid API and argument validations.

[![Travis build status](https://travis-ci.org/robertoestivill/intentbuilder.svg "Travis build status")](https://travis-ci.org/robertoestivill/intentbuilder)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/com.robertoestivill.intentbuilder/intentbuilder/badge.svg "Maven central")](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.robertoestivill.intentbuilder%22)
[![Coverage Status](https://coveralls.io/repos/robertoestivill/intentbuilder/badge.svg)](https://coveralls.io/r/robertoestivill/intentbuilder)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Intent%20Builder-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1732)

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
  <version>0.1.1</version>
</dependency>
```

Gradle
--

```groovy
dependencies {
    compile 'com.robertoestivill.intentbuilder:intentbuilder:0.1.1'
}
```


---
Usage
--
The main class of the library is `intentbuilder.IntentBuilder`.

```java

// Explicit intent
Intent intent = new IntentBuilder()
            .context(this)
            .activity(MyActivity.class)
            .extra("my_key", "my_value")
            .build();
            
// Implicit intent
Intent intent = new IntentBuilder()
            .action("my.app.MY_GREAT_ACTION")
            .extra("bool_key", true)
            .extra("int_key", 123)
            .extra("char_key", 'A')
            .flags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .build();
```

---
License
----

```
The MIT License (MIT)

Copyright (c) 2015 Roberto Estivill

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
