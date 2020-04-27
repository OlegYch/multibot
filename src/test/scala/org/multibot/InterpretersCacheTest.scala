package org.multibot

import org.scalatest.funsuite.AnyFunSuite

class InterpretersCacheTest extends AnyFunSuite {

  test("testScalaInterpreter") {
    val r = InterpretersCache(Nil).scalaInterpreter("hello"){(imain, out) =>
      imain.interpret("1")
      out.toString.trim
    }
    assert(r === "val res0: Int = 1")
  }

}
