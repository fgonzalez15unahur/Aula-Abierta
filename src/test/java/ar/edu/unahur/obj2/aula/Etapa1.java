package ar.edu.unahur.obj2.aula;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class Etapa1 {
    
    @Test 
    void dadoUnCurso_AlConsultarSuPrecioDescripcionYHoras_obtenerDatos()
    {
        Oferta curso = new Curso("Programacion",100.99,40);

        assertEquals(curso.getDescripcion(), "Programacion");
        assertEquals(curso.getPrecio(), 100.99);
        assertEquals(curso.getHoras(), 40);

    }
    @Test 
    void dadoUnPack_AlConsultarNombreYDescuento_ObtenerDatos()
    {
        Oferta pack = new Pack("Pack Ingenieria",50);

        assertEquals(pack.getDescripcion(), "Pack Ingenieria");
        assertEquals(pack.getDescuento(), 50);
    }
    @Test 
    void dadoUnPack_SumaDePreciosMenosElDescuento_ObtenerPrecio()
    {
        Pack pack = new Pack("Pack Ingenieria", 10);
        Oferta cursoMatematica = new Curso("Matematica",100.00,50);
        Oferta cursoFisica = new Curso("Fisica",100.00,50);
        pack.Agregar(cursoMatematica);
        pack.Agregar(cursoFisica);
        Double precio =pack.getPrecio();
        assertEquals(precio, (100.00 + 100.00)*0.9);
    }
    @Test void DadoUnCurso_PrecioNegativa_LanzarExcepcion()
    {
        assertThrows(PrecioNegativoException.class, ()-> new Curso("Ingles", -19, 123));
    }
        @Test void DadoUnPack_DescuentoMenorA0oMayorA100_LanzarExcepcion()
    {
        assertThrows(DescuentoInvalidoException.class, ()-> new Pack("Pack Inglés",-10));
        assertThrows(DescuentoInvalidoException.class, ()-> new Pack("Pack Inglés",-200));
    }
    @Test 
    void DadoUnaOferta_AlConsultarSiIncluyeCursoPorNombre_ObtenerSiIncluye()
    {
            Oferta oferta = new Curso("Ingles", 23, 343);
            assertTrue(oferta.ContieneCurso("Ingles")); 
        
    }
    @Test 
    void dadoUnPrograma_CalcularPrecio_ObtenerResultado()
    {
        Pack pack = new Pack("Ingles",10);
        pack.Agregar(new Curso("Inglés Básico",90.0,40));
        pack.Agregar(new Curso("Inglés Intermedio",110.0,40));
        pack.Agregar(new Curso("Conversación",60.000,20));

        assertEquals( 234.000, pack.getPrecio());


    }



}
