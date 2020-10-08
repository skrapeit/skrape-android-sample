
# [skrape{it}](https://docs.skrape.it)
====================================

_**[skrape{it}](http://www.skrape.it)**_ is a Kotlin-based HTML/XML testing and web scraping library
that can be used seamlessly in Spring-Boot, Ktor, Android or other Kotlin-JVM projects.

This project is an Android sample of use from the library.

## Configuring the environment

The fist step to be able to run, is to setup the JVM to target at least JAVA 8.
To do that, on your app level build.gradle, add these configurations:

```gradle
android {
    // Other android setups

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
```

After that, you need to exclude the `META-INF/DEPENDENCIES` from the android packaging.

```gradle
android {
    // Other android setups

    // JVM setup

    packagingOptions {
        exclude 'META-INF/DEPENDENCIES'
    }
}
```

Last but not least, import the skrape library.

```gradle
dependencies {
    implementation "it.skrape:skrapeit-core:$skrape_version"
}
```

After all of that, you can just use the library as usual:

```kotlin
    fun parse(html: String) {
        val parsedHtml = htmlDocument(html)

        _titleLiveData.postValue(parsedHtml.titleText)
        parsedHtml.findAll("li")
            .map { it.text }
            .let(_itemsLiveData::postValue)
    }
```
