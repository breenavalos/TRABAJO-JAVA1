package sistemaGestorDePrestamos;
import java.util.*;

public abstract class credito {
	private cliente unc;
	private float montoPedido;
	private float valorCuota;
	private int plazo;
	private Hashtable AcumulacionPasada;
	private Fecha fechaSolicitada;
	private Fecha fechaOtorgada;
	private int dniCliente;
	
	public credito(cliente unc, float montoPedido,float valorCuota,int plazo,Fecha fechaSolicitada) {
		this.unc=unc;
		this.montoPedido=montoPedido;
		this.valorCuota=valorCuota;
		this.plazo=plazo;
		this.AcumulacionPasada=new Hashtable<Integer,Float>();
		this.fechaSolicitada=fechaSolicitada;
		this.dniCliente=unc.getDni();
		this.fechaOtorgada=null;
		//this.fechaOtorgada=new Fecha();//LA FECHA OTORGADA VA ESTAR EN NULL (por ahora) ya que es una solicitud mientras.
	}
	
	public void poneteMult(int m) {	}
	
	public void ponetePorc(int p) {} 
	
	public abstract boolean Supera();
	
	public abstract void MotivoSupera();
	
	public void PoneteFechaOtrogada() {
			Scanner entrada=new Scanner(System.in);
			System.out.println("INGRESAR FECHA OTORGADA");
			System.out.println("DIA=");
			int dia=entrada.nextInt();
			System.out.println("MES=");
			int mes=entrada.nextInt();
			System.out.println("AÑO= ");
			int a=entrada.nextInt();
			fechaOtorgada=new Fecha(dia,mes,a);
			System.out.println("SE REALIZO LA OTORGACIÓN AL CREDITO EL DIA  "  );
			fechaOtorgada.mostrateFecha();
	}
	public void generarHashTable() {
		int i=1;
		while(i<=this.plazo) {
			AcumulacionPasada.put(i, this.valorCuota);	
			i++;
		}	
	}
	public void darDeBajaCuota() {
		Enumeration llaves = AcumulacionPasada.keys();
		if(llaves.hasMoreElements()) {
			Scanner entrada=new Scanner(System.in);
			int num;
			System.out.println("\n LISTADO DE CUOTAS A PAGAR DEL CLIENTE \n");
			while (llaves.hasMoreElements()) {
				System.out.println("NUMEROS DE CUOTA : " + llaves.nextElement());
			}
			System.out.println("\n INGRESE EL NUMERO DE CUOTA QUE DESEA DAR DE BAJA : \n");
			num=entrada.nextInt();
			AcumulacionPasada.remove(num);
			System.out.println("SE HA DADO DE BAJA EXITOSAMENTE LA CUOTA NUMERO '"+num+"'.");
			Enumeration nomas = AcumulacionPasada.keys();
			if(!(nomas.hasMoreElements())) {
				System.out.println("EL CREDITO YA NO POSEE MÁS CUOTAS DEBE DARLO DE BAJA.");
			}
		}else {
			System.out.println("EL CREDITO YA NO POSEE MÁS CUOTAS DEBE DARLO DE BAJA.");
		}
	}
		
	public void cuotasQueFaltan() {
		Enumeration<Integer> e = AcumulacionPasada.keys();
		int i=0;
		while(e.hasMoreElements()) {
			e.nextElement();
			i++;}
		if(i==0) {
			System.out.println("EL CREDITO YA NO POSEE MÁS CUOTAS DEBE DARLO DE BAJA.");
		}else {
			System.out.println("FALTAN PAGAR  "+ i + " CUOTAS . DE "+ this.valorCuota);
		}
	}
	public int cuotasQueFaltanP() {
		Enumeration<Integer> e = AcumulacionPasada.keys();
		int i=0;
		while(e.hasMoreElements()) {
			e.nextElement();
			i++;}
		return i;
	}
	public void cuotasPagadas() {
		int cp=this.cuotasQueFaltanP();
		if((this.plazo-cp)==this.plazo) {
			System.out.println("EL CREDITO YA NO POSEE MÁS CUOTAS DEBE DARLO DE BAJA.");
		}else{
			System.out.println("HASTA EL DIA DE HOY SE TIENE REGISTRADO  "+ (this.plazo -cp)+ "  CUOTAS PAGAS.");
		}
	}
	
	
	public boolean FueOtorgada() { //FUE OTROGADA????' VALIDAR SI ES CREDITO O SOLICITUD
		return (fechaOtorgada!=null); //VERDADER SI FUE OTORGADA
	}
	
	public void MostrarFechaOtorgada() {
		this.fechaOtorgada.mostrateFecha();
	}
	public void MostrarFechaSolicitada() {
		this.fechaSolicitada.mostrateFecha();
	}
	public abstract void MostrateCredito() ;

	public void esRefaccion() {}
	public void esCompraCasa() {}

//ME TIRA ERROR POR ESTO ????? // tuve que hacerle a ambas clases nada mas que una devuelve 0 y la otra 1
	//public abstract int cantidadRefaccion() ;
	public int cantidadRefaccion() {
		return 0;
	}
	//public abstract int cantidadCompraCasa();
	public int cantidadCompraCasa() {
		return 0;
	}


	public float sueldoCliente() {
		return (unc.getSueldo());
	}
	
	public void MostrarClienteDelCredito() {
		if(unc!=null) {
			unc.MostrarSoloDatosBasicosClientes();
		}else {
			System.out.println(" EL CLIENTE FUE ELIMINADO DEL REGISTRO DE CLIENTES DEL BANCO POR LO CUAL SOLO SE PUEDE TENER ACCESO A SU DNI :"+ this.dniCliente);
		}
	}
	public boolean sosCredito(cliente c) {
		return (unc==c);
	}

	public float getMonto(){
		return(montoPedido);
	}
	
	/*public boolean SuperaPorc() { //PARA QUE ESTA ESTEEE????
		return (false);
	}*/
	
//GETTERS 
	public cliente getUnc() {
		return unc;
	}

	public float getMontoPedido() {
		return montoPedido;
	}

	public float getValorCuota() {
		return valorCuota;
	}

	public int getPlazo() {
		return plazo;
	}

	public Fecha getFechaSolicitada() {
		return fechaSolicitada;
	}

	public Fecha getFechaOtorgada() {
		return fechaOtorgada;
	}
	public int getDniCliente() {
		return this.dniCliente;
	}


}	