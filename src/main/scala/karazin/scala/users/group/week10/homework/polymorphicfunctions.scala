package karazin.scala.users.group.week10.homework

object polymorphicfunctions extends App:
  
  /*
    Provide an implementation for the next functions
  */
  val `I₍₂,₄₎⁴`: [`α₁`, `α₂`, `α₃`, `α₄` ] ⇒ `α₁` ⇒ `α₂` ⇒ `α₃` ⇒ `α₄`⇒ (`α₂`, `α₄`) =
    [`α₁`, `α₂`, `α₃`, `α₄`] ⇒ (`x₁`: `α₁`) ⇒ (`x₂`: `α₂`) ⇒ (`x₃`: `α₃`) ⇒ (`x₄`: `α₄`) ⇒ (`x₂`, `x₄`)

  val `(f ० g ० h ० i)(x)`: [A, B, C, D, E] => (D => E)
                                => (C => D)
                                => (B => C)
                                => (A => B)
                                => (A => E) =
    [A, B, C, D, E] => (f: (D => E)) => (g: (C => D)) => (h: (B => C)) => (i: (A => B)) => (x: A) => f(g(h(i(x))))


end polymorphicfunctions
