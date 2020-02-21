package sistemaGestorDePrestamos;
import java.util.Calendar;

public class Fecha {

		    private int dia;
		    private int mes;
		    private int a�o;
		    
		    public Fecha() {
		    	
		    }
		    
		    //Constructor con par�metros
		    public Fecha(int dia, int mes, int a�o) {
		        this.dia = dia;
		        this.mes = mes;
		        this.a�o = a�o;
		    }
		    

		    public void mostrateFecha() {
		    	System.out.println(" EL D�A "+ getDia() + ".");
		    	System.out.println(" DEL MES "+ getMes() + ".");
		    	System.out.println(" EN EL A�O "+ getA�o() + ".");
		    }
		   
		    //M�todo para comprobar si la fecha es correcta
		    public boolean fechaCorrecta(int dia,int mes,int a�o) {
		        boolean diaCorrecto, mesCorrecto, a�oCorrecto;
		        a�oCorrecto = a�o > 0;
		        mesCorrecto = mes >= 1 && mes <= 12;
		        switch (mes) {
		            case 2:
		                if (esBisiesto()) {
		                    diaCorrecto = dia >= 1 && dia <= 29;
		                } else {
		                    diaCorrecto = dia >= 1 && dia <= 28;
		                }
		                break;
		            case 4:
		            case 6:
		            case 9:
		            case 11:
		                diaCorrecto = dia >= 1 && dia <= 30;
		                break;
		            default:
		                diaCorrecto = dia >= 1 && dia <= 31;
		        }
		       if(a�oCorrecto && mesCorrecto && diaCorrecto) {
		    	
		    	   return true;
		    	   
		    	   //Fecha unaf=new Fecha(dia,mes,a�o);
		    	  
		       }else {
		    	
		    	   return false;
		    	   
		    	   //System.out.println("");
		       }
		    }

		    //M�todo privado para comprobar si el a�o es bisiesto
		    //Este m�todo lo utiliza el m�todo fechaCorrecta
		    private boolean esBisiesto() {
		        return (a�o % 4 == 0 && a�o % 100 != 0 || a�o % 400 == 0);
		    }		    
		    //setters y getters
		    public void setDia(int d) {
		        dia = d;
		    }
		    public void setMes(int m) {
		        mes = m;
		    }
		    public void setA�o(int a) {
		        a�o = a;
		    }
		    public int getDia() {
		        return dia;
		    }
		    public int getMes() {
		        return mes;
		    }
		    public int getA�o() {
		        return a�o;
		    }	
		
		
}	
		
		
		
		
		
		
		