import nebula._
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File

////////////////////////////////////

package object spyplot {
  trait SPyPlotHelpers {
    implicit class ToPyList[A](self: Seq[A]) {
      def toPyList: String = self.mkString("[", ", ", "]")
    }

    def quote(string: String): String = "\"" + string + "\""
  }

  trait SPyPlot extends SPyPlotHelpers {
    def source: String

    /////////////////////////////////////

    val imports = """
import sys
import matplotlib
matplotlib.use('Agg')
from matplotlib.pyplot import *
"""

    val plotFile = File.createTempFile("SPyPlot_", ".png")

    def toImage: BufferedImage = {
      val pySource = File.createTempFile("SPyPlot_", ".py")
      pySource.writeString(imports + "\n\n" + source)
      nebula.util.IO.runSystemCommand(s"""python ${pySource}""")

      ImageIO.read(plotFile)
    }
  }
}