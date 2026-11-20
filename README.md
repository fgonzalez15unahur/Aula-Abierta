# 🎓 Instituto de idiomas «Aula Abierta» — Recuperatorio del 2do Parcial

**Materia:** Programación Orientada a Objetos 2 — UNAHUR
**Modalidad:** recuperatorio con resolución TDD (Baby-Steps) y refactors sucesivos
**Duración:** 2 horas
**Lenguaje:** Java 21 + JUnit 5

---

## 🎯 Objetivo

Construir, etapa por etapa, el sistema de armado de ofertas formativas y gestión del catálogo de un instituto de idiomas.

El enunciado **no indica qué patrones usar**. Cada etapa agrega requerimientos que ponen a prueba el diseño de la anterior. Cuando el código empiece a doler (duplicación, `if` por tipo, explosión de clases), ese es el momento de refactorizar. Identificar **qué problema aparece** y **qué estructura lo resuelve** es parte de lo evaluado.

> ⚠️ Aplicar cualquier estructura que no responda a una necesidad real del enunciado se considera **uso forzado** y descalifica ese punto.

---

## 📋 Criterios de evaluación

- El código debe **compilar** y los tests deben **pasar**.
- Los **nombres de los tests** deben ser descriptivos (`dadoX_alY_Z`).
- Elegir el tipo de cada excepción (verificada o no verificada) aplicando el criterio visto en la cursada y **justificar la elección** en un comentario breve en el código.
- La cobertura debe ser **≥ 75 %**.
- Cada decisión de diseño debe responder a una necesidad del enunciado.
- Se aplica análisis estático anticopia.

### Ponderación

| Etapa | Puntos |
|---|---|
| Etapa 1 — Cursos y packs | 30 |
| Etapa 2 — Adicionales e inscripciones | 30 |
| Etapa 3 — Gestión del catálogo | 30 |
| Calidad general (tests, excepciones, cobertura, ausencia de usos forzados) | 10 |

### Tiempos y checkpoints de push

| Checkpoint | Tiempo orientativo | Qué debe estar en el repositorio |
|---|---|---|
| **CP-1** | ≈ 0:40 · al terminar la Etapa 1 | Catálogo cargado, packs anidados y tests en verde |
| **CP-2** | ≈ 1:20 · al terminar la Etapa 2 | Código de las Etapas 1 y 2 refactorizado |
| **CP-3** | 2:00 · al finalizar | Entrega final |

Distribución sugerida: Etapa 1 ≈ 35 min · Etapa 2 ≈ 35 min · Etapa 3 ≈ 40 min · revisión final ≈ 10 min.

---

## 🗺️ Contexto

«Aula Abierta» arma ofertas formativas. Una oferta puede ser un **curso** suelto (Inglés Básico, Conversación, un taller) o un **pack** que reúne varias ofertas bajo un nombre comercial y con un descuento propio.

### Catálogo inicial

**Cursos**

| Nombre | Precio | Horas de cursada |
|---|---|---|
| Inglés Básico | $90.000 | 40 |
| Inglés Intermedio | $110.000 | 40 |
| Conversación | $60.000 | 20 |
| Portugués Inicial | $85.000 | 30 |
| Taller de Pronunciación | $40.000 | 10 |
| Taller de Escritura | $50.000 | 15 |

**Packs**

| Pack | Descuento | Contiene |
|---|---|---|
| Pack Inglés | 10 % | Inglés Básico, Inglés Intermedio, Conversación |
| Pack Viajero | 20 % | Portugués Inicial, Taller de Pronunciación |
| Programa Integral | 25 % | Pack Inglés, Pack Viajero, Taller de Escritura |

> ℹ️ El curso *Club de Lectura* ($30.000 · 8 horas) no integra el catálogo inicial: se crea en los escenarios de la Etapa 3.

---

## 🧩 Etapa 1 — Cursos y packs

### Parte A — Packs con cursos

**Requerimientos**

1. Modelar los cursos con su nombre, su precio y sus horas de cursada. Las horas se asumen positivas y no se validan.
2. Modelar los packs con su nombre y su descuento (porcentaje entre 0 y 100).
3. Calcular el **precio** de un pack: suma de los precios de lo que contiene, menos el descuento del pack.
4. Obtener la **descripción** de un curso o pack (su nombre).
5. Informar las **horas totales** de una oferta (un curso suelto cuenta sus propias horas).
6. Consultar si una oferta **incluye** un curso por nombre.

**Restricciones**

- Lanzar `PrecioNegativoException` al crear un curso con precio menor a 0.
- Lanzar `DescuentoInvalidoException` al crear un pack con descuento menor a 0 o mayor a 100.

**Resultados esperados**

| Consulta | Resultado |
|---|---|
| Precio de *Pack Inglés* | $234.000 |
| Precio de *Pack Viajero* | $100.000 |
| Horas totales de *Pack Inglés* | 100 |
| *Pack Inglés* incluye *Conversación* | sí |
| *Pack Inglés* incluye *Portugués Inicial* | no |

### Parte B — Packs dentro de packs

El catálogo incluye *Programa Integral*, que contiene otros packs. Un pack puede contener cursos **u** otros packs, con cualquier nivel de anidamiento.

**Requerimientos**

1. Calcular el precio, las horas totales y la inclusión por nombre de *Programa Integral* con las **mismas operaciones** de la parte anterior.
2. Quien consulta una oferta **no debe saber** si tiene delante un curso suelto o un pack.

**Restricciones de diseño**

- Evitar `instanceof`, `getClass()` y enums o banderas que indiquen el tipo de una oferta.
- Evitar duplicar la lógica de recorrido entre cursos y packs.

**Resultados esperados**

| Consulta | Resultado |
|---|---|
| Precio de *Programa Integral* | $288.000 |
| Horas totales de *Programa Integral* | 155 |
| *Programa Integral* incluye *Taller de Pronunciación* | sí |
| *Programa Integral* incluye *Club de Lectura* | no |

> ℹ️ El precio de *Programa Integral* se calcula sobre el precio **ya descontado** de sus packs internos: (234.000 + 100.000 + 50.000) − 25 % = 288.000.

---

## 🧩 Etapa 2 — Adicionales e inscripciones

Los alumnos pueden contratar **adicionales** sobre cualquier oferta (curso suelto o pack completo).

| Adicional | Efecto sobre el precio | Efecto sobre las horas | Efecto sobre la descripción |
|---|---|---|---|
| Certificación oficial | + $25.000 | ninguno | agrega « + Certificación oficial» |
| Tutoría personalizada | + $15.000 | + 10 horas | agrega « + Tutoría personalizada» |
| Beca institucional | − 20 % del precio acumulado hasta ese momento | ninguno | agrega « + Beca institucional» |

**Requerimientos**

1. Poder contratar un adicional sobre cualquier oferta, y obtener una oferta que se consulta **igual que cualquier otra** (precio, descripción, horas totales, inclusión).
2. Poder combinar adicionales en **cualquier orden** y **repetir** el mismo adicional.
3. Los adicionales **no agregan cursos**: la inclusión por nombre se mantiene. Las horas cambian solo donde lo indica la tabla.
4. Un adicional **no modifica** la oferta original: quien consulta la oferta sin adicional sigue viendo el mismo precio, las mismas horas y la misma descripción.

**Restricciones de diseño**

- Evitar una clase por cada combinación de adicionales con ofertas.
- Evitar `if` o `switch` por tipo de adicional dentro de las ofertas.
- El costo de sumar un cuarto adicional debe ser **una clase nueva**, sin modificar las existentes.

**Resultados esperados**

| Oferta | Precio | Horas | Descripción |
|---|---|---|---|
| *Pack Inglés* + Certificación oficial + Beca institucional | $207.200 | 100 | `Pack Inglés + Certificación oficial + Beca institucional` |
| *Pack Inglés* + Beca institucional + Certificación oficial | $212.200 | 100 | `Pack Inglés + Beca institucional + Certificación oficial` |
| *Taller de Pronunciación* + Tutoría personalizada + Tutoría personalizada | $70.000 | 30 | `Taller de Pronunciación + Tutoría personalizada + Tutoría personalizada` |
| *Conversación* + Beca institucional + Beca institucional | $38.400 | 20 | `Conversación + Beca institucional + Beca institucional` |
| *Programa Integral* + Certificación oficial | $313.000 | 155 | `Programa Integral + Certificación oficial` |
| *Pack Viajero* + Tutoría personalizada | $115.000 | 50 | `Pack Viajero + Tutoría personalizada` |

> ℹ️ El orden importa solo para la beca: (234.000 + 25.000) × 0,80 = 207.200, pero 234.000 × 0,80 + 25.000 = 212.200.

### Inscripciones

Una **inscripción** registra el nombre del alumno y la oferta contratada (con o sin adicionales). Al crearla, **fija el importe** vigente y la descripción en ese momento.

| Dado | Cuando | Entonces |
|---|---|---|
| Inscripción de «Martina» sobre *Pack Inglés* + Tutoría personalizada | consultar su importe | $249.000 |
| Inscripción de «Martina» sobre *Pack Inglés* + Tutoría personalizada | consultar su descripción | `Pack Inglés + Tutoría personalizada` |

> ℹ️ La inscripción **no es una oferta del catálogo**: no se cotiza ni se combina. Modelarla con alguna de las estructuras anteriores se considera uso forzado.

---

## 🧩 Etapa 3 — Gestión del catálogo

El equipo académico modifica el catálogo todos los días. Se necesita un **gestor** que centralice esas modificaciones.

**Modificaciones disponibles**

| Modificación | Efecto |
|---|---|
| Agregar una oferta a un pack | Pasa a ser un componente directo del pack |
| Quitar una oferta de un pack | Deja de ser un componente directo del pack |
| Cambiar el descuento de un pack | El pack pasa a tener el nuevo descuento |

**Requerimientos**

1. **Aplicar** una modificación de inmediato. Si se aplica correctamente, queda en el **historial**.
2. **Revertir la última entrada del historial**, repetidamente, en orden inverso al de aplicación. Revertir un cambio de descuento devuelve el descuento **anterior**; revertir un agregado quita lo agregado; revertir un quitado lo vuelve a agregar.
3. **Aplicar un lote** de modificaciones en una sola operación, en el orden en que se indican. Un lote es **todo o nada**:
   - Si todas se aplican, el lote queda en el historial como **una única entrada**: un solo revertir deshace el lote completo, en orden inverso.
   - Si alguna falla, se deshacen en orden inverso las que ya se habían aplicado del lote, el catálogo queda como estaba antes del lote, **nada** del lote queda en el historial y se propaga la excepción de la que falló.

**Reglas ante errores**

- Quitar una oferta que no es componente directo del pack: lanzar `OfertaNoIncluidaException`, sin alterar el pack.
- Cambiar el descuento de un pack a un valor fuera de rango: lanzar `DescuentoInvalidoException`.
- Revertir con el historial vacío: lanzar `NadaParaRevertirException`.
- Una modificación que falla **no se registra** en el historial.

**Restricciones de diseño**

- Evitar que el gestor tenga un `if` o `switch` por tipo de modificación.
- Evitar un método distinto en el gestor por cada combinación de «modificación × individual / lote».
- El costo de sumar una cuarta modificación debe ser **una clase nueva**, sin tocar el gestor.

**Resultados esperados**

*Secuencia inmediata* partiendo del catálogo inicial. Se crea el curso *Club de Lectura* ($30.000 · 8 horas).

| Paso | Modificación | Precio de *Pack Inglés* |
|---|---|---|
| 0 | (catálogo inicial) | $234.000 |
| 1 | Agregar *Club de Lectura* a *Pack Inglés* | $261.000 |
| 2 | Cambiar el descuento de *Pack Inglés* a 20 % | $232.000 |
| 3 | Quitar *Conversación* de *Pack Inglés* | $184.000 |
| 4 | Revertir | $232.000 |
| 5 | Revertir | $261.000 |
| 6 | Revertir | $234.000 |
| 7 | Revertir | `NadaParaRevertirException` |

*Dos cambios de descuento del mismo pack:* partiendo del catálogo inicial, crear dos modificaciones (cambiar el descuento de *Pack Inglés* a 20 % y cambiar el descuento de *Pack Inglés* a 30 %) y recién entonces aplicarlas, en ese orden.

| Paso | Acción | Resultado |
|---|---|---|
| a | Aplicar ambas | Precio de *Pack Inglés*: $182.000 |
| b | Revertir | $208.000 |
| c | Revertir | $234.000 |

*Lote:* partiendo del catálogo inicial, aplicar como un solo lote las tres modificaciones de los pasos 1 a 3 de la secuencia inmediata.

| Paso | Acción | Resultado |
|---|---|---|
| a | Aplicar el lote | Precio de *Pack Inglés*: $184.000 |
| b | Revertir (una sola vez) | $234.000 |
| c | Revertir | `NadaParaRevertirException` |

*Lote con falla:* partiendo del catálogo inicial, aplicar de inmediato «Agregar *Club de Lectura* a *Pack Inglés*» y luego aplicar un lote con, en este orden: (1) cambiar el descuento de *Pack Inglés* a 20 %, (2) quitar *Taller de Pronunciación* de *Pack Inglés*.

| Paso | Acción | Resultado |
|---|---|---|
| a | Aplicar el lote | `OfertaNoIncluidaException` (*Taller de Pronunciación* no está en ese pack) |
| b | Consultar precio de *Pack Inglés* | $261.000 (el cambio de descuento del lote se deshizo) |
| c | Revertir | $234.000 (solo se revierte lo aplicado antes del lote) |
| d | Revertir | `NadaParaRevertirException` |

*Inscripción y cambios del catálogo:* partiendo del catálogo inicial, crear una inscripción de «Martina» sobre *Pack Inglés* y luego cambiar el descuento de *Pack Inglés* a 20 %.

| Consulta | Resultado |
|---|---|
| Precio de *Pack Inglés* | $208.000 |
| Importe de la inscripción de «Martina» | $234.000 |

---

## ✅ Tests mínimos (Given-When-Then)

Escribir los tests con nombres descriptivos. Ejemplos de nombres válidos:

- `dadoUnCursoConPrecioNegativo_alCrearlo_lanzaPrecioNegativoException`
- `dadoUnPackConDescuentoMayorACien_alCrearlo_lanzaDescuentoInvalidoException`
- `dadoUnPackQueContieneOtrosPacks_alContarHoras_sumaLasDeTodosLosNiveles`
- `dadoUnPackConBecaYLuegoCertificacion_alCalcularPrecio_elOrdenAlteraElPrecioFinal`
- `dadaUnaInscripcionCreada_alCambiarElDescuentoDeUnPack_elImporteInscriptoNoCambia`
- `dadoUnCambioDeDescuento_alRevertirlo_devuelveElDescuentoAnterior`
- `dadoUnLoteConUnaModificacionInvalida_alAplicarlo_dejaElCatalogoComoEstaba`

**Cobertura obligatoria**

| Etapa | Casos a cubrir |
|---|---|
| 1 | Precio con descuento · horas totales · inclusión · precio negativo · descuento inválido · precio, horas e inclusión con packs anidados |
| 2 | Cada adicional por separado · horas con y sin tutoría · combinación en ambos órdenes · adicional repetido · adicional sobre pack · la oferta original no cambia · inscripción con importe fijo |
| 3 | Cada modificación aplicada y revertida · secuencia inmediata · dos cambios de descuento del mismo pack · lote · lote con falla · revertir un lote · historial vacío · quitar algo no incluido |

---

## 📌 Recomendaciones de resolución

1. Empezar por el test más simple de la Etapa 1 y avanzar de a un paso.
2. Dejar que el código **duplique** o **incomode** antes de refactorizar. El refactor se justifica por un dolor concreto, no por anticipación.
3. Hacer un commit por cada ciclo rojo → verde → refactor.
4. Antes de cada refactor, confirmar que todos los tests están en verde.
5. Hacer push en cada checkpoint, aunque la etapa no esté completa.
