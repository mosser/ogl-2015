package fr.unice.polytech.ogl.championships

import eu.ace_design.island.dsl.DiSLand
import eu.ace_design.island.game._
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.map.resources.Resource
import eu.ace_design.island.stdlib.POIGenerators.WithCreeks
import eu.ace_design.island.stdlib.Resources.{FLOWER, FUR, SUGAR_CANE}
import fr.unice.polytech.ogl.championships.library.{Championship, SI3}

object Week15 extends Championship with SI3 with DiSLand {

  override val seed = Islands.seed12
  override val outputDir =  "./output/week15"
  override val objectives = Set((SUGAR_CANE, 3000), (FUR, 600), (FLOWER, 2)).asInstanceOf[Set[(Resource, Int)]]

  // Retrieving the island used for this week and storing it as a PDF file
  val theIsland: IslandMap = Islands.week12
  theIsland -> (s"$outputDir/map-week15" as pdf)
  theIsland -> (s"$outputDir/map-week15" as obj)
  theIsland -> (s"$outputDir/map-week15" as json)


  // building a gameBoard with 10 creeks and displaying statistics
  val builder = new GameBoardBuilder(rand = theIsland.random, poiGenerators = Seq(new WithCreeks(10)))
  val theBoard = builder(theIsland).copy(startingTile = Some((37,95)))
  printInfo(theIsland, theBoard)

  // Building the game engine and the associated objectives
  val initialization = Game(Budget(5000), Crew(25), objectives)

  // running the championship based on the teams defined in SI3
  println("\n## Running championship with the following players")
  println(s"  - $playerNames")
  val results = run(initialization,theBoard, theIsland)

  // Displaying results
  printResults(results)

}
