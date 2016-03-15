# gradle-fdroid-plugin
Gradle Plugin for releasing Android apps to fdroid

## Configure
In `local.properties` add a line
```
fdroid.dir=/opt/fdroidserver // adjust to your environment
```

## Use
Apply the F-Droid plugin
```
plugins {
  id "org.openintents.fdroid" version "0.2"
}
``` 

### Tasks
Import meta data from a vcs like github
```
./gradlew fdroidImport
```

Rewrite your meta data
```
./gradlew fdroidRewriteMeta
```

Check for fdroid meta data lint issues
```
./gradlew fdroidLint
```

Build your application with fdroid
```
./gradlew fdroidBuild
```

