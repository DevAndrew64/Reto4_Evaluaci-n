# Sistema de Facturación - Comidas Rápidas Kairo's

## Autoría

**Desarrollado por:** Jhon Ponton (Jhon Andres Ponton Parra)
**Programa:** Análisis y Desarrollo de Software (ADSO) - SENA
**Ficha:** ADSO23
**Proyecto:** Reto 4 - Evaluación de trimestre

Todo el código de este repositorio fue escrito y versionado por el autor mencionado arriba. El historial de commits en la rama `features` documenta el proceso de desarrollo paso a paso.

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

```
src/
├── Main.java              # Menú de consola y flujo principal
├── Menu.java               # Catálogo de productos de Kairo's
├── Producto.java            # Modelo de producto (código, nombre, precio)
├── ItemFactura.java          # Producto + cantidad dentro de una factura
├── GestorFacturas.java        # Escritura, lectura y listado de facturas en archivos .txt
├── Moneda.java              # Utilidad de formato de moneda (Locale es-CO)
├── PruebaNumeracion.java      # Prueba manual de la numeración de facturas
└── facturas/                # Carpeta generada en tiempo de ejecución (no versionada)
```

## Tecnologías y conceptos aplicados

- Java estándar (sin frameworks ni dependencias externas).
- Manejo de archivos con la clase `File`.
- Escritura de texto con `FileWriter`.
- Lectura de texto con `FileReader` + `BufferedReader`.
- Manejo de excepciones (`try/catch`) para operaciones de E/S y de conversión de datos.
- Programación orientada a objetos: separación de responsabilidades en clases pequeñas (modelo, lógica de negocio, persistencia, interfaz de consola).

## Cómo compilar y ejecutar

```bash
cd src
javac *.java
java Main
```

Para correr la prueba manual de numeración:

```bash
java PruebaNumeracion
```

## Decisiones de diseño

- **Sin frameworks de testing:** se optó por una prueba manual sin dependencias (en vez de JUnit) para no introducir Maven/Gradle en un proyecto que se compila directamente con `javac`.
- **`Locale` fijo en es-CO:** para que el formato de los montos ($12.000 en vez de $12,000) sea siempre el mismo, sin depender de la configuración regional del sistema operativo donde corra el programa.
- **Persistencia simple en `.txt`:** siguiendo el alcance del reto, cada factura es un archivo independiente dentro de `facturas/`, en lugar de una base de datos.

## Posibles mejoras futuras

- Migrar a un build tool (Maven/Gradle) para incorporar pruebas automatizadas con JUnit.
- Guardar las facturas también en un formato estructurado (CSV/JSON) para facilitar reportes.
- Agregar la opción de anular o corregir una factura ya generada.
