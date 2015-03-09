package fr.unice.polytech.ogl.championships.library

import java.io._

import eu.ace_design.island.bot.IExplorerRaid
import eu.ace_design.island.game.{Engine, ExplorationEvent, Game, GameBoard}
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.map.resources.Resource
import eu.ace_design.island.viewer.svg.{FogOfWar, FogOfWarViewer}

import scala.util.Random


trait Championship extends App  {

  val outputDir: String
  val seed: Long

  def printInfo(isl: IslandMap, board: GameBoard) {
    println("\n## Island global statistics")
    isl.stats match {
      case None =>
      case Some(d) => d.toSeq sortBy { _._1.toString  } foreach { case (stat, value) => println(s"  - $stat => $value") }
    }
    println("\n## Resources amounts")
    board.contents foreach { case (res, amount) => println(f"  - ${res}%-10s => $amount") }
    println("\n## Point of Interests available")
    board.pois foreach { case (loc, pois) => println(s"  $loc: $pois") }
  }

  type ChampResult = Iterable[Either[Result, (String, String)] with Product with Serializable]

  protected def run(players: Map[String, IExplorerRaid], g: Game, b:GameBoard, isl: IslandMap): ChampResult  = {
    players map { case (name, bot) =>
      try {
        Left(handlePlayer(name, bot, g, b, isl))
      } catch {
        case e: Error => Right((name, "Error : " + e.getClass.getCanonicalName))
        case e: Exception => Right((name, "Exception : " + e.getClass.getCanonicalName))
      }
    }
  }

  protected def handlePlayer(name: String, bot: IExplorerRaid, init: Game, board: GameBoard, m: IslandMap): Result = {
    val engine = new Engine(board, init, new Random(seed))
    val (events, game) = silent(engine,bot)
    val result = game.isOK match {
      case false => KO(name)
      case true => {
        val remaining = game.budget.remaining
        val men = game.crew.used
        val resources = game.extracted map { case (resource, data) => resource -> data.values.sum }
        OK(name, remaining, men, resources.toSet)
      }
    }
    exportLog(name, events)
    exportVisitedMap(name, m, game, board.tileUnit)
    result
  }

  def silent(engine: Engine, bot: IExplorerRaid): (Seq[ExplorationEvent], Game) = {
    try {
      System.setOut(new PrintStream(new ByteArrayOutputStream()))
      System.setErr(new PrintStream(new ByteArrayOutputStream()))
      engine.run(bot)
    } finally {
      System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)))
      System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
    }
  }

  def exportLog(name: String, events: Seq[ExplorationEvent]): Unit = {
    val jsonEvents = events map { _.toJson } mkString("[", ",", "]")
    val writer = new PrintWriter(new File(s"$outputDir/$name.json"))
    try { writer.write(jsonEvents) } finally { writer.close() }
  }

  def exportVisitedMap(name: String, m: IslandMap, game: Game, tileUnit: Int): Boolean = {
    val fog = new FogOfWar(factor = tileUnit, visited = game.visited, pois = Set(), size = m.size)
    val viewer = FogOfWarViewer(fog)
    viewer(m).renameTo(new File(s"$outputDir/$name.svg"))
  }

  def printResults(results: ChampResult): Unit = {
    results foreach {
      _ match {
        case Left(r) => {
          println(s"## Playing bot delivered by ${r.name}")
          r match {
            case KO(name) => println("  ==>> Game knocked out")
            case OK(name, remaining, men, resources) => {
              println("  ==>> Game properly ended")
              println(s"  - Remaining budget: $remaining")
              println(s"  - Used men: $men")
              println(s"  - Collected resources:")
              if (resources.isEmpty)
                println("    - No resources collected")
              else
                resources foreach { r => println(s"    - ${r._1}: ${r._2}")}
            }
          }
          println()
        }
        case Right((name, exc)) => {
          println(s"## Playing bot delivered by $name")
          println(s"  ==>> Game knocked out with error [$exc]")
          println()
        }
      }
    }
  }


}

trait Result { val name: String }
case class OK(override val name: String, remaining: Int, men: Int, resources: Set[(Resource, Int)]) extends Result
case class KO(override val name: String) extends Result
