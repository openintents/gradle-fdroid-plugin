# gradle-fdroid-plugin
Gradle Plugin for releasing Android apps to fdroid

## Configure
In `local.properties` add a line (similar to `sdk.dir`)
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
Import meta data from the version control system (like gitlab, github) that is currently used for the project.
This creates a file with the meta data of the app. You might want to edit this file and add more details.
```
./gradlew fdroidImport
```

Rewrite your meta data to make sure they can be read by machines.
```
./gradlew fdroidRewriteMeta
```

Check for fdroid meta data lint issues before building your app.
```
./gradlew fdroidLint
```

Build your application with fdroid
```
./gradlew fdroidBuild
```

Then submit your meta data to [fdroid.org](https://f-droid.org/contribute/)
