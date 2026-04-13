# 🍽️ Sistema de Gestión de Restaurante - "El Gran Banquete - Alegría para tu paladar"

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![FlatLaf](https://img.shields.io/badge/UI-FlatLaf-blue?style=for-the-badge)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)

## 📌 Descripción del Proyecto
Este sistema está diseñado para optimizar la gestión operativa de un restaurante, permitiendo el control total sobre el menú, la organización de mesas y la administración de categorías. El enfoque principal ha sido crear una **interfaz intuitiva y moderna** inspirada en una estética de pizarra, utilizando componentes avanzados de Swing.

## 🚀 Características Principales
* **Gestión de Menú:** CRUD completo de platos con precios y descripciones.
* **Control de Mesas:** Organización visual de la disponibilidad del local.
* **Categorización:** Clasificación dinámica de productos.
* **Interfaz Moderna:** Implementación de **FlatLaf** para un acabado profesional y minimalista.
* **Diseño Personalizado:** Uso de `JTabbedPane` lateral con navegación optimizada.

## 🛠️ Stack Tecnológico
* **Lenguaje:** Java 21+
* **Persistencia:** MySQL (vía XAMPP y MySQL Community Server).
* **Librerías UI:** FlatLaf, EdinsonCor (para efectos visuales), JCalendar.
* **Arquitectura:** Patrón **MVC** (Modelo-Vista-Controlador) y **DAO** (Data Access Object).

## 📁 Estructura del Proyecto
```text
        src/
         ├── connection/  # Capa de Infraestructura (Conexión JDBC)
         ├── gui/         # Capa de Presentación (Vista)
         ├── logic/       # Capa de Negocio y Persistencia (DAO)
         ├── sql/         # Diccionario de Datos y Scripts
         └── assets/      # Recursos Estáticos
```

### 📂 Organización de Módulos:

* **`connection/`**: Contiene la lógica de bajo nivel para la comunicación con **MySQL**. Centraliza la gestión de sesiones y errores de red.
* **`gui/` (Views)**: 
    * `menu/`: El Frame principal que orquesta la navegación.
    * `panelesMenu/`: Los paneles dinámicos que se inyectan en el `JTabbedPane`.
    * `crudMantenimiento/`: Formularios específicos para la gestión de datos.
* **`logic/dao/` (Model & Controller)**: Implementa el patrón **Data Access Object**. Aquí reside la lógica de negocio y las consultas SQL, separando la interfaz de la base de datos.
* **`sql/`**: Repositorio de scripts de base de datos para asegurar que todos los miembros del equipo manejen la misma estructura de tablas.
* **`assets/`**: Almacén de recursos visuales (iconos, fondos de pizarra y texturas).

---

## 🛠️ Guía de Supervivencia para Colaboradores (Git)

Se han establecido las siguientes reglas para evitar conflictos de merge:

1. **Uso de `.gitignore`**: Está estrictamente prohibido subir las carpetas `/build`, `/dist` o archivos `.jar` pesados. El proyecto debe mantenerse ligero.
2. **Pull antes de Push**: Siempre realiza un `git pull` y resuelve conflictos locales antes de subir cambios al `master`.
3. **Mantenimiento de GUI**: Si necesitas agregar un nuevo formulario al menú, asegúrate de que sea un `JPanel` y no un `JFrame` para evitar errores de jerarquía de contenedores.
4. **Respetar el Diseño**: La configuración visual (FlatLaf, Insets y Paddings) reside en `Frm_MenuPrincipal.java`. Consulta con el encargado de Frontend antes de modificar este archivo.

---

## 🛡️ Reglas de Supervivencia para el Equipo (Protocolo Git)

### 1. 🧩 Commits Atómicos (La Regla de "Uno a la Vez")
* **Prohibido:** Subir commits gigantes que mezclen lógica de base de datos con cambios de diseño.
* **Obligatorio:** Un commit debe resolver **una sola tarea**. 
    * *Ejemplo:* `FEAT: Agregar validación en login` o `UI: Corregir margen de botones`.
* **Beneficio:** Si algo se rompe, podemos revertir solo ese cambio sin perder el trabajo de todo el día.

### 2. 🚦 Check de Compilación (Prohibido romper el Main)
* **Nadie realiza un `push` si el proyecto no compila en su máquina local.**
* Antes de subir cambios, presiona `Clean and Build` en NetBeans. Si hay "puntos rojos" de error, soluciona el problema localmente antes de compartirlo con el resto.

### 3. 🏷️ Estándar de Mensajes (Nomenclatura)
Para identificar rápidamente qué se cambió en los +50 commits, usaremos prefijos:
* `FEAT:` Funcionalidades nuevas (CRUDs, lógica).
* `FIX:` Corrección de errores o bugs.
* `UI:` Cambios de diseño, colores o interfaz (Territorio Frontend).
* `DOC:` Cambios en documentación o archivos SQL.

### 4. 🔒 Bloqueo Verbal de Archivos
Dado que compartimos archivos críticos como `ConnectionDB.java` y `Frm_MenuPrincipal.java`:
* Antes de iniciar una edición importante, **avisa por el grupo de comunicación**.
* Evita que dos personas editen el mismo archivo `.form` al mismo tiempo; esto corrompe la vista de diseño de NetBeans.

### 5. 📦 Gestión de Librerías y Peso
* **Cero Basura:** El archivo `.gitignore` debe respetarse. No subas las carpetas `/build` o `/dist`. 
* **Librerías Centralizadas:** Cualquier librería nueva (`.jar`) debe guardarse en la carpeta `/lib` del proyecto. No las dejes en descargas o carpetas personales.

### 6. ⚠️ Jerarquía de Contenedores (JPanel vs JFrame)
* **Regla de Oro:** Todos los formularios de mantenimiento deben ser creados como **JPanel Form**.
* No intentes insertar un `JFrame` dentro del `JTabbedPane` del menú principal, ya que esto genera errores de ejecución y rompe la navegación.


