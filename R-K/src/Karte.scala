import scala.io._

class Karte (filename: String) {
    var staedte: List[Stadt] = List.empty
    var strassen: List[Strasse] = List.empty

    def getOrCreateStadt(s: String) : Stadt = {
        for (stadt <- staedte)
            if (stadt.name == s)
                return stadt;

        val stadt = new Stadt(s)
        staedte = stadt :: staedte
        return stadt
        
        //  Damali probe
    }

    for (line <- Source.fromFile(filename).getLines) {
        val pair = line.split(";")
        val stadt1 = getOrCreateStadt(pair(0))
        val stadt2 = getOrCreateStadt(pair(1))

        strassen = new Strasse(stadt1, stadt2) :: strassen
    }
    
 
	
 
}
