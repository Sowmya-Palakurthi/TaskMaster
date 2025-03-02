package `in`.porter.taskmaster.domain.Entities.TaskEntities
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date

object TaskEntity {
    object tasks : Table("tasks") {
        val uuid = integer("uuid")
        val uid = integer("uid")
        val title = varchar("title", 255)
        val description = text("description")
        val isdone = bool("is_done")
        val deadline = date("deadline")

        override val primaryKey = PrimaryKey(uuid)
    }
}
