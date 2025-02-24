package `in`.porter.taskmaster.domain.Entities

import org.jetbrains.exposed.sql.Table

object UserEntity {
    object users : Table("users") {
        val uid = integer("uid") // Manually entered
        val username = varchar("username", length = 100)
        val password = varchar("password", length = 255)

        override val primaryKey = PrimaryKey(uid) // Set userid as primary key
    }
}
