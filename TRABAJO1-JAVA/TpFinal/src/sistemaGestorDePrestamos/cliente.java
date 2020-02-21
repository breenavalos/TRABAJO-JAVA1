package sistemaGestorDePrestamos;
import java.util.*;

public class cliente {
	private String nombre;
	private String apellido;
	private int dni;
	private String direccion;
	private float sueldo;
	private float numeroTel;
	private ArrayList<credito>creditosAnteriores;
	private ArrayList<String>antecedentes;
	private credito creditoOS; //ES EL CREDITO SOLICITADO QUE ES EL MISMO AUNQUE HAYA SIDO OTORGADO. LO QUE SE DIFERENCIAN EN SI ESTA OTORGADO O NO. SON LAS FECHAS. EL OTORGADO SE DA = 0 SI ES UNA SOLICITUD.
	
	
	public cliente(String nombre,String apellido,int dni,float numeroTel,String direccion,float sueldo) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni=dni;
		this.numeroTel=numeroTel;
		this.direccion=direccion;
		this.sueldo=sueldo;
		creditosAnteriores=new ArrayList<credito>();
		antecedentes=new ArrayList<String>();
	}
	
	public void agregarCredito(credito solicitudAotorgar) {
		this.creditoOS=solicitudAotorgar;
	}
	public credito eliminarSolicitud(credito cred) {
		return creditoOS=null;
	}
	public void eliminarCreditoApagar(credito cred) {
		creditosAnteriores.add(cred);
		this.eliminarSolicitud(cred);
	}
	
	public boolean sos(int dni) {
		return this.dni==dni;
	}

	public void agregarAntecedente(String unantecedente) {
		if(!validarExistenciaAntecendente(unantecedente)) {
			this.antecedentes.add(unantecedente);
			System.out.println("EL ANTECEDENTE "+ unantecedente+" FUE AÑADIDO EXITOSAMENTE . \n");
		}else {
			System.out.println(" EL CLIENTE YA TIENE REGISTRADO COMO ANTECEDENTE LA CONDICION : " + unantecedente );
		}
	}
	
	public boolean validarExistenciaAntecendente(String unantecedente) {
		int i=0;
		while((i<antecedentes.size()) && !(unantecedente.compareTo(antecedentes.get(i)) == 0)) {
			i++;
		}
	
		if(i==antecedentes.size()) {
			return false;
		}else {
			return true;}
	}

	public void darDeBajaAntecedente(String unantecedente) {
		int i=0;
		while((i<antecedentes.size()) && !(unantecedente.compareTo(antecedentes.get(i)) == 0)) {
			i++;}
		if(unantecedente.compareTo(antecedentes.get(i)) == 0) {
			antecedentes.remove(i);
			System.out.println("EL ANTECEDENTE "+ unantecedente +" SE ELIMINO EXITOSAMENTE .");
		}
	}
	
	public boolean tieneAntecedentes() {
		if(!(antecedentes.isEmpty()))
			return (true);
		else
			return (false);
	}
	public void MostrarSoloDatosBasicosClientes() {
		System.out.println(" DATOS DEL CLIENTE : \n ");
		System.out.println(" NOMBRE : "+nombre);
		System.out.println(" APELLIDO : "+apellido);
		System.out.println(" DNI : "+dni);
		System.out.println(" DIRECCION : "+direccion);
		System.out.println(" NUMERO DE TELEFONO :"+numeroTel);
		System.out.println(" SUELDO  :  "+sueldo);
		System.out.println("\n");
		
	}
	
	public void MostrarCliente() {
		System.out.println(" DATOS DEL CLIENTE \n ");
		System.out.println(" NOMBRE : "+nombre);
		System.out.println(" APELLIDO : "+apellido);
		System.out.println(" DNI : "+dni);
		System.out.println(" DIRECCION : "+direccion);
		System.out.println(" SUELDO  :  "+sueldo);
		System.out.println("\n");
		
		if(creditosAnteriores.isEmpty()) {
			System.out.println(" NO POSEE CREDITOS ANTERIORES . ");
		}else {
			this.listadoCreditoAnt();}
		System.out.println("\n");
		if(antecedentes.isEmpty()) {
			System.out.println("  NO POSEE ANTECEDENTES . ");
		}else {
			this.listadoAntecedentes();}
		System.out.println("\n");
		if(this.creditoOS==null) {
			System.out.println("NO POSEE CREDITO A PAGAR NI SOLICITUD A OTORGAR"); 
		}else {
			System.out.println(" DATOS DEL CREDITO : ");
			creditoOS.MostrateCredito();}
	}
	
	public void listadoCreditoAnt() {
		System.out.println(" LISTADO DE CREDITOS ANTERIORES : ");
			for(credito c : creditosAnteriores) {
				c.MostrateCredito();
			}
		
	}
	public boolean tieneCreditosAnteriores() {
		if(!(creditosAnteriores.isEmpty()))
			return (true);
		else
			return (false);
	}
	
	public String darAntecedentes(int i) {
		if(i<antecedentes.size()) {
			return this.antecedentes.get(i);
		}else {
			return null;}
	}
	
	
	public void listadoAntecedentes() {
			System.out.println("LISTADO DE ANTECEDENTES DEL CLIENTE :");
			int i;
			for(i=0;i<antecedentes.size();i++) {
				System.out.println( "-"+antecedentes.get(i));}
	}
	
	
	public ArrayList<String> getAntecedentes() {
		return (antecedentes);
	}
	
	public int CantAntecedentes() {
		return (antecedentes.size());
	}
	
	/*public void poneteCredito() { //VA CUANDO LO OTORGAN
		creditoOS.PoneteFechaOtrogada();
	}*/
	public boolean existeCredito() { //VERIFICAR FECHA OTROGADA OSEA QUE YA SEA UN CREDITO A PAGAR o TIRA NULL EN EL CASO DE QUE EL CLIENTE NI HAYA HECHO UNA SOLICITUD
		return creditoOS.FueOtorgada();
	}

					//GETERS AND SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDni() {
		return dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public float getSueldo() {
		return sueldo;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	public String getApellido() {
		return this.apellido;
	}
	public credito getCreditoOS() {
		return this.creditoOS;
	}
	public float setTelefono(float telefono) {
		return this.numeroTel=telefono;
	}
	public float getTelefono() {
		return this.numeroTel;
	}

}
