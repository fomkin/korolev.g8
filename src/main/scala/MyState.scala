import korolev.ApplicationContext
import korolev.execution._
import scala.concurrent.Future

case class MyState()

object MyState {
  val globalContext = Context[Future, MyState, Any]
}
