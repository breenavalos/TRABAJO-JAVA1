package sistemaGestorDePrestamos;

import java.util.Scanner;

public class cRefaccion extends credito{
	private static int cantSueldo;
	
	public cRefaccion(cliente unc, float montoPedido,float valorCuota,int plazo,Fecha f){
		super(unc,montoPedido,valorCuota,plazo,f);
	}
	
	public void MostrateCredito() {
		if(super.getFechaOtorgada()==null) {
			System.out.println(" EL CREDITO FUE SOLICITADO PARA REFACCIONAMIENTO DE CASA EN LA FECHA DE  ");
			this.MostrarFechaSolicitada();
			System.out.println("\n");
			System.out.println(" EL MONTO SOLICITADO ES DE :  "+super.getMontoPedido()+" . \n" );
			System.out.println(" EL VALOR DE LA CUOTA SOLICITADA ES DE  :"+super.getValorCuota()+"  .\n");
			System.out.println(" EL PLAZO ES DE "+super.getPlazo()+" MESES . \n");
			System.out.println("\n");
			System.out.println(" SE ENCUENTRA EN LA COLA DE LAS SOLICITUDES EN ESPERA PARA SER OTORGADO. ");
		
		}else {
			System.out.println(" EL CREDITO FUE OTORGADO POR UN REFACCIONAMIENTO DE CASA EN LA FECHA DE ");
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
	//FUNCIONES DE VARIABLE DE CLASE
	
		//cantsueldo seria el doble, triple o sea lo que no tiene que superar del sueldo
	public static float DarMult() {
			if(cantSueldo==0) {
				Scanner in=new Scanner(System.in);
				System.out.println(" INGRESAR EL MULTIPLE DEL SUELDO DE CONDICION ");
				cantSueldo=in.nextInt();
			}
		return cantSueldo;
	}
		
	public void poneteMult(int m) {
			cantSueldo=m;
	}
		
	public void esRefaccion() {
			super.MostrarClienteDelCredito();
			this.MostrateCredito();
	}
		
		//FIN DE FUNC DE VC
	public boolean Supera() { //DA VERDADERO CUMPLA
		return(super.getMonto()<=(super.sueldoCliente()*(cRefaccion.DarMult())));
	}
	
	public void MotivoSupera() {
		if(!this.Supera()) {
			System.out.println("NO SE PUEDE OTORGAR EL PRESTAMO DE REFACCION DEBIDO A QUE EL MONTO");
			System.out.println("SOLICITADO SUPERA AL SUELDO DEL CLIENTE");
		}
	}
	public int cantidadRefaccion() {
		return 1;
	}
	/* int cantidadCompraCasa() {
		return 0;
	}*/
		
}

