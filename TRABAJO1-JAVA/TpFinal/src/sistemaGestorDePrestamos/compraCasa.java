package sistemaGestorDePrestamos;
import java.util.*;
public class compraCasa extends credito{
	private static int porcentajeSueldo;
	
	public compraCasa(cliente unc, float montoPedido,float valorCuota,int plazo,Fecha f){
		super(unc,montoPedido,valorCuota,plazo,f);
	}
	public void MostrateCredito() { 
		if(super.getFechaOtorgada()==null) {
			System.out.println(" EL CREDITO FUE SOLICITADO PARA UNA COMPRA DE CASA EN LA FECHA DE  ");
			this.MostrarFechaSolicitada();
			System.out.println("\n");
			System.out.println(" EL MONTO SOLICITADO ES DE :  "+super.getMontoPedido()+" . \n" );
			System.out.println(" EL VALOR DE LA CUOTA SOLICITADA ES DE  :"+super.getValorCuota()+"  .\n");
			System.out.println(" EL PLAZO ES DE "+super.getPlazo()+" MESES . \n");
			System.out.println("\n");
			System.out.println(" SE ENCUENTRA EN LA COLA DE LAS SOLICITUDES EN ESPERA PARA SER OTORGADO. ");
		}else {
			System.out.println(" EL CREDITO FUE OTORGADO POR UN COMPRA DE CASA DE CASA EN LA FECHA DE ");
			this.MostrarFechaOtorgada();
			System.out.println("\n");
			System.out.println(" EL MONTO SOLICITADO ES DE :  "+super.getMontoPedido()+" . \n" );
			System.out.println(" EL VALOR DE LA CUOTA SOLICITADA ES DE  :"+super.getValorCuota()+"  .\n");
			System.out.println(" EL PLAZO ES DE "+super.getPlazo()+" MESES . \n");
			System.out.println("\n");

			super.cuotasPagadas();
			super.cuotasQueFaltan();
		}
	
	}
	public static int DarPorc() {
		if(porcentajeSueldo==0) {
			Scanner in=new Scanner(System.in);
			System.out.println(" INRESAR PORCENTAJE DEL SUELDO (%) ");
			porcentajeSueldo=in.nextInt();
		}
		return porcentajeSueldo;
	}
	public void esCompraCasa() {
		super.MostrarClienteDelCredito();
		this.MostrateCredito();
	}
	
	
	public void ponetePorc(int p) {
		System.out.println("EL PORCENTAJE ANTERIOR ERA DE "+ compraCasa.porcentajeSueldo +" % . SE MODIFICO A"+ p +"  %" );
		porcentajeSueldo=p;
	}
	
	public boolean Supera() {
		float total=(super.sueldoCliente()*(compraCasa.DarPorc()/100));
		return(super.getValorCuota()>=total);
		
	}
	
	public void MotivoSupera() {
		if(!this.Supera()) {
			System.out.println("NO SE PUEDE OTORGAR EL PRESTAMO PARA COMPRAR CASA DEBIDO A QUE LA CUOTA A PAGAR");
			System.out.println("MENSUALMENTE POR EL PRESTAMO SUPERA AL PORCENTAJE DEL SUELDO ESTABLECIDO");
		}
	}
	public int cantidadCompraCasa() { //PORQUE ESTOS NO TIENEN UN ABSTRACT EN CREDITO????
		return 1;
	}
	/*public int cantidadRefaccion() {
		return 0;
	}*/
}



