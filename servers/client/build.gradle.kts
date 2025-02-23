plugins {
  id(Plugins.kotlinKapt)
  id(Plugins.mavenPublish)
  id(Plugins.mavenS3) version Plugins.mavenS3Version
  id(Plugins.sonaTypePlugin) version Plugins.sonaTypePluginVersion
}
//newly added
java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}


dependencies {
  api(project(Modules.TASKMASTER.Api.models))
  implementation(Libs.KotlinUtils.serdeJackson)
  implementation(Libs.KotlinUtils.awsSqs)
  implementation("redis.clients:jedis:5.0.0")
  implementation(Libs.Dagger.dagger)
  kapt(Libs.Dagger.compiler)

}
val sourceJar = task("sourceJar", Jar::class) {
  dependsOn(JavaPlugin.CLASSES_TASK_NAME)
  archiveClassifier.set("sources")
  from(project.the<SourceSetContainer>()["main"].allSource)
}


publishing {
  publications {
    create<MavenPublication>(name) {
      artifactId = "taskmaster-client"

      from(components["java"])
      artifact(sourceJar)
    }
  }

  repositories {
    maven {
      url = uri("s3://porter-maven/releases")
      credentials(AwsCredentials::class, mavenS3.credentials())
    }
  }
}
