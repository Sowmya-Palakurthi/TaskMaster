dependencies {
  implementation(project(Modules.TASKMASTER.domain))
  implementation(project(Modules.TASKMASTER.Api.models))
  implementation("redis.clients:jedis:5.1.0")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
}



// dataclasses in api layer
// service layer mapper implementation
//service layer entities
