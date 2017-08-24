import korolev.server._
import korolev.blazeServer._
import korolev.execution._

import scala.concurrent.Future

object $name$ extends KorolevBlazeServer {

  import MyState.globalContext._
  import MyState.globalContext.symbolDsl._

  val service = blazeService[Future, MyState, Any] from KorolevServiceConfig[Future, MyState, Any] (
    stateStorage = StateStorage.default(MyState()),
    render = { case state => 'div("Hello world!") },
    serverRouter = ServerRouter.empty[Future, MyState]
  )
}


