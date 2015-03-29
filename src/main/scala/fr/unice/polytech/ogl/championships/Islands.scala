package fr.unice.polytech.ogl.championships

import eu.ace_design.island.dsl.DiSLand
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.map.processes.AssignPitch
import eu.ace_design.island.stdlib.WhittakerDiagrams

object Islands extends DiSLand {

  /**
   * Week 10: A single donuts, with a lot of resources available on top of it.
   */
  val seed10 = 0x762FFC83BEF34278L
  lazy val week10: IslandMap = {
      createIsland shapedAs donut(80.percent, 20.percent) withSize 1000 having 1200.faces usingSeed seed10 builtWith Seq(
      plateau(23), flowing(rivers = 15, distance = 0.2), withMoisture(soils.wet, distance = 400),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean))
  }


  /**
   * Week 11: Still a donuts, with more rivers and higher elevation.
   */
  val seed11 = 0x762FFC83BEF34978L
  lazy val week11: IslandMap = {
    createIsland shapedAs donut(70.percent, 10.percent) withSize 1000 having 1200.faces usingSeed seed11 builtWith Seq(
      plateau(30), flowing(rivers = 30, distance = 0.2), withMoisture(soils.wet, distance = 200),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean))
  }

  /**
   * Week #12: An ellipsis, with no lake and a small mangrove on the east side
   */
  val seed12 = 0x762FFC83BEF34978L
  lazy val week12: IslandMap = {
    createIsland shapedAs ellipsis(x=95.percent, y=40.percent, theta=15) builtWith Seq(
      flatDistribution(30), flowing(rivers = 30, distance = 0.6), withMoisture(soils.wet, distance = 150),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)
    ) usingSeed seed12 withSize 1600 having 3000.faces
  }

  /**
   * Week #13: irregular shape, no lakes, dry.
   */
  val seed13 = 0x2E33A1DD059647D8L
  lazy val week13: IslandMap = {
    createIsland shapedAs radial(factor=0.99) builtWith Seq(
      plateau(30), flowing(rivers = 55, distance = 0.2), withMoisture(soils.dry, distance = 300),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)
    ) usingSeed seed13 withSize 1600 having 3000.faces
  }

}
