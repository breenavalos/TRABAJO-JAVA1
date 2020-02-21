package sistemaGestorDePrestamos;
import java.util.*;
public class bancoHipotecario {
	private ArrayList<cliente> clientes;
	private ArrayList<credito>solicitudes;
	private ArrayList<credito> creditosApagar;
	private Hashtable condiciones;
	private ArrayList<credito>creditosYaPagados;
	
	public bancoHipotecario() {
		clientes=new ArrayList<cliente>();
		solicitudes=new ArrayList<credito>(); 
		creditosApagar=new ArrayList<credito>();
		condiciones=new Hashtable<String, Integer >();
		creditosYaPagados=new ArrayList<credito>();
	}

	
	
//                                SUELDOS PROMEDIO 
	public void SueldoPromedioCompranCasa() {
		int total=0;
		float sueldoTotal=0, prom=0;
		for(credito c: creditosApagar) {
			total+=c.cantidadCompraCasa();
			sueldoTotal+=c.sueldoCliente();
		}
		if(total!=0 && sueldoTotal!=0) {
			prom=sueldoTotal/total;
			System.out.println("EL SUELDO PROMEDIO ENTRE LOS CLIENTES QUE COMPRAN CASA ES DE : "+ prom +" %");
		}
		else
			System.out.println("EL SUELDO PROMEDIO ENTRE LOS CLIENTES QUE COMPRAN CASA ES DE 0 % ");
	}
	
	public void SueldoPromedioRefaccion() {
		int total=0;
		float sueldoTotal=0, prom=0;
		for(credito c: creditosApagar) {
			total+=c.cantidadRefaccion();
			sueldoTotal+=c.sueldoCliente();
		}
		if(total!=0 && sueldoTotal!=0) {
			prom=sueldoTotal/total;
			System.out.println("EL SUELDO PROMEDIO ENTRE LOS CLIENTES QUE REFACCIONAN CASA ES DE : "+ prom +" %");
		}
		else
			System.out.println("EL SUELDO PROMEDIO ENTRE LOS CLIENTES QUE REFACCIONAN CASA ES DE 0 %");
	}
	
	
//            MODIFICACION DE PORCENTAJE Y CANTIDAD
	
	public void ModificarPorcentaje() {
		Scanner in=new Scanner(System.in);
		System.out.println("INGRESE EL NUEVO PORCENTAJE COMO CONDICION DE LOS CREDITOS DE COMPRA CASA :");
		int p=in.nextInt();
		for(credito c : creditosApagar) {
			c.ponetePorc(p);
		}
		for(credito c : solicitudes) {
			c.ponetePorc(p);
		}
	}
	
	public void ModificarMultiploSueldo() {
		Scanner in=new Scanner(System.in);
		System.out.println("INGRESAR NUEVO MULTIPLO COMO CONDICION EN EL SUELDO DE LOS CREDITOS DE REFACCION :");
		int m=in.nextInt();
		for(credito c : creditosApagar) {
			c.poneteMult(m);
		}
		for(credito c : solicitudes) {
			c.poneteMult(m);
		}
	}

	
//                                    GESTION DE CUOTAS A PAGAR
	public void PagoCuota() {
		cliente c= buscarClienteV();
		if(c==null) {
			System.out.println("NO SE ENCUENTA EL CLIENTE REGISTRADO EN EL SISTEMA. ");
		}else {
			credito unc=this.buscarCreditoApagar(c);
			if(unc==null) {
				System.out.println("EL CLIENTE NO TIENE REGISTRADO NINGUN CREDITO A PAGAR ");
			}else {
				unc.darDeBajaCuota();
			}
		}
	}
	public void ConsultarCuotasPagadas() {
		cliente c= buscarClienteV();
		if(c==null) {
			System.out.println("NO SE ENCUENTA EL CLIENTE REGISTRADO EN EL SISTEMA. ");
		}else {
			credito unc=this.buscarCreditoApagar(c);
			if(unc==null) {
				System.out.println("EL CLIENTE NO TIENE REGISTRADO NINGUN CREDITO A PAGAR ");
			}else { 
				unc.cuotasPagadas();
			}
		}
	}
	public void ConsultarCuotasFaltantes() {
		cliente c= buscarClienteV();
		if(c==null) {
			System.out.println("NO SE ENCUENTA EL CLIENTE REGISTRADO EN EL SISTEMA. ");
		}else {
			credito unc=this.buscarCreditoApagar(c);
			if(unc==null) {
				System.out.println("EL CLIENTE NO TIENE REGISTRADO NINGUN CREDITO A PAGAR ");
			}else {
				unc.cuotasQueFaltan();
			}
		}
	}

	
//									REALIZACION "GESTION DE SOLICITUDES"
	public void EvaluarSolicitud() {
		cliente c= buscarClienteV();
		if(c==null)
			System.out.println("NO SE ENCUENTRA EL CLIENTE REGISTRADO EN EL SISTEMA.");
		else {
			credito unc=this.buscarCredito(c);
			if(unc==null)
				System.out.println("EL CLIENTE NO POSEE REGISTRADA UNA SOLICITUD DE CREDITO .");
			else {
				if(c.existeCredito()==true)
					System.out.println("NO SE PUEDE OTORGAR EL PRESTAMO DEBIDO A QUE EN ESTE MOMENTO HAY OTRO PAGANDO");
				else {
					if(!(this.EvaluarAntecedentes(c))) {
						this.motivoAntecedentes(c);
						c.eliminarSolicitud(unc);
						solicitudes.remove(unc);
					}else {
						if(!(unc.Supera())) {
							unc.MotivoSupera();
							c.eliminarSolicitud(unc);
							solicitudes.remove(unc);
						}else {
							unc.PoneteFechaOtrogada();
							unc.generarHashTable();
							creditosApagar.add(unc);
							System.out.println("LA SOLICITUD PARA EL PRESTAMO HA SIDO OTORGADA");
						}
					}
				}	
			 }
		}
	}
	public void EvaluarSolicitud(cliente c,credito unc) {
				if(c.existeCredito()==true) //IGUAL NO DEBE SUCEDER NUNCA YA QUE ANTERIORMENTE PARA REALIZAR UNA SOLICITUD EVALUA QUE NO HAYA UN CREDITO U OTRA SOLICITUD REGISTRADA EN EL CLIENTE
					System.out.println("NO SE PUEDE OTORGAR EL PRESTAMO DEBIDO A QUE EN ESTE MOMENTO HAY OTRO PAGANDO");
				else {
					if(!(this.EvaluarAntecedentes(c))) {
						this.motivoAntecedentes(c);
						c.eliminarSolicitud(unc);
						solicitudes.remove(unc);
					}else {
						if(!(unc.Supera())) {
							unc.MotivoSupera();
							c.eliminarSolicitud(unc);
							solicitudes.remove(unc);
						}else {
							unc.PoneteFechaOtrogada();
							unc.generarHashTable();
							creditosApagar.add(unc);
							solicitudes.remove(unc);
							System.out.println("LA SOLICITUD PARA EL PRESTAMO HA SIDO OTORGADA");
						}
					}
				}	
	}
	public void listarSolicitudes() {
		if(solicitudes.size()==0) {
			System.out.println("EL SISTEMA NO TIENE REGISTRADO SOLICITUDES" );
		}else {
			int i=0;
			while(i<solicitudes.size()) {
				((credito) solicitudes.get(i)).MostrarClienteDelCredito();
				((credito)solicitudes.get(i)).MostrateCredito();
				i++;
			}
		}
	}
	public void listarSolicitudesCompraCasa() {
		if(solicitudes.size()==0) {
			System.out.println("EL SISTEMA NO TIENE REGISTRADO SOLICITUDES PARA COMPRA CASA" );
		}else {
			int i=0;
			while(i<solicitudes.size()) {
			 ((credito)solicitudes.get(i)).esCompraCasa();
			i++;
			}
		}
	}
	public void listarSolicitudesRefacciones() {
		if(solicitudes.size()==0) {
			System.out.println("EL SISTEMA NO TIENE REGISTRADO SOLICITUDES PARA REFACCION DE CASA" );
		}else {
			int i=0;
			while(i<solicitudes.size()) {
				((credito)solicitudes.get(i)).esRefaccion();
				i++;
			}
		}
	}
	
	public void darDeBajaSolicitud() {
		cliente unc=buscarClienteV();
		if(unc!=null) {
			credito cred=buscarCredito(unc);
			if(cred!=null) {
				solicitudes.remove(cred);
				System.out.println("LA SOLICITUD HA SIDO REMOVIDA CON EXITO");
			}else {
				System.out.println("EL CLIENTE NO POSEE REGISTRADA UNA SOLICITUD DE CREDITO .");
			}
		}else {
			System.out.println("EL CLIENTE NO ENCUENTRA REGISTRADO EN EL SISTEMA . ");
		}
	}
	public credito buscarCredito(cliente c) { //buscanSolicitudes //RETORNA EL VALOR
		int i=0;
		while(i<solicitudes.size() && !(((credito) solicitudes.get(i)).sosCredito(c)))
			i++;
		if(i==solicitudes.size())
			return (null);
		else
			return (credito) (solicitudes.get(i));
	}
	public void buscarSolicitudesListar() {		//buscaSolicitudes //MUESTRA
		cliente c= buscarClienteV();
		if(c==null)
			System.out.println("NO SE ENCUENTRA EL CLIENTE REGISTRADO EN EL SISTEMA.");
		else {
			credito unc=this.buscarCredito(c);
			if(unc==null)
				System.out.println("EL CLIENTE NO POSEE REGISTRADO UN CREDITO .");
			else {
				unc.MostrateCredito();
			}
		}
	}
	
	
		//              GESTION DE CREDITOS
	
	public void listarCreditosApagarRefaccionCasa() {
		if(creditosApagar.size()==0) {
			System.out.println("EL SISTEMA NO TIENE REGISTRADO CREDITOS A PAGAR PARA REFACCION DE CASA");
		}else {
			int i=0;
			while(i<creditosApagar.size()) {
				((credito)creditosApagar.get(i)).esRefaccion();
				i++;
			}
		}
		
	}
	public void listarCreditosApagarCompraCasa() {
		if(creditosApagar.size()==0) {
			System.out.println("EL SISTEMA NO TIENE REGISTRADO CREDITOS A PAGAR PARA COMPRA CASA" );
		}else {
		int i=0;
			while(i<creditosApagar.size()) {
			 ((credito)creditosApagar.get(i)).esCompraCasa();
			 i++;
			}
		}
	}
	public void darDeBajaCredito() {
		cliente unc=buscarClienteV();
		if(unc!=null) {
			credito cred=buscarCreditoApagar(unc);
			if(cred!=null) {
				if(cred.cuotasQueFaltanP()==0) {
					creditosYaPagados.add(cred);
					unc.eliminarCreditoApagar(cred);
					creditosApagar.remove(cred);
					System.out.println("EL CREDITO HA SIDO REMOVIDO CON EXITO");
				}else {
					System.out.println("EL CREDITO POSEE CUOTAS SIN PAGAR. PORFAVOR DE 'DE BAJA' AQUELLAS CUOTAS FALTANTES Y LUEGO FINALIZE EL CREDITO ");
					cred.cuotasQueFaltan();
				}
			}else {
				System.out.println("EL CLIENTE NO POSEE REGISTRADA UNA SOLICITUD DE CREDITO .");
			}
		}else {
			System.out.println("EL CLIENTE NO ENCUENTRA REGISTRADO EN EL SISTEMA . ");
		}
	}
	
	public void buscarCreditoListar() {		//buscaCreditosApagar //MUESTRA
		cliente c= buscarClienteV();
		if(c==null)
			System.out.println("NO SE ENCUENTRA EL CLIENTE REGISTRADO EN EL SISTEMA.");
		else {
			credito unc=this.buscarCreditoApagar(c);
			if(unc==null)
				System.out.println("EL CLIENTE NO POSEE REGISTRADO UN CREDITO .");
			else {
				unc.MostrateCredito();
			}
		}
	}
	
	public credito buscarCreditoApagar(cliente c) { //busca cred a pagar //RETORNA EL VALOR
		int i=0;
		while(i<creditosApagar.size() && !(((credito) creditosApagar.get(i)).sosCredito(c)))
			i++;
		if(i==creditosApagar.size())
			return (null);
		else
			return (credito) (creditosApagar.get(i));
	}
	
	
	public void listarCreditosApagar() {
		if(creditosApagar.size()==0) {
			System.out.println("EL SISTEMA NO TIENE REGISTRADO CREDITOS A PAGAR " );
		}else {
			int i=0;
			while(i<creditosApagar.size()) {
				((credito) creditosApagar.get(i)).MostrarClienteDelCredito();
			 ((credito)creditosApagar.get(i)).MostrateCredito();
			i++;
			}
		}
	}
	

	
	public void listarCreditosRealizados() {
		if(creditosApagar.size()==0) {
			System.out.println("EL SISTEMA NO TIENE REGISTRADO CREDITOS REALIZADOS" );
		}else {
			int i=0;
			while(i<creditosYaPagados.size()) {
				((credito) creditosYaPagados.get(i)).MostrarClienteDelCredito();
				((credito)creditosYaPagados.get(i)).MostrateCredito();
				i++;
				}
		}
	}
	public void EvaluarCuotaMensualPromedio(){
		int i=0;
		float cuotaMensual=0;
		while(i<creditosApagar.size()) {
			cuotaMensual=cuotaMensual+ ((credito) creditosApagar.get(i)).getValorCuota();
			i++;
		}
		cuotaMensual=(cuotaMensual/(i+1));
		System.out.println(" LA CUOTA MENSUAL PROMEDIO ENTRE TODOS LOS CREDITOS A PAGAR REGISTRADOS EN EL SISTEMA ES DE : "+ cuotaMensual);
	}
	
	

	
	
//                              GESTION DE CLIENTES  
	
	public void ListarCreditosAnteriores() {
		cliente unc=buscarClienteV();
		if(unc!=null) {
			if(unc.tieneCreditosAnteriores()) {
				unc.listadoCreditoAnt();
			}else {
				System.out.println("EL CLIENTE "+ unc.getNombre()+" "+ unc.getApellido()+" NO POSEE CREDITOS ANTERIORES YA FINALIZADOS . ");
			}
		}else {
			System.out.println("EL CLIENTE NO ENCUENTRA REGISTRADO EN EL SISTEMA . ");
		}
	}
	public void AgregarCreditoAnterior() {
		System.out.println("PARA AGREGAR UN 'CREDITO ANTERIOR' EL CLIENTE YA DEBE ESTAR REGISTRADO EN EL SISTEMA.");
		System.out.println("LUEGO SE GENERA UNA SOLICIUD, SE OTORGA Y SE DARÁ DE BAJA COMO FINALIZADA");
		Scanner entrada=new Scanner(System.in);
		String op;
		do {
			System.out.println("¿ DESEA CONTINUAR ?(SI/NO)");
			op=entrada.next();
			op=op.toUpperCase();
		}while((!op.equals("SI"))&&(!op.equals("NO")));
		if(op.equals("SI")) {
			cliente unc=this.buscarClienteV();
			this.ingresoSolicitudPrestamo(unc);
		}	
	}

	
	public void modificarCliente() {
		cliente unc=this.buscarClienteV();
		if(unc!=null) {
			System.out.println("LOS ACTUALES");
			unc.MostrarSoloDatosBasicosClientes();
			Scanner entrada=new Scanner(System.in);
			int op;
			do {
			System.out.println(" INGRESE EL NUM DE LA OPCION QUE DESEA MODIFICAR ");
			System.out.println("1-DIRECCION");
			System.out.println("2-TELEFONO ");
			System.out.println("3-SUELDO");
			op=entrada.nextInt();
			}while(op<1 || op>4);
			switch(op) {
			case 1:
				String direccion;
				System.out.println("INGRESE LA NUEVA DIRECCION ");
				direccion=entrada.next();
				unc.setDireccion(direccion);
				System.out.println("EL CAMBIO SE REALIZO CON EXITO. ");break;
			case 2:
				float tel;
				System.out.println("INGRESE EL NUEVO TELEFONO ");
				tel=entrada.nextFloat();
				unc.setTelefono(tel);
				System.out.println("EL CAMBIO SE REALIZO CON EXITO. ");break;
			case 3:
				float sueldo;
				System.out.println("INGRESE EL NUEVO SUELDO ");
				sueldo=entrada.nextFloat();
				unc.setSueldo(sueldo);
				System.out.println("EL CAMBIO SE REALIZO CON EXITO. ");break;
			}
		}else {
			System.out.println("EL CLIENTE NO SE ENCUENTRA REGISTRADO EN EL SISTEMA .");
		}
	}
	public void ObtenerPuntajeTotal() {
		cliente unc=this.buscarClienteV();
		if(unc!=null) {
			int puntaje;
			puntaje=this.puntajeAntecedentes(unc);
			System.out.println(" EL PUNTAJE TOTAL DE ANTECEDENTES QUE POSEE EL CLIENTE "+ unc.getNombre()+" "+ unc.getApellido()+" ES DE :"+ puntaje+" PUNTOS .");
		}else {
			System.out.println("EL CLIENTE NO SE ENCUENTRA REGISTRADO EN EL SISTEMA .");
		}
	}
	public void SolicitudCredito() {
		cliente unc=buscarClienteV();
		Scanner entrada=new Scanner(System.in);
		String op;
		if(unc!=null) {
			if(unc.getCreditoOS()==null) {
				this.ingresoSolicitudPrestamo(unc);
			}else {
				System.out.println("NO SE PUEDE REALIZAR LA SOLICITUD YA QUE EL CLIENTE TIENE REGISTRADO UNA SOLICITUD / CREDITO ");
			}
		}else {
			System.out.println("EL CLIENTE NO ESTA REGISTRADO EN EL SISTEMA. POR EL CUAL NO ES POSIBLE REALIZAR LA SOLICITUD DE CREDITO .");
			do {
				System.out.println("¿ DESEA AGREGARLO ? (SI/NO) \n");
				op=entrada.next();
				op=op.toUpperCase();
			}while((!op.equals("SI"))&&(!op.equals("NO")));
			if(op.equals("SI")) {
				int dni;
				System.out.println("INGRESE NUEVAMENTE SU DNI : ");
				dni=entrada.nextInt();
				this.agregarCliente(dni);
			}
			
		}	
	}
	public int puntajeAntecedentes(cliente c) {
		int i, puntaje=0;
		ArrayList<String> antecedentes=new ArrayList<String>();
		antecedentes=(ArrayList<String>) c.getAntecedentes().clone();
		String d;
		for(i=0;i<antecedentes.size();i++) {
			d=antecedentes.get(i);
			if((condiciones.get(d))!=null) 
				puntaje=puntaje+ (int)(condiciones.get(d) );
		}
		return (puntaje);
	}
	public void clienteMayorPuntaje() {
		int i,mayor=0;
		cliente cmax=null;
		for(cliente c : clientes) {
			if(this.puntajeAntecedentes(c)>mayor) {
				mayor=this.puntajeAntecedentes(c);
				cmax=c;
			}
		}
		if(cmax!=null) {
			System.out.println("EL MAYOR PUNTAJE ES DE= "+mayor);
			System.out.println("Y EL CLIENTE ES=");
			cmax.MostrarCliente();
		}
		
	}
	public void agregarCliente(int d) {
			//INGRESO DATOS BASICOS 
		Scanner entrada=new Scanner(System.in);
		System.out.println(" NOMBRE DEL CLIENTE : ");
		String nombre=entrada.next();
		System.out.println(" APELLIDO DEL CLIENTE : ");
		String apellido=entrada.next();
		System.out.println(" INGRESE EL DOMICILIO (DESCRIPCION) :");
		String domicilio=entrada.next();
		System.out.println(" INGRESE UN NUM DE TELEFONO : ");
		float telefono=entrada.nextFloat();
		System.out.println(" INGRESE EL SUELDO DEL CLIENTE :  ");
		float sueldo=entrada.nextFloat();
		cliente c=new cliente(nombre,apellido,d,telefono,domicilio,sueldo);
		clientes.add(c);
		int opcion;
			//INGRESO ANTECEDENTES
		System.out.println(" INGRESO DE ANTECEDENTES DEL CLIENTE \n");
		do {
			System.out.println(" ESCOJA UNA OPCION : ");
			System.out.println("1-AGREGAR NUEVO ANTECEDENTE");
			System.out.println("0-FINALIZAR ");
			opcion=entrada.nextInt();
			if(opcion==1) 
				this.ingresoDeAntecedentes(c);
		}while(opcion!=0);
			//INGRESO DE SOLICITUD DE PRESTAMO 
		this.ingresoSolicitudPrestamo(c);
			
	
	}
	public void ingresoSolicitudPrestamo(cliente c) {
		Scanner entrada=new Scanner(System.in);
		int opcion;
		do {
			System.out.println("INGRESO EL TIPO DE CREDITO A SOLICITAR : ");
			System.out.println("1-CREDITO PARA COMPRAR CASA");
			System.out.println("2-CREDITO PARA REFACCION DE CASA ");
			opcion=entrada.nextInt();
		}while(opcion!=1 && opcion!=2);
				
		float montoPedido,valorCuota;
		int plazo;
		System.out.println("INGRESE EL MONTO PEDIDO ");
		montoPedido=entrada.nextFloat();
		System.out.println("INGRESE EL PLAZO DEL CREDITO (CANTIDAD DE MESES A PAGAR DICHO MONTO) ");
		plazo=entrada.nextInt(); 
		valorCuota=this.realizarValorCuota(montoPedido,plazo);
		System.out.println(" A PARTIR DE UN MONTO SOLICITADO DE '"+montoPedido+"' Y UN PLAZO DE "+ plazo+ " MESES A PAGAR." + 
		" EL VALOR DE CADA CUOTA SERÁ DE  '"+ valorCuota+" ' PESOS \n " );
		Fecha f=ingresoDeFecha();
		SolicitarCredito(opcion, c, montoPedido, valorCuota, plazo, f);
	}
		
	public void buscarCliente() {
		Scanner entrada=new Scanner(System.in);
		int i=0;
		System.out.println(" INGRESO DE DATOS DEL CLIENTE \n ");
		System.out.println(" INGRESE EL DNI DEL CLIENTE : ");
		int d=entrada.nextInt();
		while((i<clientes.size()) && !(((cliente) clientes.get(i)).sos(d)))
			i++;
		if(i==clientes.size()) {
			System.out.println(" EL CLIENTE NO ESTA REGISTRADO EN EL SISTEMA ");
			String op;
			do {
				System.out.println("¿ DESEA AGREGARLO ?(SI/NO)");
				op=entrada.next();
				op=op.toUpperCase();
			}while((!op.equals("SI"))&&(!op.equals("NO")));
			if(op.equals("SI")) {
				this.agregarCliente(d);
			}
		}else {
			System.out.println(" EL CLIENTE ESTA REGISTRADO EN EL SISTEMA ");
			String op;
				do {
					System.out.println("¿ DESEA VER SUS DATOS ?(SI/NO)");
					op=entrada.next();
					op=op.toUpperCase();
				}while((!op.equals("SI"))&&(!op.equals("NO")));
			if(op.equals("SI")) {
				cliente unc=(cliente) clientes.get(i);
				unc.MostrarCliente();}
		}
	}
	public cliente buscarClienteV() {
		Scanner entrada=new Scanner(System.in);
		int i=0;
		System.out.println(" INGRESE EL DNI DEL CLIENTE : ");
		int d=entrada.nextInt();
		while((i<clientes.size()) && !(((cliente) clientes.get(i)).sos(d)))
			i++;
		if(i==clientes.size()) {
			return null;
		}else {
		return (cliente) clientes.get(i);	}   
	}
	
	public void darDeBajaCliente() {
		cliente unc=this.buscarClienteV();
		if(unc==null) {
			System.out.println(" EL CLIENTE NO ESTA REGISTRADO EN EL SISTEMA .");
		}else {
			System.out.println(" EL CLIENTE "+ unc.getNombre() +" "+ unc.getApellido() );
			System.out.println(" CON DNI "+ unc.getDni() );
			System.out.println("\n");
			if(!(unc.getCreditoOS()==null)) {
				System.out.println(" NO ES POSIBLE DARLO DE BAJA YA QUE POSEE UN CREDITO A PAGAR A SU NOMBRE ");
				System.out.println(" PRIMERO FINALIZE EL CREDITO A PAGAR POR EL CLIENTE Y LUEGO REALIZE LA BAJA AL CLIENTE ");
			}else {
			Scanner entrada=new Scanner(System.in);
			String op;
				do {
					System.out.println("RECUERDE QUE AL DARSE DE BAJA UN CLIENTE, POSTERIORMENTE SOLO SE TENDRA ACCESO A SU DNI EN LOS CREDITOS YA COMPLETADOS DE LA EMPRESA ");
					System.out.println("¿ ESTA SEGURO QUE DESEA ELIMINARLO DEL REGISTRO DE CLIENTES ?(SI/NO)");
					op=entrada.next();
					op=op.toUpperCase();
				}while((!op.equals("SI"))&&(!op.equals("NO")));
				if(op.equals("SI")) {
					clientes.remove(unc);
				}
			}
		}
	}
	public void informarAntecedentes() {
		cliente unc=this.buscarClienteV();
		if(unc!=null) {
			if(unc.tieneAntecedentes()){
				unc.listadoAntecedentes();
			}else {
				System.out.println("EL CLIENTE NO POSEE ANTECEDENTES. ");
			}
		}else {
			System.out.println(" NO SE ENCUENTRA REGISTRADO EL CLIENTE EN EL SISTEMA.");
		}
	}
	public void ingresoDeAntecedentes() {
		cliente unc=this.buscarClienteV();
		if(unc!=null) {
				this.ingresoDeAntecedentes(unc);
		}else {
			System.out.println(" NO SE ENCUENTRA REGISTRADO EL CLIENTE EN EL SISTEMA.");
		}		
}
	public void ingresoDeAntecedentes(cliente unc) {
		Scanner entrada=new Scanner(System.in);
		System.out.println(" LAS CONDICIONES ACEPTABLES POSIBLES COMO ANTECEDENTES PARA EL CLIENTE SON : ");
		this.ListarCondiciones();
		System.out.println(" ********************************* ");
		System.out.println(" INGRESE LA DESCRIPCION DE LA CONDICION REQUERIDA : ");
		String condi=entrada.next();
		condi=condi.toUpperCase();
		if(validarDescripcion(condi)){
			unc.agregarAntecedente(condi);
		}else {
			System.out.println(" LA DESCRIPCION ' "+condi+" ' NO ES VALIDA. VUELVA A SOLICITAR EL INGRESO DE CONDICION NUEVAMENTE. ");
		}
	}	
	public void DardeBajaAntecedente() {
		cliente unc=this.buscarClienteV();
		if(unc!=null) {
			
			if(unc.tieneAntecedentes()) {
				Scanner entrada=new Scanner(System.in);
				String descripcion;
				do {
				unc.listadoAntecedentes();
				System.out.println(" INGRESE LA DESCRIPCION DEL ANTECEDENTE A ELIMINAR ");
				descripcion=entrada.next();
				descripcion=descripcion.toUpperCase();
				}while(!unc.validarExistenciaAntecendente(descripcion));
				unc.darDeBajaAntecedente(descripcion);
			}else {
				System.out.println("EL CLIENTE NO POSEE ANTECEDENTE ALGUNO ");
			}
		}else {
			System.out.println(" NO SE ENCUENTRA REGISTRADO EL CLIENTE EN EL SISTEMA.");
		}
		
	}
	public boolean EvaluarAntecedentes(cliente c) {
		if(c.CantAntecedentes()>=3 && this.puntajeAntecedentes(c)>100)
			return (true);
		else 
			return (false);
		
		
	}
	public void motivoAntecedentes(cliente c) {
		System.out.println("EL CREDITO NO SERÁ OTORGADO YA QUE ");
		if(c.CantAntecedentes()<3)
			System.out.println(" EL CLIENTE TIENE MENOS DE 3 ANTECEDENTES REGISTRADOS. ");
		else {
			if(this.puntajeAntecedentes(c)<=100)
				System.out.println("EL CLIENTE TIENE MENOS DE 100 PUNTOS EN LA SUMA DE PUNTAJES EN SUS ANTECEDENTES");	
		}
		
	}
	
	public void listarClientes() {
		if(clientes.size()==0) {
			System.out.println("EL SISTEMA NO TIENE CARGADO NINGUN CLIENTE.");	
		}else {
			int i=0;
			while(i<clientes.size()) {
				System.out.println(i);
				((cliente) clientes.get(i)).MostrarSoloDatosBasicosClientes();
				i++;
			}
			System.out.println("\n");
			System.out.println(" ( RECUERDE QUE SI DESEA ACCEDER A GESTION DE CREDITOS Y SOLICITUDES DE ELLOS. DEBE BUSCARLOS INDIVIDUALMENTE ) ");	
			
		}
	}

	
	public Fecha ingresoDeFecha() {
		Scanner entrada=new Scanner(System.in);
		boolean val=false;
		Fecha f=new Fecha();
		do {
			System.out.println("\n INGRESAR FECHA ACTUAL DE LA SOLICITUD");
			System.out.println(" DIA = ");
			int dia=entrada.nextInt();
			System.out.println(" MES = ");
			int mes=entrada.nextInt();
			System.out.println(" AÑO = ");
			int a=entrada.nextInt();
			val=f.fechaCorrecta(dia, mes, a);
			if(val) {
				f=new Fecha(dia, mes, a);
				val=true;
			}else {
			System.out.println(" FECHA INGRESADA NO ES CORRECTA .");
			}
		}while(val==false);
		return f;
	}
	
	public void SolicitarCredito(int opcion,cliente unc,float montoPedido,float valorCuota,int plazo,Fecha f) {
		//NO SE PORQUE NO ME PERMITE PONER LA PREGUNTA FUERA DEL IF, TENGO QUE ESCRIBIRLO EN LAS DOS OPCIONES
		Scanner entrada=new Scanner(System.in);
		String op;
		if (opcion==1) {
			credito uncredito=new compraCasa(unc,montoPedido,valorCuota,plazo,f);
			solicitudes.add(uncredito);
			unc.agregarCredito(uncredito);
			System.out.println(" LA SOLICITUD SE HA GENERADO EXITOSAMENTE . ");
			System.out.println(" LOS DATOS REGISTRADOS EN EL SISTEMA SON : ");
			unc.MostrarCliente();
			do {
				System.out.println(" ¿DESEA VALIDAR EL OTORGAMIENTO DEL CREDITO EN ESTE MOMENTO ? (SI /NO)");
				op=entrada.next();
				op=op.toUpperCase();
			}while((!op.equals("SI"))&&(!op.equals("NO")));
			if(op.equals("SI")) {
				this.EvaluarSolicitud(unc,uncredito);
			}else {
				System.out.println("EL CREDITO SE ENCUENTRA REGISTRADO EN LAS SOLICITUDES PARA SER OTORGADO .");
			}		
		}else {
			credito uncredito=new cRefaccion(unc,montoPedido,valorCuota,plazo,f);
			solicitudes.add(uncredito);
			unc.agregarCredito(uncredito);
			System.out.println(" LA SOLICITUD SE HA GENERADO EXITOSAMENTE . ");
			System.out.println(" LOS DATOS REGISTRADOS EN EL SISTEMA SON : ");
			unc.MostrarCliente();
			
			do {
				System.out.println(" ¿DESEA VALIDAR EL OTORGAMIENTO DEL CREDITO EN ESTE MOMENTO ?");
				op=entrada.next();
				op=op.toUpperCase();
			}while((!op.equals("SI"))&&(!op.equals("NO")));
			if(op.equals("SI")) {
				this.EvaluarSolicitud(unc,uncredito);
			}else {
				System.out.println("EL CREDITO SE ENCUENTRA REGISTRADO EN LAS SOLICITUDES PARA SER OTORGADO .");
			}
		}
		
	}
	public float realizarValorCuota(float montoPedido, int plazo) {
		return (montoPedido/plazo) ;
	}

	
	
//                 				 GESTION DE CONDICIONES  
	public void AgregarCondicion() {
		String descripcion=this.ingresoDeCondicion();
		if(!validarDescripcion(descripcion)) {
			int puntaje;
			Scanner entrada=new Scanner(System.in);
			System.out.println(" 2-INGRESE SU PUNTAJE CORRESPONDIENTE : ");
			puntaje=entrada.nextInt();
			condiciones.put(descripcion, puntaje);
			System.out.println(" LA CONDICION : ' "+	descripcion + " ' \n CON UN PUNTAJE : "+puntaje+" PUNTOS.  \n FUE AÑADIDO EXITOSAMENTE. " );
			}else {
			System.out.println(" LA CONDICION "+descripcion+" YA FUE AÑADIDA CON ANTERIORIDAD.");
		}
	}
	public String ingresoDeCondicion() {
		Scanner entrada=new Scanner(System.in);
		String descripcion;
		//VALIDAR INGRESO DE STRING 
		System.out.println(" 1- INGRESE LA DESCRIPCION DE LA CONDICION : ");
		descripcion=entrada.next();
		descripcion=descripcion.toUpperCase();
		return descripcion;
	}
	
	public boolean validarDescripcion(String descripcion) {
		if(condiciones.containsKey(descripcion))
			return true; 
		else
			return false; 
	}
	
	public void BuscarCondicion() {
		String descripcion= this.ingresoDeCondicion();
		if(condiciones.containsKey(descripcion)) {
			System.out.println(" LA CONDICION "+ descripcion + " ESTA REGISTRADA EN EL SISTEMA CON UN PUNTAJE DE "+ condiciones.get(descripcion)+ " PUNTOS."); 
		}else{
			System.out.println(" LA CONDICION "+descripcion+ " NO ESTA REGISTRADA EN EL SISTEMA .");}
	}
	
	public void DardeBajaCondicion() {
		String descripcion= this.ingresoDeCondicion();
		if(validarDescripcion(descripcion)) {
			condiciones.remove(descripcion);
			int i=0;
			cliente unc;
			while(i<clientes.size()) {
				unc=(cliente)clientes.get(i);
				unc.darDeBajaAntecedente(descripcion);
			}
			System.out.println(" LA CONDICION  "+descripcion+"  SE HA ELIMINADO EXITOSAMENTE .");
			System.out.println(" ( Recuerde que el dar de baja implica modificaciones en los antecedentes de los clientes con dicha condición .) ");
		}else {
			System.out.println(" LA CONDICION "+descripcion+ " NO ESTA REGISTRADA EN EL SISTEMA .");
		}
		
	}
	public void ListarCondiciones() {
		Enumeration e=condiciones.keys();
		Object clave;
		Object valor;
		int i=0;
		while(e.hasMoreElements()) {
			clave=e.nextElement();
			valor=condiciones.get(clave);
			System.out.println(""+ i +"- DESCRIPCION : "+clave +"- CON UN PUNTAJE DE : "+valor);
			i++;
		}
	}

}
