package ar.edu.unahur.obj2.aula;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Etapa2 {

    @Test

    void DadoUnaOfertaConCertificacion_AgregarPackCursos_ObtengoDatos()
    {
        Pack pack = new Pack("Pack Inglés",10);
        pack.Agregar(new Curso("Inglés Básico",90000,40));
        pack.Agregar(new Curso("Inglés Intermedio",110000,40));
        pack.Agregar(new Curso("Conversación",60000,20));

        Oferta oferta = new BecaInstitucional( new CertificacionOficial(pack));
        
         assertEquals(oferta.getPrecio() , 207200);
        assertEquals(oferta.getHoras() , 100);
        assertEquals(oferta.getDescripcion() , "Pack Inglés + Certificación oficial + Beca institucional");
    }
    @Test

    void DadoUnaOfertaConCertificacionOficialBecaInstitucional_AgregarPackCursos_ObtengoDatos()
    {
        Pack pack = new Pack("Pack Inglés",10);
        pack.Agregar(new Curso("Inglés Básico",90000,40));
        pack.Agregar(new Curso("Inglés Intermedio",110000,40));
        pack.Agregar(new Curso("Conversación",60000,20));

        Oferta oferta = new CertificacionOficial( new BecaInstitucional(pack));
        
        assertEquals(oferta.getPrecio() , 212200);
        assertEquals(oferta.getHoras() , 100);
        assertEquals(oferta.getDescripcion() , "Pack Inglés + Beca institucional + Certificación oficial");
    }
}
