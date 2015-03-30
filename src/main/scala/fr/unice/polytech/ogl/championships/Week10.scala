package fr.unice.polytech.ogl.championships


import eu.ace_design.island.dsl.DiSLand
import eu.ace_design.island.game._
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.stdlib.POIGenerators.WithCreeks
import eu.ace_design.island.stdlib.Resources.WOOD
import fr.unice.polytech.ogl.championships.library.{Championship, SI3}

object Week10 extends Championship with SI3 with DiSLand {

  override val seed = Islands.seed10
  override val outputDir =  "./output/week10"

  // Retrieving the island used for this week and storing it as a PDF file
  val theIsland: IslandMap = Islands.week10
  theIsland -> (s"$outputDir/map-week10" as pdf)
  theIsland -> (s"$outputDir/map-week10" as obj)
  theIsland -> (s"$outputDir/map-week10" as json)

  // building a gameBoard with 10 creeks and displaying statistics
  val builder = new GameBoardBuilder(rand = theIsland.random, poiGenerators = Seq(new WithCreeks(10)))
  val theBoard = builder(theIsland)
  printInfo(theIsland, theBoard)

  // Building the game engine and the associated objectives
  val objective = (WOOD, 600)
  val initialization = Game(budget = Budget(600), crew = Crew(50), objectives = Set(objective) )

  // running the championship based on the teams defined in SI3
  println("\n## Running championship with the following players")
  println(s"  - ${playerNames}")
  val results = run(initialization,theBoard, theIsland)

  // Displaying results
  printResults(results)

}
