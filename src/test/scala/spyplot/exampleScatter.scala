package spyplot

import spyplot._
import nebula._
import nebula.util._
import org.scalatest._
import org.scalacheck._
import org.scalacheck.Prop._
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import scala.swing.Dialog
import java.awt.image.BufferedImage

////////////////////////////////////////

class TestMain extends FunSuite {
  test("make scatter plot") {
    val xs = 0.0 to 1.0 by 0.01
    val ys = xs map (x => math.sin(10 * x))

    val plot = new SPyPlot {
      override def source = s"""
xs = ${xs.toPyList}
ys = ${ys.toPyList}
    
fig = figure()
ax = fig.add_subplot(111)
ax.plot(xs, ys, 'o')

savefig("${plotFile}", bbox_inches='tight')
"""
    } toImage
  }
}


