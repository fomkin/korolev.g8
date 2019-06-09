import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, Materializer}
import korolev._
import korolev.akkahttp._
import korolev.execution._
import korolev.server._
import korolev.state.javaSerialization._

import scala.concurrent.Future

object $name$ extends App {

  private implicit val actorSystem: ActorSystem = ActorSystem()
  private implicit val materializer: Materializer = ActorMaterializer()

  val applicationContext = Context[Future, MyState, Any]

  import MyState.globalContext._
  import symbolDsl._

  private val config = KorolevServiceConfig[Future, MyState, Any](
    stateStorage = StateStorage.default(MyState()),
    router = Router.empty,
    render = { case _ => 'body("Hello world") }
  )

  private val route = akkaHttpService(config).apply(AkkaHttpServerConfig())

  Http().bindAndHandle(route, "0.0.0.0", 8080)
}
