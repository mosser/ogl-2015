package fr.unice.polytech.ogl.championships

import eu.ace_design.island.dsl.DiSLand
import eu.ace_design.island.game._
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.map.resources.Resource
import eu.ace_design.island.stdlib.POIGenerators.WithCreeks
import eu.ace_design.island.stdlib.Resources.{FUR, WOOD}
import fr.unice.polytech.ogl.championships.library.{Championship, SI3}

object Week18 extends Championship with SI3 with DiSLand {

  override val seed = Islands.seed18
  override val outputDir =  "./output/week18"

  // Retrieving the island used for this week and storing it as a PDF file
  val theIsland: IslandMap = Islands.week18
  theIsland -> (s"$outputDir/map-week18" as pdf)
  theIsland -> (s"$outputDir/map-week18" as obj)
  theIsland -> (s"$outputDir/map-week18" as json)


  // building a gameBoard with 10 creeks and displaying statistics
  val builder = new GameBoardBuilder(rand = theIsland.random, poiGenerators = Seq(new WithCreeks(10)))
  val theBoard = builder(theIsland).copy(startingTile = Some((13,57)))
  printInfo(theIsland, theBoard)

  // Building the game engine and the associated objectives
  val objectives = Set((FUR, 50), (WOOD, 1000)).asInstanceOf[Set[(Resource, Int)]]
  val initialization = Game(Budget(10000), Crew(15), objectives)

  // running the championship based on the teams defined in SI3
  println("\n## Running championship with the following players")
  println(s"  - $playerNames")
  val results = run(initialization,theBoard, theIsland)

  // Displaying results
  printResults(results)

}
