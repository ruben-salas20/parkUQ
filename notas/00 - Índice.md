# 🅿️ ParkUQ — Guía de estudio para la exposición

> [!abstract] ¿Qué es esto?
> Tu paquete de estudio completo para entender y exponer **ParkUQ**, el sistema de gestión de parqueadero hecho como proyecto final de Programación I. Cada nota explica una parte con el código REAL del proyecto, analogías y lo que debes saber decir en la exposición.

---

## 🗺️ Ruta de estudio recomendada

Lee en este orden. Cada nota está enlazada con las demás.

1. [[01 - Visión general]] — Qué es, para qué sirve, quién lo usa.
2. [[02 - Arquitectura en capas]] — Cómo está organizado el proyecto (lo más importante).
3. [[03 - Los 4 pilares de la POO]] — Herencia, polimorfismo, abstracción, encapsulamiento. **El corazón de la nota.**
4. [[04 - Diagrama de clases explicado]] — Las clases y cómo se relacionan.
5. [[05 - Flujos principales]] — Qué pasa cuando entra y sale un vehículo.
6. [[06 - Cómo ejecutar y demostrar]] — Para que no falle nada en vivo.
7. [[07 - Preguntas y respuestas de exposición]] — Simulacro de las preguntas difíciles.
8. [[08 - Puntos débiles a defender]] — Decisiones discutibles y cómo justificarlas.

---

## 🎯 Lo mínimo que DEBES poder explicar

> [!important] Si solo tienes 10 minutos antes de exponer, repasa esto
> - **Las 4 capas**: `ui` → `servicio` → `modelo` → (`enums` + `excepcion`). Ver [[02 - Arquitectura en capas]].
> - **Los 4 pilares con un ejemplo cada uno**. Ver [[03 - Los 4 pilares de la POO]].
> - **El flujo de salida**: cómo se calcula la tarifa. Ver [[05 - Flujos principales]].
> - **Composición vs Agregación**. Ver [[04 - Diagrama de clases explicado]].

---

## 📂 Mapa del proyecto

```
trabajoFinal/
├── enums/        → valores fijos (TipoVehiculo, EstadoEspacio…)
├── excepcion/    → errores controlados del dominio
├── modelo/       → las "cosas" del mundo real (Vehiculo, Usuario…)
├── servicio/     → la lógica de negocio (Parqueadero, GestorReportes)
├── ui/           → la interfaz JavaFX (controladores + vistas .fxml)
└── test/         → pruebas unitarias JUnit 5
```

Documentos originales del proyecto que también vale la pena leer:
- `pensamiento_computacional.md` — el análisis del problema antes de codificar.
- `parkuq.puml` — el diagrama UML oficial.
- `README.md` — instrucciones de instalación.
