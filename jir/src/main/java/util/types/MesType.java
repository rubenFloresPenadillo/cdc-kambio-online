package util.types;

public enum MesType {
	
	ENERO("Enero",1),
	FEBRERO("Febrero",2),
	MARZO("Marzo",3),
	ABRIL("Abril", 4),
	MAYO("Mayo",5),
	JUNIO("Junio",6),
	JULIO("Julio",7),
	AGOSTO("Agosto",8),
	SETIEMBRE("Setiembre",9),
	OCTUBRE("Octubre",10),
	NOVIEMBRE("Noviembre",11),
	DICIEMBRE("Diciembre",12);
	
	private String nombre;
	private Integer valor;

	private MesType(String nombre, Integer valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Integer getValor() {
		return valor;
	}

}
