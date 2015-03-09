package fr.unice.polytech.ogl.championships

import java.io._

import eu.ace_design.island.game._
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.map.processes.AssignPitch
import eu.ace_design.island.stdlib.POIGenerators.WithCreeks
import eu.ace_design.island.stdlib.Resources.WOOD
import eu.ace_design.island.stdlib.WhittakerDiagrams
import eu.ace_design.island.viewer.svg.{FogOfWarViewer, FogOfWar}
import org.json.JSONArray

object Week10 extends Championship {

  // Creating the island used for this week and storing it as a PDF file
  val seed = 0x762FFC83BEF34278L
  override val theIsland: IslandMap = {
    createIsland shapedAs donut(80.percent, 20.percent) withSize 1000 having 1200.faces usingSeed seed builtWith Seq(
      plateau(23), flowing(rivers = 15, distance = 0.2), withMoisture(soils.wet, distance = 400),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)
    )
  }
  theIsland -> ("./output/week10/map-week10" as pdf)

  // building a gameBoard and displaying statistics
  val builder = new GameBoardBuilder(rand = theIsland.random, poiGenerators = Seq(new WithCreeks(10)))
  override val theBoard = builder(theIsland)

  // displaying info about the game:
  printInfo()

  // Building the game engine
  val objective = (WOOD, 600)
  val initialization = Game(budget = Budget(600), crew = Crew(50), objectives = Set(objective) )
  val engine = new Engine(theBoard, initialization, theIsland.random)

  players foreach { case(name, bot) =>
      println(s"\n## Playing bot from team [$name]")
      try {
        System.setOut(new PrintStream(new ByteArrayOutputStream()))
        System.setErr(new PrintStream(new ByteArrayOutputStream()))
        val (events, game) = engine.run(bot)
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)))
        game.isOK match {
          case false => println("  ==>> Game knocked out :(")
          case true => {
            println("  ==>> Game properly ended")
            println(s"\n  Remaining APs: ${game.budget.remaining}")
            println(s"  Used men: ${game.crew.used}")
            game.extracted foreach { case (resource, data) =>
              val amount = data.values.sum
              println(s"  $resource -> $amount")
            }
          }
        }
        val jsonEvents = events map { _.toJson } mkString("[",",","]")
        val writer = new PrintWriter(new File(s"./output/week10/$name.json"))
        writer.write(jsonEvents)
        writer.close()
        val fog = new FogOfWar(factor = 10, visited = game.visited, pois = Set(), size = theIsland.size)
        val viewer = FogOfWarViewer(fog)
        viewer(theIsland).renameTo(new java.io.File(s"./output/week10/$name.svg"))

      } catch {
        case e: Error =>  println("Error : " + e.getClass.getCanonicalName); e.printStackTrace()
        case e: Exception => println("Exception : " + e.getClass.getCanonicalName)//; e.printStackTrace()
      } finally {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)))
      }
  }

}
