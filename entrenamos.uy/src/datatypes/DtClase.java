package datatypes;

public class DtClase{

	private String nombre,  correoProfesor,  nicknameProfesor,  urlwebsite;
	byte [] imgName;
	private int minSocios,  maxSocios;
	private DtFechaHora fechaClase,  fechaRegistro;	
	
	public DtClase(String nom,  String nickP,  String emailP,  int min,  int max,  String url,  DtFechaHora fechC,  DtFechaHora fechR,  byte [] imgName) {
		nombre = nom;
		correoProfesor = emailP;
		nicknameProfesor = nickP;
		minSocios = min;
		maxSocios = max;
		urlwebsite = url;
		fechaClase = fechC;
		fechaRegistro = fechR;
		this.imgName = imgName;
	}
	
	public DtClase(String nom,  String nickP,  String emailP,  int min,  int max,  String url,  DtFechaHora fechC,  DtFechaHora fechR){
		nombre = nom;
		correoProfesor = emailP;
		nicknameProfesor = nickP;
		minSocios = min;
		maxSocios = max;
		urlwebsite = url;
		fechaClase = fechC;
		fechaRegistro = fechR;
		imgName= null;
	}
	
	public String getNombre() {
		return nombre; 
	}
	
	public String getNicknameProfesor() { 
		return nicknameProfesor; 
	}
	
	public String getCorreoProfesor() { 
		return correoProfesor;
	}
	
	public int getMinSocios() {
		return this.minSocios;
	}
	
	public int getMaxSocios() {
		return this.maxSocios;
	}
	
	public String getURL() {
		return this.urlwebsite;
	}
	
	public DtFechaHora getFechaClase() {
		return this.fechaClase;
	}
	
	public DtFechaHora getFechaRegistro() {
		return this.fechaRegistro;
	}

	public byte[] getImgName() {
		return this.imgName;
	}
}