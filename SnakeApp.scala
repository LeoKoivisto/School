package o1.snake

import o1.*


val SizeInSquares = 40


val PixelsPerGridSquare = 20
val WorldSizeInPixels = PixelsPerGridSquare * SizeInSquares
val GameSpeed = 15 //24

val Background = rectangle(WorldSizeInPixels, WorldSizeInPixels, White)
val SegmentPic = circle(PixelsPerGridSquare * 1.5, Purple)
val FoodPic    = rectangle(PixelsPerGridSquare * 2 / 3, PixelsPerGridSquare * 2 / 3, Green)


def toPixelPos(gridPos: GridPos) = Pos(gridPos.x * PixelsPerGridSquare, gridPos.y * PixelsPerGridSquare)


val initialGridPosOfSnake = GridPos(SizeInSquares / 5, SizeInSquares / 2)
val game = SnakeGame(initialGridPosOfSnake, East)


object snakeView extends View(game, GameSpeed, "Snake"):


  def makePic =

    val snakePos = game.snakeSegments.map(segment => toPixelPos(segment))
    val foodPos  = toPixelPos(game.nextFood)
    Background.placeCopies(SegmentPic, snakePos).place(FoodPic, foodPos)

  override def onTick() =
    game.advance()

  override def isDone = {
    if game.isOver then
      println("Game over!")
      true
    else
      false
  }

  override def onKeyDown(key: Key) =
    CompassDir.fromKey(key) match
      case Some(newDirection) =>
        game.snakeHeading = newDirection
      case None =>

end snakeView


@main def runSnakeApp() =
  snakeView.start()

