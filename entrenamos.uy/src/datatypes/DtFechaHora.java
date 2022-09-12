package datatypes;

import java.time.LocalDateTime;    
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DtFechaHora {

	private int anio, mes, dia, horas, minutos, segundos;
	public DtFechaHora() {
		LocalDateTime t = LocalDateTime.now();
		this.anio = t.getYear();
		this.mes = t.getMonthValue();
		this.dia = t.getDayOfMonth();
		this.horas = t.getHour();
		this.minutos = t.getMinute();
		this.segundos = t.getSecond();
	}
	public DtFechaHora (int anio,int mes,int dia,int horas,int minutos,int segundos) {
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
	}
	public DtFechaHora (DtFechaHora q) {
		this.anio = q.getAnio();
		this.mes = q.getMes();
		this.dia = q.getDia();
		this.horas = q.getHoras();
		this.minutos = q.getMinutos();
		this.segundos = q.getSegundos();
	}
	
	public DtFechaHora(Calendar c) {
		this.anio = c.get(Calendar.YEAR);
		this.mes = c.get(Calendar.MONTH) + 1;
		this.dia = c.get(Calendar.DAY_OF_MONTH);
		this.horas = c.get(Calendar.HOUR_OF_DAY);
		this.minutos = c.get(Calendar.MINUTE);
		this.segundos = c.get(Calendar.SECOND);
	}
	
	public int getAnio() {
		return this.anio;
	}
	
	public int getMes() {
		return this.mes;
	}
	
	public int getDia() {
		return this.dia;
	}
	
	public int getHoras() {
		return this.horas;
	}
	
	public int getMinutos() {
		return this.minutos;
	}
	
	public int getSegundos() {
		return this.segundos;
	}
	
	public String toFechaHora() {
		String res = new String();
	    res += dia + "/" + mes + "/" + anio + " - ";
	    if (horas < 10)
	        res += "0";
	    res += horas + ":";
	    if (minutos < 10)
	        res += "0";
	    res +=  minutos;
	    return res;
	}
	
	public String toFecha() {
		return dia + "/" + mes + "/" + anio;
	}
	
	public boolean esMenor(DtFechaHora fechaAComp) {
		long min1, min2 = 0;
		min1 = minutos + (horas + (dia + (mes + (anio) * 12) * 31) * 24) * 60;
		min2 = fechaAComp.getMinutos() + (fechaAComp.getHoras() + (fechaAComp.getDia() + (fechaAComp.getMes() + 
				(fechaAComp.getAnio()) * 12) * 31) * 24) * 60;
		return (min1 <= min2);
	}
	
	public boolean equals(DtFechaHora f) {
		return anio==f.getAnio() && mes==f.getMes() && dia==f.getDia() && horas==f.getHoras() && minutos==f.getMinutos() && segundos==f.getSegundos();
	}
	
	public Calendar toCalendar() {
		return new GregorianCalendar(getAnio(),getMes()-1,getDia(),getHoras(),getMinutos(),getSegundos());
	}
}