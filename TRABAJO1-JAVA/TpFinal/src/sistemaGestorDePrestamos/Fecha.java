package sistemaGestorDePrestamos;
import java.util.Calendar;

public class Fecha {

		    private int dia;
		    private int mes;
		    private int año;
		    
		    public Fecha() {
		    	
		    }
		    
		    //Constructor con parámetros
		    public Fecha(int dia, int mes, int año) {
		        this.dia = dia;
		        this.mes = mes;
		        this.año = año;
		    }
		    

		    public void mostrateFecha() {
		    	System.out.println(" EL DÍA "+ getDia() + ".");
		    	System.out.println(" DEL MES "+ getMes() + ".");
		    	System.out.println(" EN EL AÑO "+ getAño() + ".");
		    }
		   
		    //Método para comprobar si la fecha es correcta
		    public boolean fechaCorrecta(int dia,int mes,int año) {
		        boolean diaCorrecto, mesCorrecto, añoCorrecto;
		        añoCorrecto = año > 0;
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
		       if(añoCorrecto && mesCorrecto && diaCorrecto) {
		    	
		    	   return true;
		    	   
		    	   //Fecha unaf=new Fecha(dia,mes,año);
		    	  
		       }else {
		    	
		    	   return false;
		    	   
		    	   //System.out.println("");
		       }
		    }

		    //Método privado para comprobar si el año es bisiesto
		    //Este método lo utiliza el método fechaCorrecta
		    private boolean esBisiesto() {
		        return (año % 4 == 0 && año % 100 != 0 || año % 400 == 0);
		    }		    
		    //setters y getters
		    public void setDia(int d) {
		        dia = d;
		    }
		    public void setMes(int m) {
		        mes = m;
		    }
		    public void setAño(int a) {
		        año = a;
		    }
		    public int getDia() {
		        return dia;
		    }
		    public int getMes() {
		        return mes;
		    }
		    public int getAño() {
		        return año;
		    }	
		
		
}	
		
		
		
		
		
		
		