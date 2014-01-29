  import scala.io._
  
  
class Match(spieler1:Spieler,spieler2:Spieler,karte:Karte){
     val historie = new Historie()  
     var aktiverSpieler:Spieler = { spieler1 }
	 var aussetzenanzahl:Int = 0
	
	     
	 
		 	for (i <- karte.staedte) {
		    	if (i.name == "Rom") i.besetzer = spieler1 ;
		        if (i.name == "Karthago") i.besetzer = spieler2 } 
		       	
	 
	
		       
 
	
def verhungern():Unit = {
	    
	    for ( i <- karte.staedte){
	      i.istUmzingelt = true  ;
	       if (i.besetzer == null) i.istUmzingelt = false 
	    }
	    
	    for (i <- karte.strassen){
	      if (i.stdt1.besetzer == null) 
	            i.stdt2.istUmzingelt = false  ;
	      if (i.stdt2.besetzer == null) 
	            i.stdt1.istUmzingelt = false 
	     }
	    
	    for(i <- 0 until karte.staedte.length){
	    for (i <- karte.strassen){
	      if ((i.stdt1.besetzer == i.stdt2.besetzer ) && (i.stdt2.istUmzingelt == false))
	           i.stdt1.istUmzingelt = false  ;
	      if ((i.stdt1.besetzer == i.stdt2.besetzer ) && (i.stdt1.istUmzingelt == false))
	           i.stdt2.istUmzingelt = false  ; 
	                         } 
	    								 }
	    for ( i <- karte.staedte){
	    if (i.istUmzingelt == true) 
	        i.besetzer = null 
	    }
	    
	                                       
	   }
	  
	  
def schwarzWeissVerhungern():Unit= {
	    
	    for ( i <- karte.staedte){
	      i.schwarzUmzingelt = true  ;
	       if (i.besetzer != null) i.schwarzUmzingelt = false ;
	        i.weissUmzingelt = true  ;
	       if (i.besetzer != null) i. weissUmzingelt = false  
	    }
	    
	     for (i <- karte.strassen){
	      if (i.stdt1.besetzer == spieler2) 
	            i.stdt2.schwarzUmzingelt = false  ;
	      if (i.stdt2.besetzer == spieler2) 
	            i.stdt1.schwarzUmzingelt = false ;
	      if (i.stdt1.besetzer == spieler1) 
	            i.stdt2. weissUmzingelt = false  ;
	      if (i.stdt2.besetzer == spieler1) 
	            i.stdt1. weissUmzingelt = false 
	     }
	    
	    for(i <- 0 until karte.staedte.length){
	    for (i <- karte.strassen){
	      if ((i.stdt1.besetzer == i.stdt2.besetzer ) && (i.stdt2.schwarzUmzingelt == false))
	           i.stdt1.schwarzUmzingelt = false  ;
	      if ((i.stdt1.besetzer == i.stdt2.besetzer ) && (i.stdt1.schwarzUmzingelt == false))
	           i.stdt2.schwarzUmzingelt = false  ; 
	      if ((i.stdt1.besetzer == i.stdt2.besetzer ) && (i.stdt2.weissUmzingelt == false))
	           i.stdt1. weissUmzingelt = false  ;
	      if ((i.stdt1.besetzer == i.stdt2.besetzer ) && (i.stdt1.weissUmzingelt == false))
	           i.stdt2. weissUmzingelt = false  ;
	                         } 
	    								 }
	   
	    
	  }
	  
	  
	  
	  
def spielerWechsel:Unit = { 
	          if (aktiverSpieler == spieler1) 
		      aktiverSpieler = spieler2 
		      else aktiverSpieler = spieler1 
		                       }
		       
def setZug(wahl:Int,city:String):Unit = {
             
               def zugistaussetzen():Unit= {      
			    		  aktiverSpieler.aussetzen ;
			    		   aussetzenanzahl +=1 ; 
			    		   println("Spieler " + aktiverSpieler.name + " setzt aus") 
			    		   spielerWechsel ;
			    	       }
		       
			                                        
			 def zugistbesetzen():Unit={ 
			                         
				           var z:Stadt = null
					          for ( i <-  karte.staedte){ 
					              if (i.name.toLowerCase() == city) z = i  }
					         if (z != null )
							       {  if ( z.besetzer == null  ){
								        aktiverSpieler.besetzen(z) ;
								        aussetzenanzahl = 0 ;
								        println(aktiverSpieler.name + " besetzt" + z.name ) ; 
								        spielerWechsel ; 
								        stellung(historie) } 
							         else println("Ungueltiges Zug  \n Bitte versuchen Sie nochmal") }                     
					         else println("Ungueltige Stadtname \n Bitte versuchen Sie nochmal")  }    
			 
			 
			 if (wahl==1)  zugistaussetzen() ;
			 if (wahl==2) zugistbesetzen() ;
		
		                 }
	 
	
def stellung(historie:Historie):Unit = {
             var currentstellung:List[Pair[Stadt,Spieler]]= Nil
          for (stadt <- karte.staedte){
				    currentstellung = (stadt, stadt.besetzer) :: currentstellung 
				    }
		 if  (historie.stellungsHistorie.contains(currentstellung))
		     { println("Stellung wird wiederholt. Zug ist verboten und wird abgebrochen!" ) ;
		                   for( fruehereStellung <- historie.stellungsHistorie.head ) {
		                        fruehereStellung._1.besetzer = fruehereStellung._2 }
		                  spielerWechsel
		       }      
		 	else	{historie.stellungsHistorie = currentstellung :: historie.stellungsHistorie  } 
		 
     }
  
			   
def spielende:Unit = {
		       if (aussetzenanzahl == 4) 
		       println(" Dieses Spiel kommt zu Ende") ; 
		       schwarzWeissVerhungern;
		       
		       
		     
	          for ( st <- karte.staedte) { 
	              if ((st.besetzer == spieler1) || (st.schwarzUmzingelt)        ) spieler1.punktestand +=1 ;
		          if  ((st.besetzer == spieler2) || (st.weissUmzingelt)        ) spieler2.punktestand +=1
		           }
		              
             if (spieler1.punktestand > spieler2.punktestand)
		        println(spieler1.name + ": " + spieler1.punktestand + " Punkte \n " +  spieler2.name  + ": " + spieler2.punktestand + " Punkte \n " + spieler1.name + " hat gewonnen")
	         if (spieler2.punktestand > spieler1.punktestand)
		         println(spieler1.name + ": " + spieler1.punktestand + " Punkte \n " +  spieler2.name  + ": " + spieler2.punktestand + " Punkte \n " + spieler2.name + " hat gewonnen")
		     if  (spieler1.punktestand == spieler2.punktestand)	
		         println(spieler1.name + ": " + spieler1.punktestand + " Punkte \n " +  spieler2.name  + ": " + spieler2.punktestand + " Punkte \n  Spiel Unentschieden" )
		       }
    
   
		 
  }