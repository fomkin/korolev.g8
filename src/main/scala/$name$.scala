import korolev.state.StateStorage
import korolev.server._
import korolev.blazeServer._
import korolev.execution._
import korolev.state.javaSerialization._

import scala.concurrent.Future

object SimpleExample extends KorolevBlazeServer {

  import MyState.globalContext._
  import MyState.globalContext.symbolDsl._

  val service = blazeService[Future, MyState, Any] from KorolevServiceConfig[Future, MyState, Any] (
    stateStorage = StateStorage.default(MyState()),
    render = { case state => 'body("Hello world!") },
    serverRouter = ServerRouter.empty[Future, MyState]
  )
}


