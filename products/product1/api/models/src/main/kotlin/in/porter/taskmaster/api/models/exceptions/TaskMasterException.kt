package `in`.porter.taskmaster.api.models.exceptions

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("stack_trace", "cause", "suppressed", "localized_message")
open class TaskMasterException(override val message: String) : Exception(message)
