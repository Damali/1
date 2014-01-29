
import scala.io._

class Spieler(val name2:String){
    val name:String = name2
    var punktestand:Int = 0

    def aussetzen():Unit = { }

    def besetzen(sdt:Stadt):Unit = {
        if (sdt.besetzer == null )
            sdt.besetzer = this
    }

   
}
