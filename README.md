# Sistema de Facturación - Comidas Rápidas Kairo's

## Autoría

**Desarrollado por:** 
- Jhon Ponton (Jhon Andres Ponton Parra)
- Sebastian Suarez
- Luna Barreto

**Programa:** Análisis y Desarrollo de Software (ADSO) - SENA
**Ficha:** ADSO23
**Proyecto:** Reto 4 - Evaluación de trimestre

**Link del repositorio de Github: https://github.com/DevAndrew64/Reto4_Evaluaci-n**
Se recomienda clonar el repo

El código de este repositorio fue escrito, revisado y versionado en conjunto por el equipo de desarrollo mencionado arriba. El historial de commits en la rama `features` documenta el proceso de desarrollo y las contribuciones paso a paso.

## Descripción del problema

Comidas rápidas Kairo's necesita automatizar su proceso de facturación. Este proyecto implementa un sistema de facturación por consola que permite:

- Mostrar un menú de productos disponibles.
- Registrar los productos y cantidades que pide un cliente.
- Calcular subtotal, IVA y total a pagar.
- Guardar cada factura en un archivo de texto (`.txt`), además de mostrarla en pantalla.
- Consultar el historial de facturas ya generadas.

## Funcionalidades implementadas

- **Generación de facturas** con validación de cliente, producto, cantidad y confirmación de respuesta (s/n).
- **Persistencia en archivos de texto** usando `FileWriter` para escribir y `BufferedReader`/`FileReader` para leer, dentro de la carpeta `facturas/`.
- **Numeración de facturas persistente**: el sistema revisa la carpeta `facturas/` al iniciar y continúa la numeración donde quedó, en vez de reiniciar en 1 cada vez.
- **Suma de productos repetidos**: si el mismo código de producto se agrega más de una vez en la misma factura, se suma la cantidad en lugar de crear líneas duplicadas.
- **Cancelar factura a medias** escribiendo `cancelar` en el campo de código de producto.
- **Validaciones de entrada** contra errores humanos comunes: nombre de cliente vacío, espacios accidentales en los códigos, cantidades menores o iguales a 0, y respuestas distintas a `s`/`n` en las confirmaciones.
- **Manejo de errores de E/S** (`IOException`) en todas las operaciones de archivo, y verificación de que la carpeta `facturas/` se haya creado correctamente.
- **Formato de moneda consistente** usando `Locale` es-CO, independiente de la configuración regional del equipo donde se ejecute.
- **Prueba manual** (`PruebaNumeracion.java`) que valida el cálculo del siguiente número de factura sin depender de un framework externo.

## Estructura del proyecto
