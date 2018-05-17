package Mundo;
public class Cancion {
	
	private String id;
	private String nombre;
	private int numeroEnAlbum;
	private int duracion;
	private String genero;
	private int popularidad;
	
	
	
	public Cancion(String id, String nombre, int numeroEnAlbum, int duracion, String genero, int popularidad) {
		
		this.id = id;
		this.nombre = nombre;
		this.numeroEnAlbum = numeroEnAlbum;
		this.duracion = duracion;
		this.genero = genero;
		this.popularidad = popularidad;
	}
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroEnAlbum() {
		return numeroEnAlbum;
	}

	public void setNumeroEnAlbum(int numeroEnAlbum) {
		this.numeroEnAlbum = numeroEnAlbum;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getPopularidad() {
		return popularidad;
	}

	public void setPopularidad(int popularidad) {
		this.popularidad = popularidad;
	}




	@Override
	public String toString() {
		return id + ". " + nombre + " - Numero en album:" + numeroEnAlbum + " - " 
				+ duracion + " segundos - " + genero + " - Popularidad:" + popularidad;
	}
	
	
}
