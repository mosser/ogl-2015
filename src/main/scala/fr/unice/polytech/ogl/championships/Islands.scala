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

  /**
   * Week #14: irregular shape, no lakes, dry.
   */
  val seed14 = 0x1AAA5FA02D1ADBCAL
  lazy val week14: IslandMap = {
    createIsland shapedAs radial(factor=1.25) builtWith Seq(
      plateau(20), flowing(rivers = 75, distance = 0.2), withMoisture(soils.wet, distance = 200),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)
    ) withSize 1600 having 3000.faces  usingSeed seed14
  }


  /**
   * Week #16: irregular shape, no lakes, dry.
   */
  val seed16 = 0x8426E0E9FFF5839AL
  lazy val week16: IslandMap = {
    createIsland shapedAs radial(factor=0.1) builtWith Seq(
      plateau(40), flowing(rivers = 50, distance = 0.4), withMoisture(soils.dry, distance = 400),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)
    ) withSize 1600 having 3000.faces  usingSeed seed16
  }


  /**
   * Week #17: irregular shape, no lakes, dry.
   */
  val seed17 = 0xAA50A6C47539125DL
  lazy val week17: IslandMap = {
    createIsland shapedAs radial(factor=0.756) builtWith Seq(
      plateau(40), flowing(rivers = 8, distance = 0.1), withMoisture(soils.wet, distance = 600),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)
    ) withSize 1600 having 3000.faces  usingSeed seed17
  }

  /**
   * Week #18:  Circular atoll
   */
  val seed18 = 0x7EDA89CECD919441L
  lazy val week18: IslandMap = {
    createIsland shapedAs donut(90.percent, 70.percent) builtWith Seq(
      plateau(5), flowing(rivers = 15, distance = 0.1), withMoisture(soils.wet, distance = 600),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)
    ) withSize 1600 having 3000.faces  usingSeed seed18
  }

  /**
   * Week #19: irregular shape, no lakes, extremely wet.
   */
  val seed19 = 0xE605A267A61D1AA6L
  lazy val week19: IslandMap = {
    createIsland shapedAs radial(factor=0.556) builtWith Seq(
      plateau(50), flowing(rivers = 30, distance = 0.4), withMoisture(soils.wet, distance = 800),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)
    ) withSize 1600 having 3000.faces  usingSeed seed19
  }

  /**
   * Week #20: irregular shape, no lakes, dry, nordic biomes.
   */
  val seed20 = 0x3170F56CD3009A9BL
  lazy val week20: IslandMap = {
    createIsland shapedAs radial(factor=0.256) builtWith Seq(
      plateau(50), flowing(rivers = 30, distance = 0.4), withMoisture(soils.normal, distance = 400),
      AssignPitch, usingBiomes(WhittakerDiagrams.nordic)
    ) withSize 1600 having 3000.faces  usingSeed seed20
  }

}
