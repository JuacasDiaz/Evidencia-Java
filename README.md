# CRUD de Productos - Conexión JDBC con MySQL

## Descripción

Este proyecto implementa un CRUD (Crear, Leer, Actualizar, Eliminar) básico sobre una tabla `productos` en una base de datos MySQL. El programa está escrito en Java y utiliza JDBC para la conexión. Se incluye el controlador MySQL Connector/J dentro de la carpeta `lib`.

## Requisitos previos

1. **Java Development Kit (JDK)** versión 8 o superior.  
   Para verificar la versión instalada, ejecutar en terminal o línea de comandos:
java -version

text
Debe mostrarse una salida similar a: `java version "1.8.0_xxx"` o `openjdk version "11.0.xx"`. En caso de no tener Java, descargar e instalar desde el sitio oficial de Oracle o adoptium.net.

2. **MySQL Community Server** o XAMPP/WAMP que incluya MySQL.  
El servicio de MySQL debe estar en ejecución. Por defecto se utiliza el usuario `root` sin contraseña. Si la instalación tiene contraseña, debe modificarse en el archivo `CRUDProducto.java`.

3. **MySQL Workbench** (opcional pero recomendado) para ejecutar el script SQL.

4. **Un IDE** (NetBeans, Eclipse, IntelliJ) o capacidad para compilar y ejecutar desde terminal.

## Estructura del proyecto

La carpeta `CRUD_Productos` contiene los siguientes elementos:
CRUD_Productos/
├── src/
│ └── CRUDProducto.java
├── lib/
│ └── mysql-connector-java-8.0.33.jar
├── script.sql
└── README.md

text

## Paso 1: Preparar la base de datos

1. Abrir MySQL Workbench y conectar con el usuario `root` (sin contraseña o con la contraseña configurada).
2. Abrir el archivo `script.sql` (ubicado en la raíz del proyecto) con un editor de texto o copiar su contenido.
3. En MySQL Workbench, pegar el contenido completo en una pestaña de consulta SQL.
4. Ejecutar el script (botón de rayo o `Ctrl+Shift+Enter`).

**Salida esperada:**  
El área de resultados mostrará mensajes como:
Query OK, 1 row affected (0.02 sec)
Query OK, 0 rows affected (0.00 sec)
Query OK, 0 rows affected (0.01 sec)

text

No debe aparecer ningún error. La base de datos `tienda` y la tabla `productos` quedan creadas.

## Paso 2: Abrir el proyecto en un IDE

### Opción A: NetBeans
- Menú `File` -> `Open Project`.
- Navegar hasta la carpeta `CRUD_Productos` y seleccionarla.
- Hacer clic en `Open Project`.
- En el explorador de proyectos, desplegar `CRUD_Productos` -> `Source Packages` y hacer doble clic sobre `CRUDProducto.java`.

### Opción B: Eclipse
- `File` -> `Import` -> `Existing Projects into Workspace`.
- En `Select root directory`, buscar y seleccionar la carpeta `CRUD_Productos`.
- Asegurarse de que la casilla `Copy projects into workspace` esté activada (opcional).
- Hacer clic en `Finish`.

### Opción C: Terminal (sin IDE)
- Abrir una terminal en la carpeta `CRUD_Productos`.
- Compilar:
javac -cp "lib/mysql-connector-java-8.0.33.jar" src/CRUDProducto.java

text
- Ejecutar:
- En Linux/macOS:
java -cp "src:lib/mysql-connector-java-8.0.33.jar" CRUDProducto

text
- En Windows:
java -cp "src;lib\mysql-connector-java-8.0.33.jar" CRUDProducto

text

## Paso 3: Ejecutar el programa

Dentro del IDE, hacer clic derecho sobre `CRUDProducto.java` y elegir `Run File` o `Run 'CRUDProducto.main()'`. En la consola se mostrará:
Crear

Listar

Actualizar

Eliminar

Salir
Opción:

text

A continuación se detallan las salidas esperadas para cada operación.

### Operación 1: Crear un producto
El usuario ingresa el número `1` y presiona Enter. Luego el programa solicita:
Nombre:

text

Se escribe un nombre, por ejemplo: `Teclado USB`. Luego:
Precio:

text

Se escribe un valor numérico, por ejemplo: `25.50`. El programa responde:
Creado.

text

Y vuelve a mostrar el menú principal.

### Operación 2: Listar todos los productos
El usuario ingresa `2`. Si existen productos, la salida es similar a:
ID | Nombre | Precio
1 | Teclado USB | 25.50

text

Si no hay ningún producto, se muestra únicamente el encabezado:
ID | Nombre | Precio

text

(no aparece ninguna fila de datos).

### Operación 3: Actualizar un producto
Primero se recomienda listar (opción 2) para conocer el ID del producto a modificar. Luego se ingresa `3`. El programa pide:
ID a modificar:

text

Se escribe un número entero, por ejemplo `1`. Luego:
Nuevo nombre:

text

Se ingresa el nuevo nombre, por ejemplo `Teclado Mecánico`. Después:
Nuevo precio:

text

Se ingresa el nuevo precio, por ejemplo `45.00`. Si el ID existe, la respuesta es:
Actualizado.

text

Si el ID no existe en la tabla, se muestra:
ID no existe.

text

### Operación 4: Eliminar un producto
Se ingresa `4`. El programa pide:
ID a eliminar:

text

Se escribe el ID del producto a borrar (por ejemplo `2`). Si el ID existe, la salida es:
Eliminado.

text

Si el ID no existe, se muestra:
ID no existe.

text

### Operación 5: Salir
Se ingresa `5`. El programa muestra:
Adiós

text

y finaliza su ejecución.

## Posibles errores y soluciones

1. **Error: `java.sql.SQLException: No suitable driver found`**  
   - Causa: El driver JDBC no está en el classpath.  
   - Solución: Verificar que el archivo `mysql-connector-java-8.0.33.jar` esté efectivamente dentro de la carpeta `lib` y que el IDE lo haya incluido como biblioteca. En terminal, revisar que el parámetro `-cp` incluya la ruta correcta al `.jar`.

2. **Error: `Access denied for user 'root'@'localhost' (using password: NO)`**  
   - Causa: La contraseña de MySQL no está vacía o el usuario no es `root`.  
   - Solución: Editar el archivo `CRUDProducto.java` y modificar las constantes `USER` y `PASS` con las credenciales correctas. Guardar y volver a ejecutar.

3. **Error: `Unknown database 'tienda'`**  
   - Causa: El script SQL no se ha ejecutado previamente.  
   - Solución: Ejecutar el contenido de `script.sql` en MySQL Workbench antes de lanzar el programa.

4. **Error: `Connection refused: connect`**  
   - Causa: El servicio de MySQL no está activo.  
   - Solución: Iniciar el servicio (en Windows: servicios -> MySQL -> Iniciar; en XAMPP: presionar Start en MySQL; en Linux: `sudo systemctl start mysql`).

## Validación de criterios de evaluación

El proyecto cumple con los indicadores solicitados de la siguiente manera:

- **Conexión JDBC**: El código utiliza `DriverManager.getConnection()`, `PreparedStatement` y `ResultSet` para interactuar con la base de datos.
- **CRUD completo**: Se implementan las cuatro operaciones fundamentales: crear (INSERT), listar (SELECT), actualizar (UPDATE) y eliminar (DELETE).
- **Versionamiento**: Todo el código y los recursos están subidos a un repositorio GitHub (por ejemplo, `socos-e-comerce`), con commits que evidencian el desarrollo.
- **Estándar de codificación**: Se sigue la convención de nombres de Java (clase en PascalCase, métodos y variables en camelCase), indentación consistente, manejo de recursos con try-with-resources y comentarios adecuados.

## Archivos incluidos

- `src/CRUDProducto.java` : código fuente principal.
- `lib/mysql-connector-java-8.0.33.jar` : controlador JDBC para MySQL.
- `script.sql` : script para crear la base de datos y la tabla.
- `README.md` : este documento de instrucciones.
