dependencies {
  implementation(project(Modules.TASKMASTER.domain))
  implementation(project(Modules.TASKMASTER.Api.models))
  implementation("redis.clients:jedis:5.1.0")

}



// dataclasses in api layer
// service layer mapper implementation
//service layer entities
