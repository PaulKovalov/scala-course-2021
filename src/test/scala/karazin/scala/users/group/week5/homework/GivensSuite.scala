package karazin.scala.users.group.week5.homework

import karazin.scala.users.group.week5.homework.givens.{JsonEncoder}

import scala.concurrent.Future
/*
  Write test for all programs in karazin.scala.users.group.week4.homework.givens

  Make sure that the following cases are tested:
    • json string representation for integers works
    • json string representation for booleans works
    • json string representation for strings works
    • json string representation for lists for integers, booleans and strings works
    • json string representation for maps fails on compile time

  Review:
    • https://www.json.org/json-en.html
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/assertions.html#compileerrors
    
  NB: Do not use sync, this homework does not belong async stuff
    
 */
class GivensSuite extends munit.FunSuite:
  
  test("json string representation for integers works") {
    val int: Int = 42
    val encoded: String = summon[JsonEncoder[Int]].encode(int)
    assert(encoded.equals("42"))
  }

  test("json string representation for True boolean works") {
    val True: Boolean = true
    val encoded: String = summon[JsonEncoder[Boolean]].encode(True)
    assert(encoded.equals("true"))
  }

  test("json string representation for False boolean works") {
    val False: Boolean = false
    val encoded: String = summon[JsonEncoder[Boolean]].encode(False)
    assert(encoded.equals("false"))
  }

  test("json string representation for strings works") {
    val str: String = "123456abcdef"
    val encoded: String = summon[JsonEncoder[String]].encode(str)
    assert(encoded.equals("\"" + str + "\""))
  }

  test("json string representation for lists of integers works") {
    val listOfIntegers = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
    val encoded: String = summon[JsonEncoder[List[Int]]].encode(listOfIntegers)
    assert(encoded.equals("[1,2,3,4,5]"))
  }
  
  test("json string representation for lists of strings works") {
    val listOfStrings = "foo" :: "bar" :: "xyz" :: Nil
    val encoded: String = summon[JsonEncoder[List[String]]].encode(listOfStrings)
    assert(encoded.equals("[\"foo\",\"bar\",\"xyz\"]"))
  }
  
  test("json string representation for lists of booleans works") {
    val listOfBooleans = true :: false :: true :: Nil
    val encoded: String = summon[JsonEncoder[List[Boolean]]].encode(listOfBooleans)
    assert(encoded.equals("[true,false,true]"))
  }

  test("json string representation for maps fails on compile time") {
    val code = "summon[JsonEncoder[Map[String, String]]].encode(Map())"
    compileErrors(code)
  }
