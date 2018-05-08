package ru.prsolution.winstrike.ui.main

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import ru.prsolution.winstrike.R
import ru.prsolution.winstrike.WinstrikeApp
import ru.prsolution.winstrike.mvp.models.GameRoom
import ru.prsolution.winstrike.mvp.models.Seat
import ru.prsolution.winstrike.mvp.models.Wall
import timber.log.Timber


/*protocol UISeatsViewDelegate: class {
    func seatPicked(id: String, unselect: Bool, publicPid: String)
}*/
public class DrawView(context: Context, room: GameRoom) : View(context) {

    val seats: List<Seat> = room.seats
    var p: Paint
    var rectWall: Rect
    var bitmap: Bitmap
    var dx = 0f
    var dy = 0f
    var dxx = 0f
    var dyy = 0f
    var angle : Double
    var xScaleFactor: Float
    var yScaleFactor: Float


    // Ряд
    var raw: Int

    init {
        raw = 1
        p = Paint()
        // настройка кисти
        // красный цвет
        p.color = Color.WHITE
        p.style = Paint.Style.STROKE
        // толщина линии = 10
        p.setStrokeWidth(10f)
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.seat_darkgrey);

        val height = WinstrikeApp.getInstance().displayHeightPx
        val widht =  WinstrikeApp.getInstance().displayWidhtPx
        val wall:Wall
        wall = room.walls[0]

        xScaleFactor = (widht / wall.end.x)
        yScaleFactor = (height/ wall.end.y)

        angle = 0.0
        val leftXTop = wall.start.x * xScaleFactor.toInt()
        val leftYTop = wall.start.x * (yScaleFactor/1.5).toInt()
        val bottomXRight = wall.end.x * xScaleFactor.toInt() + bitmap.width
        val bottomYRight = wall.end.y * (yScaleFactor).toInt()

        rectWall = Rect(leftXTop,leftYTop,bottomXRight,bottomYRight)
    }

    override fun onDraw(canvas: Canvas) {
        // заливка канвы цветом
        //canvas.drawARGB(80, 102, 204, 255)
        canvas.drawColor(Color.BLACK)
        p.color = Color.RED
        canvas.drawRect(rectWall,p)

        var prevSeatDx = seats[0].dx.toFloat()
        var prevSeatDy = seats[0].dy.toFloat()


        seats.forEachIndexed { index, seat ->

            dx = seat.dx.toFloat() * xScaleFactor
            dy = seat.dy.toFloat() * yScaleFactor/1.5f
            angle = Math.toDegrees(seat.angle)

            Timber.d("xScaleFactor: %s",xScaleFactor)
            Timber.d("yScaleFactor: %s",yScaleFactor)

            Timber.d("index[%s] - raw: %s",index, raw)
            Timber.d("index[%s] - dx: %s, dy: %s", index, seat.dx, seat.dy)
            canvas.save()
//            canvas.drawPoint(dx, dy, p)
            canvas.translate(dx, dy)
            canvas.rotate(angle.toFloat(),bitmap.width /2f, bitmap.height/2f)
            canvas.drawBitmap(bitmap, 0f, 0f, p)
            canvas.restore()

            prevSeatDx = seat.dx.toFloat()
            prevSeatDy = seat.dy.toFloat()

        }

        class UISeatsView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
            //    weak var delegate: UISeatsViewDelegate?
            private val sh: SurfaceHolder = holder
            private val paint = Paint(ANTI_ALIAS_FLAG)
            private val rect = Rect(50, 50, 100, 100)

            init {
                sh.addCallback(this)
                paint.color = Color.BLUE
                paint.style = Paint.Style.FILL
            }

            override fun surfaceCreated(holder: SurfaceHolder) {
                val canvas = sh.lockCanvas()
                canvas.drawColor(Color.BLACK)
                canvas.drawRect(rect, paint)
                sh.unlockCanvasAndPost(canvas)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int,
                                        height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {}

            lateinit var gameRoom: GameRoom

            var pickedSeats = mutableSetOf<Int>()

            fun setData(gameRoom: GameRoom) {
                this.gameRoom = gameRoom
                this.drawRoom()
            }

            /**вычисляет расстояние от начала координат до начальной точки картинки через гипотенузу*/
            fun getDist(coord: Point): Double {
                val d = Math.sqrt(Math.pow(coord.x.toDouble(), 2.0) + Math.pow(coord.y.toDouble(), 2.0))
                return d
            }


            private fun drawRoom() {
//        var mainGroup = Group()
                //добавляем кресла
                gameRoom.seats.forEachIndexed { index, seat ->
                    //            var seatView = createMImage(seatApi)
                }
            }

/*    private fun createMImage(seatApi: SeatApi): ImageView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var image = SeatType.getImage(seatApi.type)
//        var seatView = MImage(image: image, opaque: false)

//        var animation = seatView.srcVar.value = "ChooseSeat/seatGrey.png";
        var seatTransform = Transform
                .move(dx: seatApi.dx, dy: seatApi.dy)
                .rotate(
                        angle: seatApi.angle,
                        x: Double(image.size.width) / 2,
                        y: Double(image.size.height) / 2
        )
        seatView.place = seatTransform
        return seatView
    }*/
        }
    }
}
