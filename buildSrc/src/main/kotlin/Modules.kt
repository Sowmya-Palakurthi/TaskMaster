object Modules {

  object TASKMASTER {


    const val domain = ":domain"
    const val client = ":client"
    object Data {
      private const val data = ":data"
      const val psql = "$data-psql"
    }

    object Api {
      private const val api = ":api"

      const val models = "$api-models"
      const val service = "$api-service"
    }
  }


  object Servers {
    const val commons = ":server-commons"
    const val client = ":client"
//   const val ktor= ":ktor-server"
  }


}
