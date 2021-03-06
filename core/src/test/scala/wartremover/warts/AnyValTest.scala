package org.wartremover
package test

import org.scalatest.FunSuite

import org.wartremover.warts.AnyVal

class AnyValTest extends FunSuite with ResultAssertions {
  test("AnyVal can't be inferred") {
    val result = WartTestTraverser(AnyVal) {
      List(1, true)
    }
    assertError(result)("Inferred type containing AnyVal")
  }

  test("AnyVal wart obeys SuppressWarnings") {
    val result = WartTestTraverser(AnyVal) {
      @SuppressWarnings(Array("org.wartremover.warts.AnyVal"))
      val x = List(1, true)
    }
    assertEmpty(result)
  }
}
