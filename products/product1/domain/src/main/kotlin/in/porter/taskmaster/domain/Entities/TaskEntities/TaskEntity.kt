package `in`.porter.taskmaster.domain.Entities


import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.timestamp

object TaskEntity {


    object tasks : Table("tasks") {
        var uuid = integer("uuid")
        val uid = integer("userid").references(UserEntity.users.uid, onDelete = ReferenceOption.CASCADE)
        val title = varchar("title", length = 200)
        val description = text("description")
        val isdone = bool("isdone").default(false)
        val deadline = timestamp("deadline")
        override val primaryKey = PrimaryKey(uuid)
    }
}








