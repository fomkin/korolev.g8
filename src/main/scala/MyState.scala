import korolev.ApplicationContext
import korolev.execution._
import scala.concurrent.Future

case class MyState()

object MyState {
  val applicationContext = ApplicationContext[Future, MyState, Any]
}
