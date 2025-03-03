package `in`.porter.taskmaster.domain.Entities.TaskEntities
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserEntity
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date

object TaskEntity {
    object tasks : Table("tasks") {
        val uuid = integer("uuid")
        val uid = integer("uid").references(UserEntity.users.uid, onDelete = ReferenceOption.CASCADE)
        val title = varchar("title", 255)
        val description = text("description")
        val isdone = bool("is_done")
        val deadline = date("deadline")

        override val primaryKey = PrimaryKey(uuid)
    }
}
