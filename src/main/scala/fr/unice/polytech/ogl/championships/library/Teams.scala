package fr.unice.polytech.ogl.championships.library

import eu.ace_design.island.bot.IExplorerRaid

trait Teams {
  def players: Map[String, IExplorerRaid]

  def playerNames: String = players.map { case (n,_) => n.toUpperCase }.toSeq.sorted mkString ", "

}

trait SI3 extends Teams {

  def players: Map[String, IExplorerRaid] = g1 ++ g2 ++ g3 ++ g4

  private val g1 = Map(
    "islaa" -> new fr.unice.polytech.ogl.islaa.Explorer(),
    "islab" -> new fr.unice.polytech.ogl.islab.Explorer(),
    "islac" -> new fr.unice.polytech.ogl.islac.Explorer()
    // "islad" -> new fr.unice.polytech.ogl.islad.Explorer()
    // "islae" -> new fr.unice.polytech.ogl.islae.Explorer()
  )

  private val g2 = Map(
    "islba" -> new fr.unice.polytech.ogl.islba.Explorer(),
    "islbb" -> new fr.unice.polytech.ogl.islbb.Explorer(),
    "islbc" -> new fr.unice.polytech.ogl.islbc.Explorer(),
    // "islbd" -> new fr.unice.polytech.ogl.islbd.Explorer()
    "islbe" -> new fr.unice.polytech.ogl.islbe.Explorer()
  )

  private val g3 = Map(
    "islca" -> new fr.unice.polytech.ogl.islca.Explorer(),
    "islcb" -> new fr.unice.polytech.ogl.islcb.Explorer(),
    "islcc" -> new fr.unice.polytech.ogl.islcc.Explorer(),
    "islcd" -> new fr.unice.polytech.ogl.islcd.Explorer(),
    // "islce" -> new fr.unice.polytech.ogl.islce.Explorer(), // Constructor must be public !!
    "islcf" -> new fr.unice.polytech.ogl.islcf.Explorer()
  )

  private val g4 = Map(
    "islda" -> new fr.unice.polytech.ogl.islda.Explorer(),
    "isldb" -> new fr.unice.polytech.ogl.isldb.Explorer(),
    // "isldc" -> new fr.unice.polytech.ogl.isldc.Explorer(),
    "isldd" -> new fr.unice.polytech.ogl.isldd.Explorer(),
    "islde" -> new fr.unice.polytech.ogl.islde.Explorer(),
    "isldf" -> new fr.unice.polytech.ogl.isldf.Explorer()
  )



}
