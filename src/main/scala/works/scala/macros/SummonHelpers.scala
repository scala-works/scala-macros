package works.scala.macros

import scala.compiletime.*
import TypeHelpers.*

/** summon related helpers.
  */
object SummonHelpers:

  /** Summon type class instances from case class constructor arguments via
    * Mirrors. For example, for CaseClass(a: A, b: B) ans TypeClass[T], we can
    * use this to summon a List[TypeClass[ A | B ]]
    *
    * @return
    */
  inline def summonListOf[T <: Tuple, A[_]]: List[A[UAnyType[T]]] =
    _summonListOf[T, UAnyType[T], A]

  /** A helper method for summonListOf, since the main operations is recursive
    * for T, and we would lose a type in the Tuple each time, we keep a version
    * of it constant - U, used as UType[T].
    *
    * @return
    */
  private inline def _summonListOf[T <: Tuple, U, A[U]]: List[A[U]] =
    inline erasedValue[T] match
      case _: EmptyTuple => Nil
      case _: (t *: ts)  =>
        summonInline[A[t]].asInstanceOf[A[U]] :: _summonListOf[ts, U, A]
