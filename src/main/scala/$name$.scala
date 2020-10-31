import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream._
import akka.stream.scaladsl._
import korolev._
import korolev.akka._
import korolev.server._
import korolev.state.javaSerialization._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

object $name$ extends App {

  implicit val ec = ExecutionContext.global

  private implicit val actorSystem: ActorSystem = ActorSystem()

  val applicationContext = Context[Future, MyState, Any]

  import MyState.globalContext._
  import levsha.dsl._
  import html._

  private val config = KorolevServiceConfig[Future, MyState, Any](
    stateLoader = StateLoader.default(MyState()),
    render = myState => optimize {
      body("Hello world")
    }
  )

  private val route = akkaHttpService(config).apply(AkkaHttpServerConfig())

  Http().bindAndHandle(route, "0.0.0.0", 8080)
}
