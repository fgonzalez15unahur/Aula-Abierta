package ar.edu.unahur.obj2.aula;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Etapa2 {

    @Test

    void DadoUnaOfertaConCertificacion_AgregarPackCursos_ObtengoDatos()
    {
        Pack pack = new Pack("Pack Inglés",10);
        pack.Agregar(new Curso("Inglés Básico",90.0,40));
        pack.Agregar(new Curso("Inglés Intermedio",110.0,40));
        pack.Agregar(new Curso("Conversación",60.000,20));

        Oferta oferta = new BecaInstitucional( new CertificacionOficial(pack));
        
        // assertEquals(oferta.getPrecio() , 207.200);
        assertEquals(oferta.getHoras() , 100);
        assertEquals(oferta.getDescripcion() , "Pack Inglés + Certificación oficial + Beca institucional");
    }
    @Test

    void DadoUnaOfertaConCertificacionOficialBecaInstitucional_AgregarPackCursos_ObtengoDatos()
    {
        Pack pack = new Pack("Pack Inglés",10);
        pack.Agregar(new Curso("Inglés Básico",90.0,40));
        pack.Agregar(new Curso("Inglés Intermedio",110.0,40));
        pack.Agregar(new Curso("Conversación",60.000,20));

        Oferta oferta = new CertificacionOficial( new BecaInstitucional(pack));
        
        // assertEquals(oferta.getPrecio() , 207.200);
        assertEquals(oferta.getHoras() , 100);
        assertEquals(oferta.getDescripcion() , "Pack Inglés + Beca institucional + Certificación oficial");
    }
}
