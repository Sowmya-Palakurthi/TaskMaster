









plugins {
  id(Plugins.mavenPublish)
}

dependencies {
  implementation(Libs.KotlinUtils.serdeJackson)
}

val sourceJar = task("sourceJar", Jar::class) {
  dependsOn(JavaPlugin.CLASSES_TASK_NAME)
  archiveClassifier.set("sources")
  from(project.the<SourceSetContainer>()["main"].allSource)
}

publishing {
  publications {
    create<MavenPublication>(name) {
      from(components["java"])
      artifact(sourceJar)
    }
  }

  repositories {
    maven {
      url = uri("s3://porter-maven/releases")
      authentication {
        create<AwsImAuthentication>("awsIm")
      }
    }
  }
}
//
//plugins {
//  id(Plugins.mavenPublish)
//
//
//}
//
//dependencies {
//  implementation(Libs.KotlinUtils.serdeJackson)
//  implementation("redis.clients:jedis:5.1.0")
//}
//
//val sourceJar = task("sourceJar", Jar::class) {
//  dependsOn(JavaPlugin.CLASSES_TASK_NAME)
//  archiveClassifier.set("sources")
//  from(project.the<SourceSetContainer>()["main"].allSource)
//}
//
//publishing {
//  publications {
//    create<MavenPublication>(name) {
//      from(components["java"])
//      artifact(sourceJar)
//    }
//  }
//
//  repositories {
//    maven {
//      url = uri("s3://porter-maven/releases")
//      authentication {
//        create<AwsImAuthentication>("awsIm")
//      }
//    }
//  }
//}
