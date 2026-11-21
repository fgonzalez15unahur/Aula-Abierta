package ar.edu.unahur.obj2.aula;


public interface Oferta {
    Double  getPrecio();
    String getDescripcion();
    Integer getHoras();
    Boolean ContieneCurso(String ContieneCurso);
    Integer getDescuento();
}
