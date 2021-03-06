package fr.unice.polytech.ogl.championships

import eu.ace_design.island.dsl.DiSLand
import eu.ace_design.island.game._
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.stdlib.POIGenerators.WithCreeks
import eu.ace_design.island.stdlib.Resources._
import fr.unice.polytech.ogl.championships.library.{Championship, SI3}

object Week20 extends Championship with SI3 with DiSLand {

  override val seed = Islands.seed19
  override val outputDir =  "./output/week20"
  override val objectives = Set((PLANK, 500), (FUR, 3000), (GLASS, 15))

  // Retrieving the island used for this week and storing it as a PDF file
  val theIsland: IslandMap = Islands.week20
  theIsland -> (s"$outputDir/map-week20" as pdf)
  theIsland -> (s"$outputDir/map-week20" as obj)
  theIsland -> (s"$outputDir/map-week20" as json)


  // building a gameBoard with 10 creeks and displaying statistics
  val builder = new GameBoardBuilder(rand = theIsland.random, poiGenerators = Seq(new WithCreeks(10)))
  val theBoard = builder(theIsland).copy(startingTile = Some((145,44)))
  printInfo(theIsland, theBoard)

  // Building the game engine and the associated objectives
  val initialization = Game(Budget(7000), Crew(15), objectives)

  // running the championship based on the teams defined in SI3
  println("\n## Running championship with the following players")
  println(s"  - $playerNames")
  val results = run(initialization,theBoard, theIsland)

  // Displaying results
  printResults(results)

}
