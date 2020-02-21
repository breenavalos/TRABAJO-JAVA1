package sistemaGestorDePrestamos;
import java.util.Scanner;
public class ejecutora {

		public static void main(String args[]) {
			Scanner entrada=new Scanner(System.in);
			bancoHipotecario unMovimiento=new bancoHipotecario();
			int opcion,opcion2;
			do {
				System.out.println(" BANCO HIPOTECARIO \n");
				System.out.println("MENU PRINCIPAL DE OPCIONES :  ");
				System.out.println("1) GESTION DE CONDICIONES. "); 
				System.out.println("2) GESTION DE CLIENTES. ");
				System.out.println("3) GESTION DE CREDITOS DADOS. ");
				System.out.println("4) GESTION DE PAGO DE CUOTAS ");
				System.out.println("5) GESTION DE SOLICITUDES PARA CREDITOS A OTORGAR ");
				System.out.println("6) MODIFICAR PORCENTAJE DE SUELDO PARA CREDITOS DE COMPRA CASA ");
				System.out.println("7) MODIFICAR LA CANTIDAD DE SUELDO PERMITIDO PARA CREDITOS DE REFACCION \n");
				System.out.println("0) SALIR.");
				opcion=entrada.nextInt();
				switch(opcion) {
					case 1:
						do { 
						System.out.println(" OPCION 1 : GESTION DE CONDICIONES \n");
	/* PUNTO 1: */		System.out.println(" 2- AGREGAR CONDICION . "); 
						System.out.println(" 3- BUSCAR CONDICION  ");                    
						System.out.println(" 4- DAR DE BAJA CONDICION "); 
						System.out.println(" 5- LISTAR CONDICIONES \n");  
						System.out.println(" 6- VOLVER A MENU PRINCIPAL DE OPCIONES. \n");
						opcion2=entrada.nextInt();
						}while(opcion2<2 || opcion2>6); 
						switch(opcion2) {
							case 2:unMovimiento.AgregarCondicion() ;break;
							case 3:unMovimiento.BuscarCondicion();break;
							case 4:unMovimiento.DardeBajaCondicion();break;
							case 5:unMovimiento.ListarCondiciones();break;
						}
					break;
					case 2:
						do {
							System.out.println(" OPCION 2 : GESTION DE CLIENTES \n");
							System.out.println(" 3-AGREGAR CLIENTE. "); 
							System.out.println(" 4-BUSCAR CLIENTE. ");
							System.out.println(" 5-DAR DE BAJA CLIENTE. ");
							System.out.println(" 6-LISTAR CLIENTES. ");
							System.out.println(" 7-INFORMAR ANTECEDENTES DE UN CLIENTE. "); 
		/*PUNTO 3 : */		System.out.println(" 8-AGREGAR ANTECEDENTES AL CLIENTE "); 
							System.out.println(" 9-DAR DE BAJA ANTECEDENTE DEL CLIENTE ");
		/*PUNTO 6 : */		System.out.println(" 10-EVALUAR SUELDO PROMEDIO ENTRE LOS CLIENTES QUE COMPRAN CASA. "); 
							System.out.println(" 11-EVALUAR SUELDO PROMEDIO ENTRE LOS CLIENTES QUE REFACCIONAN. "); 
		/*PUNTO 7 : */		System.out.println(" 12-EVALUAR CUOTA MENSUAL PROMEDIO ENTRE TODOS LOS CLIENTES. ");
							System.out.println(" 13-LISTAR CREDITOS ANTERIORES REALIZADOS POR UN CLIENTE ");
							System.out.println(" 14-AGREGAR UN CREDITO ANTERIOR REALIZADO POR UN CLIENTE ");
							System.out.println(" 15-MODIFICAR CLIENTE (DIRECCION/TELEFONO/SUELDO) ");
							System.out.println(" 16-OBTENER PUNTAJE TOTAL A PARTIR DE LOS ANTECEDENTES DEL CLIENTE  ");
							System.out.println(" 17-OBTENER PUNTAJE MAYOR DE ANTECEDENTES ENTRE TODOS LOS CLIENTES \n");
							System.out.println(" 18-VOLVER AL MENU PRINCIPAL DE OPCIONES \n");
							opcion2=entrada.nextInt();
						}while(opcion2<3 || opcion2>18);
						switch(opcion2) {
								case 3:unMovimiento.buscarCliente();break;
								case 4:unMovimiento.buscarCliente();break;
								case 5:unMovimiento.darDeBajaCliente();break;
								case 6:unMovimiento.listarClientes();break;
								case 7:unMovimiento.informarAntecedentes();break;
								case 8:unMovimiento.ingresoDeAntecedentes();break;
								case 9:unMovimiento.DardeBajaAntecedente();break;
								case 10:unMovimiento.SueldoPromedioCompranCasa();break;
								case 11:unMovimiento.SueldoPromedioRefaccion();break;
								case 12:unMovimiento.EvaluarCuotaMensualPromedio();break;
								case 13:unMovimiento.ListarCreditosAnteriores();break;
								case 14:unMovimiento.AgregarCreditoAnterior();break;
								case 15:unMovimiento.modificarCliente();break;
								case 16:unMovimiento.ObtenerPuntajeTotal();break;
								case 17:unMovimiento.clienteMayorPuntaje();break;
						}
					break;
					case 3:
						do{
							System.out.println("OPCION 3 : GESTION DE CREDITOS \n");
							System.out.println(" 4-LISTAR TODOS LOS CREDITOS A PAGAR. ");
							System.out.println(" 5-LISTAR LOS CREDITOS A PAGAR PARA COMPRA CASA");
							System.out.println(" 6-LISTAR LOS CREDITOS A PAGAR PARA REFACCION DE CASA");
							System.out.println(" 7-LISTAR TODOS LOS CREDITOS REALIZADOS."); 
							System.out.println(" 8-BUSCAR CREDITO A PAGAR. ");
							System.out.println(" 9-DAR DE BAJA CREDITO. \n"); 
							System.out.println(" 10-VOLVER A MENU PRINCIPAL DE OPCIONES. \n");
							opcion2=entrada.nextInt();
						}while(opcion2<4 || opcion2>11);
						switch(opcion2) {
						case 4:unMovimiento.listarCreditosApagar();break;
						case 5:unMovimiento.listarCreditosApagarCompraCasa();break;
						case 6:unMovimiento.listarCreditosApagarRefaccionCasa();break;
						case 7:unMovimiento.listarCreditosRealizados();break;
						case 8:unMovimiento.buscarCreditoListar();break;
						case 9:unMovimiento.darDeBajaCredito();break;
						}
				    break;
					case 4:do{
						System.out.println("OPCION 4 : GESTION DE PAGO DE CUOTAS \n");
						System.out.println(" 5-PAGAR CUOTA ");
						System.out.println(" 6-CONSULTAR CANTIDAD DE CUOTAS PAGADAS");
						System.out.println(" 7-CONSULTAR CANTIDAD DE CUOTAS FALTANTES \n");
						System.out.println(" 8-VOLVER AL MENU PRINCIPAL DE OPCIONES . \n");
						opcion2=entrada.nextInt();
						}while(opcion2<5 || opcion2>8);
						switch(opcion2) {
						case 5:unMovimiento.PagoCuota();break;
						case 6:unMovimiento.ConsultarCuotasPagadas();break;
						case 7:unMovimiento.ConsultarCuotasFaltantes();break;
						}
				    break;
					case 5:
						do {
							System.out.println("OPCION 5: GESTION DE SOLICITUDES \n");
							System.out.println(" 6-PETICIÓN DE SOLICITUD DE CREDITO");
							System.out.println(" 7- EVALUAR OTORGAMIENTO DE CREDITO "); //PUNTO 4 
							System.out.println(" 8-DAR DE BAJA SOLICITUD ");
							System.out.println(" 9-LISTAR TODAS LAS SOLICITUDES ");
							System.out.println(" 10-LISTAR SOLICITUDES PARA COMPRA CASA");
							System.out.println(" 11-LISTAR SOLICITUDES PARA REFACCIONES ");
							System.out.println(" 12-BUSCAR SOLICITUD \n");
							System.out.println(" 13-VOLVER AL MENU PRINCIPAL DE OPCIONES. \n");
							opcion2=entrada.nextInt();
						}while(opcion2<6 || opcion2>13);
						switch(opcion2) {
						case 6:unMovimiento.SolicitudCredito();break;
						case 7:unMovimiento.EvaluarSolicitud();break;
						case 8:unMovimiento.darDeBajaSolicitud();break;
						case 9:unMovimiento.listarSolicitudes();break;	
						case 10:unMovimiento.listarSolicitudesCompraCasa();break;
						case 11:unMovimiento.listarSolicitudesRefacciones();break;
						case 12:unMovimiento.buscarSolicitudesListar();break;
						}
					break;
					case 6:
						unMovimiento.ModificarPorcentaje();break;
					case 7:
						unMovimiento.ModificarMultiploSueldo();break;			
				}
				System.out.println("\n ********************************************************************************************** \n");
			}while(opcion!=0);
		}
}
