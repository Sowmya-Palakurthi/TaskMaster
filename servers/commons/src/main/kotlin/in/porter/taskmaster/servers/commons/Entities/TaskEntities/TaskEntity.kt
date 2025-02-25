import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.timestampWithTimeZone

object TasksTable : Table("tasks") {
    val id = integer("id").autoIncrement()
    val uid = integer("uid")
    val title = varchar("title", 255)
    val description = text("description")
    val isDone = bool("is_done")
    val deadline = timestampWithTimeZone("deadline")

    override val primaryKey = PrimaryKey(id)
}
