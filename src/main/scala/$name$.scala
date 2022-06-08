import korolev.*
import korolev.server.*
import korolev.state.javaSerialization.*
import korolev.effect.*

import levsha.dsl.*
import levsha.dsl.html.*

import scala.concurrent.Future

case class State(
    title: String = "Hello Korolev!",
    items: Vector[String] = Vector("one", "two", "three")
)

object $name$ extends KorolevApp[Future, Array[Byte], State, Any] {

  import context.*

  private val newItemInput = elementId(Some("newItemInput"))

  private def onSubmit(access: Access): Future[Unit] =
    for {
      newItem <- access.valueOf(newItemInput)
      _ <- access.transition(state =>
        state.copy(items = state.items :+ newItem)
      )
    } yield ()

  private def render(state: State): Node = optimize {
    Html(
      body(
        div(clazz := "title", state.title),
        ul(clazz := "list", state.items.map(item => li(clazz := "item", item))),
        form(
          input(newItemInput),
          button("Add"),
          event("submit")(onSubmit)
        )
      )
    )
  }

  val config = Future.successful {
    KorolevServiceConfig(
      stateLoader = StateLoader.default(State()),
      document = render
    )
  }
}

