package fr.unice.polytech.ogl.championships

import eu.ace_design.island.bot.IExplorerRaid
import eu.ace_design.island.dsl.DiSLand
import eu.ace_design.island.game.GameBoard
import eu.ace_design.island.map.IslandMap


trait Championship extends App with DiSLand {

  val theIsland: IslandMap

  val theBoard: GameBoard

  def printInfo() {
    println("\n## Island global statistics")
    theIsland.stats match {
      case None =>
      case Some(d) => d.toSeq sortBy { _._1.toString  } foreach { case (stat, value) => println(s"  - $stat => $value") }
    }

    println("\n## Resources amounts")
    theBoard.contents foreach { case (res, amount) => println(f"  - ${res}%-10s => $amount") }

    println("\n## Point of Interests available")
    theBoard.pois foreach { case (loc, pois) => println(s"  $loc: $pois") }
  }

  val players: Map[String, IExplorerRaid] = Map(
    "islaa" -> new fr.unice.polytech.ogl.islaa.Explorer(),
    "islab" -> new fr.unice.polytech.ogl.islab.Explorer(),
    "islac" -> new fr.unice.polytech.ogl.islac.Explorer(),
    "islad" -> new fr.unice.polytech.ogl.islad.Explorer(),
    // "islae" -> new fr.unice.polytech.ogl.islae.Explorer(),
    "islba" -> new fr.unice.polytech.ogl.islba.Explorer(),
    // "islbb" -> new fr.unice.polytech.ogl.islbb.Explorer(),
    "islbc" -> new fr.unice.polytech.ogl.islbc.Explorer(),
    "islbd" -> new fr.unice.polytech.ogl.islbd.Explorer(),
    // "islbe" -> new fr.unice.polytech.ogl.islbe.Explorer(),
    "islca" -> new fr.unice.polytech.ogl.islca.Explorer(),
    "islcb" -> new fr.unice.polytech.ogl.islcb.Explorer(),
    "islcc" -> new fr.unice.polytech.ogl.islcc.Explorer(),
    "islcd" -> new fr.unice.polytech.ogl.islcd.Explorer(),
    // "islce" -> new fr.unice.polytech.ogl.islce.Explorer(), // Constructor must be public !!
    "islcf" -> new fr.unice.polytech.ogl.islcf.Explorer(),
    "islda" -> new fr.unice.polytech.ogl.islda.Explorer(),
    "isldb" -> new fr.unice.polytech.ogl.isldb.Explorer(),
    "isldc" -> new fr.unice.polytech.ogl.isldc.Explorer(),
    "isldd" -> new fr.unice.polytech.ogl.isldd.Explorer(),
    "islde" -> new fr.unice.polytech.ogl.islde.Explorer(),
    "isldf" -> new fr.unice.polytech.ogl.isldf.Explorer()
  )


}
