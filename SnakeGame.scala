package o1.snake

import o1.*

class SnakeGame(initialPos: GridPos, initialHeading: CompassDir):

  private var segments = Vector(initialPos)
  var snakeHeading = initialHeading
  var nextFood = randomEmptyLocation()

  def snakeSegments = this.segments

  def isOver =
    val head = this.segments.head
    val validCoords = 1 until SizeInSquares
    val collidedWithWall = !validCoords.contains(head.x) || !validCoords.contains(head.y)
    val collidedWithSelf = this.segments.tail.contains(head)

    collidedWithWall || collidedWithSelf

  def advance() =

    val head = this.segments.head
    val newHead = head.neighbor(this.snakeHeading)
    this.segments = newHead +: this.segments
    if newHead == this.nextFood then
      this.nextFood = randomEmptyLocation()
      val newSegments = this.segments :+ this.segments.last

    else
      this.segments = this.segments.init

  private def randomEmptyLocation(): GridPos =
    val screenFull = this.snakeSegments.size >= (SizeInSquares - 1) * (SizeInSquares - 1)
    if !screenFull then
      def randomLocs = LazyList.continually( GridPos.random(1, SizeInSquares) )
      randomLocs.dropWhile(this.snakeSegments.contains).head
    else
      GridPos(0, 0)

end SnakeGame

