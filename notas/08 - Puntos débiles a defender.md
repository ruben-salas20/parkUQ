# 08 — Puntos débiles a defender

> [!important] Por qué esta nota es tu mejor arma
> Un buen estudiante conoce las **limitaciones** de su propio proyecto. Si TÚ las mencionas antes de que el profesor las encuentre, demuestras madurez técnica. No son errores graves: son **decisiones discutibles** o alcance que quedó fuera. Aquí está cada una con su justificación.

---

## 1. 🟡 `buscarTarifa()` devuelve $0 si no encuentra el tipo

En `Parqueadero.java`:

```java
private Tarifa buscarTarifa(TipoVehiculo tipo) {
    for (Tarifa t : tarifas) {
        if (t.getTipoVehiculo() == tipo) return t;
    }
    return new Tarifa(tipo, 0.0);   // ← fallback silencioso
}
```

> [!warning] El problema
> Si no existe tarifa para un tipo, en vez de avisar, **cobra $0**. Un error de configuración pasaría desapercibido.

> [!success] Cómo lo defiendes
> *"Es un fallback defensivo para que el sistema no se caiga. En una versión productiva, lo correcto sería lanzar una excepción `TarifaNoConfiguradaException` para que el administrador note que falta configurar una tarifa."*

---

## 2. 🟡 `modificarEstadoEspacio()` no avisa si el código no existe

```java
public void modificarEstadoEspacio(String codigo, EstadoEspacio nuevoEstado) {
    for (EspacioParqueadero espacio : espacios) {
        if (espacio.getCodigo().equalsIgnoreCase(codigo)) {
            espacio.setEstado(nuevoEstado);
            return;
        }
    }
    // ← si no encuentra el código, NO pasa nada (silencioso)
}
```

> [!warning] El problema
> El admin podría escribir un código equivocado y el sistema no le diría nada. Pensaría que cambió el estado cuando no fue así.

> [!success] Cómo lo defiendes
> *"Faltaría retornar un `boolean` o lanzar una excepción cuando el código no existe, para dar retroalimentación al usuario."*

---

## 3. 🟡 `TipoVehiculo` y `TipoEspacio` están duplicados

Son dos enums con los **mismos valores** (CARRO, MOTOCICLETA, BICICLETA). Por eso en `buscarEspacioDisponible` se comparan por nombre:

```java
if (espacio.getTipoEspacio().name().equals(tipo.name()) && espacio.estaDisponible())
```

> [!warning] El problema
> Comparar por `.name()` es frágil: si renombras un valor en un enum y no en el otro, se rompe silenciosamente.

> [!success] Cómo lo defiendes
> *"Los separé porque conceptualmente 'tipo de vehículo' y 'tipo de espacio' son cosas distintas. Pero como sus valores coinciden 1 a 1, un diseño más limpio usaría un solo enum compartido, evitando la comparación por nombre."*

---

## 4. 🔵 No hay persistencia (los datos se pierden al cerrar)

> [!note] El alcance
> Todo vive en **memoria**. Al cerrar la app, se pierde el historial del día y vuelve a los datos iniciales.

> [!success] Cómo lo defiendes
> *"La persistencia (base de datos o archivos) está fuera del alcance de Programación I, que se centra en POO. El diseño en capas permite agregarla después sin tocar la lógica: bastaría una capa de repositorio entre `servicio` y el almacenamiento."* → ver [[02 - Arquitectura en capas]]

---

## 5. 🔵 Las contraseñas se guardan en texto plano

En `UsuarioSistema`, la contraseña se almacena tal cual (`"1234"`, `"admin"`).

> [!success] Cómo lo defiendes
> *"En un sistema real las contraseñas deberían guardarse 'hasheadas' (ej. con BCrypt), nunca en texto plano. Para un proyecto académico de POO se priorizó la claridad del flujo de autenticación."*

---

## 6. 🔵 El reporte solo cuenta vehículos que YA salieron

`generarResumenDia()` recorre el `historialDia`, que solo tiene los que ya salieron. Los que siguen dentro no cuentan en ingresos.

> [!success] Cómo lo defiendes
> *"Es correcto: solo se cobra al salir, así que los ingresos solo incluyen vehículos que ya pagaron. El reporte sí muestra aparte cuántos siguen activos."*

---

## 📋 Resumen: tu lista de "qué mejoraría"

> [!tip] Si te preguntan "¿qué mejorarías?", di esto
> 1. **Persistencia** en base de datos para no perder los datos.
> 2. **Unificar** `TipoVehiculo` y `TipoEspacio` en un solo enum.
> 3. **Feedback de errores** cuando no se encuentra una tarifa o un espacio.
> 4. **Seguridad**: hashear las contraseñas.
>
> Mencionar esto demuestra que entiendes el proyecto MÁS ALLÁ de que "funcione".

---

🔗 Anterior: [[07 - Preguntas y respuestas de exposición]] · Volver al [[00 - Índice]]
