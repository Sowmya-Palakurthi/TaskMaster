package `in`.porter.taskmaster.servers.ktor




interface ConfigActions {
    fun getDbUrl(): String
    fun getDbDriver(): String
    fun getDbUsername(): String
    fun getDbPassword(): String
    fun getRedisHost(): String
    fun getRedisPort(): Int
    fun getServerHost(): String
    fun getServerPort(): Int
    fun getJwtAudience(): String
    fun getJwtDomain(): String
    fun getJwtRealm(): String
    fun getJwtSecret(): String
}
