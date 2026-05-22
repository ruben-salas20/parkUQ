# ParkUQ — Sistema de Gestión de Parqueadero Inteligente

Proyecto final de **Programación I** — Universidad del Quindío  
Programa de Ingeniería de Sistemas y Computación

---

## Descripción

Sistema de gestión para el Parqueadero Central de la Universidad del Quindío. Permite registrar entradas y salidas de vehículos, asignar espacios, calcular tarifas con descuentos por tipo de usuario y generar reportes del día. Cuenta con dos roles: **Operador** y **Administrador**.

---

## Requisitos previos

Antes de clonar el proyecto asegúrate de tener instalado:

| Herramienta | Versión recomendada | Descarga |
|---|---|---|
| JDK | 21 o superior | [adoptium.net](https://adoptium.net) |
| IntelliJ IDEA | Community o Ultimate | [jetbrains.com/idea](https://www.jetbrains.com/idea/download) |

---

## Instalación paso a paso

### 1. Clonar el repositorio

```bash
git clone https://github.com/TU_USUARIO/TU_REPOSITORIO.git
```

Abre IntelliJ IDEA y selecciona **Open** → navega hasta la carpeta clonada → ábrela.

---

### 2. Configurar el módulo en IntelliJ

Ve a **File → Project Structure** (`Ctrl + Alt + Shift + S`):

**Pestaña SDK:**
- En **Project SDK** selecciona tu JDK instalado (21 o superior).
- Si no aparece, haz clic en **Add SDK → JDK** y navega hasta donde lo instalaste.

**Pestaña Modules:**
- Haz clic en **+** → **New Module from Existing Sources**
- Selecciona la carpeta del proyecto clonado
- En el asistente marca `2do_semestre/src` como **Sources** (carpeta azul)
- Marca `2do_semestre/src/trabajoFinal/test` como **Tests** (carpeta verde)

---

### 3. Agregar las dependencias (JavaFX y JUnit 5)

En **File → Project Structure → Libraries**, haz clic en **+** → **From Maven** y agrega una por una:

```
org.openjfx:javafx-controls:21
org.openjfx:javafx-fxml:21
org.openjfx:javafx-graphics:21
org.openjfx:javafx-base:21
org.junit.jupiter:junit-jupiter:5.10.2
```

> IntelliJ descargará los archivos automáticamente. Asegúrate de tener conexión a internet en este paso.

---

### 4. Configurar la ejecución

Ve a **Run → Edit Configurations** → haz clic en **+** → **Application**:

| Campo | Valor |
|---|---|
| Name | `ParkUQ` |
| Main class | `trabajoFinal.ui.AppParkUQ` |

Luego haz clic en **Modify options** → activa **Add VM options** y pega lo siguiente en el campo que aparece:

```
--module-path "C:\Users\TU_USUARIO\.m2\repository\org\openjfx\javafx-controls\21\javafx-controls-21-win.jar;C:\Users\TU_USUARIO\.m2\repository\org\openjfx\javafx-fxml\21\javafx-fxml-21-win.jar;C:\Users\TU_USUARIO\.m2\repository\org\openjfx\javafx-graphics\21\javafx-graphics-21-win.jar;C:\Users\TU_USUARIO\.m2\repository\org\openjfx\javafx-base\21\javafx-base-21-win.jar" --add-modules javafx.controls,javafx.fxml
```

> **Importante:** reemplaza `TU_USUARIO` con tu nombre de usuario de Windows (el que aparece en `C:\Users\`).

---

### 5. Ejecutar el proyecto

Haz clic en el botón **Run** (▶) o presiona `Shift + F10`.

Se abrirá la pantalla de login con las siguientes credenciales de prueba:

| Rol | Usuario | Contraseña |
|---|---|---|
| Operador | `operador` | `1234` |
| Administrador | `admin` | `admin` |

---

## Estructura del proyecto

```
2do_semestre/src/trabajoFinal/
├── enums/              → TipoVehiculo, TipoEspacio, EstadoEspacio, etc.
├── excepcion/          → ParkUQException y subclases
├── modelo/             → Vehiculo, Usuario, EspacioParqueadero, Tarifa, etc.
├── servicio/           → Parqueadero (lógica central), GestorReportes
├── ui/
│   ├── AppParkUQ.java  → Punto de entrada JavaFX
│   ├── controlador/    → LoginControlador, OperadorControlador, AdminControlador
│   └── vista/          → login.fxml, operador.fxml, admin.fxml
└── test/               → Pruebas unitarias JUnit 5
```

---

## Documentación incluida

| Archivo | Contenido |
|---|---|
| `pensamiento_computacional.md` | Análisis de descomposición, patrones, abstracción y algoritmos |
| `parkuq.puml` | Diagrama de clases UML (abrir con extensión PlantUML en VS Code o en plantuml.com) |

---

## Funcionalidades

### Operador
- Registrar ingreso de vehículos (carro, motocicleta, bicicleta)
- Registrar salida con cálculo automático de tarifa y descuento
- Consultar vehículos activos y espacios disponibles
- Generar reporte del día

### Administrador
- Gestionar espacios (agregar, cambiar estado)
- Configurar tarifas por tipo de vehículo
- Registrar y eliminar usuarios autorizados con descuento

### Seguridad
- Autenticación con roles (Operador / Administrador)
- Control de errores: placa duplicada, sin espacios disponibles, vehículo no registrado