import scala.io._

object ConsoleGame {

    def main(args: Array[String]) {
        println("Bitte Drucken Sie das File Location")
        
        val k1 = new Karte(if (args.length > 0) args(0) else "C:/Users/ali/Desktop/Studium/3. Semester/EST/Zettel6/t.txt")
        
        println("Drucken Sie nun die Name von Spieler 1")
        val spieler1name = readLine()
        val sp1 = new Spieler(spieler1name)
        println("Spieler 1 : " + spieler1name)
        
        println("\nDrucken Sie nun die Name von Spieler 2")
        val spieler2name = readLine()
        val sp2 = new Spieler(spieler2name)
        println("Spieler 2 : " + spieler2name)
        
        val m = new Match(sp1,sp2,k1)

        
        while (m.aussetzenanzahl != 4) {
            print("\nSpiel geht weiter! \nSpieler " + m.aktiverSpieler.name + " ist dran")
            println("\nWas moechten Sie jetzt tun? \nBitte Drucken 1 um auszusetzen oder 2 um eine Stadt zu besetzen")
            val wahl:Int = readInt()
            if (wahl == 1)
               { m.setZug(wahl,"")} 
            
           else if (wahl == 2) 
                  { println("Welche Stadt moechten Sie besezten ? ");
                             val city = readLine() ;
                             m.setZug(wahl,city.toLowerCase())   ; m.verhungern                 }
            
           else println(" Ungueltiges Wahl")
      
        }
        
        m.spielende
    }
}