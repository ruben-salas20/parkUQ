# 07 — Preguntas y respuestas de exposición

> [!important] Cómo usar esta nota
> Tapa las respuestas e intenta responder en voz alta. Si titubeas, vuelve a la nota correspondiente. El objetivo no es memorizar: es **entender** para responder con tus palabras.

---

## 🎯 Conceptos de POO

> [!question] ¿Qué es una clase abstracta y por qué usaste `Vehiculo` abstracta?
> Es una clase que **no se puede instanciar** directamente y que puede tener métodos abstractos (sin cuerpo). La usé porque un vehículo "genérico" no existe: existe un carro, una moto o una bici. Me permite definir lo común una vez y obligar a cada subtipo a implementar `getTipoDescripcion()`. → [[03 - Los 4 pilares de la POO]]

> [!question] ¿Qué diferencia hay entre clase abstracta e interfaz?
> Una clase abstracta puede tener **atributos y métodos con código**; una interfaz (clásicamente) solo define el contrato. Una clase hereda de **una** sola clase abstracta, pero puede implementar **varias** interfaces. Aquí usé abstracta porque `Vehiculo` comparte estado (placa, conductor) y comportamiento, no solo un contrato.

> [!question] Muéstrame el polimorfismo en tu código.
> `getDescuento()`: el `Parqueadero` llama `usuario.getDescuento()` sin saber si es estudiante o docente; cada subclase devuelve su porcentaje. Lo mismo con `getTipoDescripcion()` en los vehículos. **No hay `if/else` por tipo.** → [[03 - Los 4 pilares de la POO]]

> [!question] ¿Qué es el encapsulamiento y dónde lo aplicaste?
> Proteger los datos haciéndolos `private` y exponerlos solo por métodos. Ejemplo: `EspacioParqueadero.asignarVehiculo()` cambia el vehículo Y el estado juntos, garantizando coherencia. Además, los getters de listas devuelven **copias defensivas**. → [[03 - Los 4 pilares de la POO]]

> [!question] ¿Qué es `@Override` y `super(...)`?
> `@Override` indica que un método **reemplaza** al del padre (y el compilador verifica que exista). `super(...)` llama al **constructor del padre** para inicializar los atributos heredados. Ambos están en cada subclase, ej. `Carro`.

---

## 🏛️ Arquitectura y diseño

> [!question] ¿Por qué dividiste el proyecto en paquetes (capas)?
> Por **separación de responsabilidades**: cada capa tiene un trabajo. La `ui` se encarga de mostrar; el `servicio` de las reglas; el `modelo` de los datos. Así puedo cambiar la interfaz sin tocar la lógica, y probar la lógica sin la interfaz. → [[02 - Arquitectura en capas]]

> [!question] ¿Cuál es la diferencia entre composición y agregación en tu proyecto?
> **Composición** (rombo relleno): el `Parqueadero` es dueño de sus `espacios` — no existen sin él. **Agregación** (rombo vacío): el `Parqueadero` "tiene" `vehiculos`, pero ellos existen por su cuenta (vienen de la calle). → [[04 - Diagrama de clases explicado]]

> [!question] ¿Por qué `Parqueadero` es el objeto central?
> Porque todas las operaciones necesitan los mismos datos (espacios, vehículos, tarifas). Centralizarlos en una clase evita duplicación y da un único punto de acceso a las reglas del negocio.

> [!question] ¿Diferencia entre `Usuario` y `UsuarioSistema`?
> `Usuario` es el **conductor** (con descuento según tipo). `UsuarioSistema` es quien **inicia sesión** (operador/admin). Nombres parecidos, conceptos distintos. → [[04 - Diagrama de clases explicado]]

---

## 🔢 Sobre los enums y excepciones

> [!question] ¿Por qué usaste `enum` en vez de String?
> Porque el tipo de vehículo o el estado de un espacio son **conjuntos cerrados** que no cambian. Con `enum` el **compilador** detecta valores inválidos: no puedes escribir `"CARO"` mal. Con String tendría "strings mágicos" propensos a error.

> [!question] ¿Por qué creaste tus propias excepciones?
> Para representar errores **del dominio** con mensajes claros (placa duplicada, sin espacio, vehículo no encontrado). Todas heredan de `ParkUQException`, así la interfaz las atrapa con un solo `catch`. → [[05 - Flujos principales]]

> [!question] ¿Por qué `ParkUQException extends RuntimeException` y no `Exception`?
> `RuntimeException` es **no comprobada** (unchecked): no obliga a poner `throws` en cada método. Es una decisión de diseño para no contaminar las firmas; el control se hace en el `catch` de la capa de interfaz.

---

## 💰 Sobre la lógica de negocio

> [!question] ¿Cómo se calcula lo que se cobra?
> `total = max(1, minutos/60) × valorPorHora × (1 − descuento)`. Se cobra mínimo 1 hora aunque esté menos tiempo. El descuento depende del tipo de conductor. → [[05 - Flujos principales]]

> [!question] ¿Qué pasa si entra un carro pero no hay espacios de carro?
> Se lanza `SinEspacioDisponibleException` y la interfaz muestra el error. El carro NO ocupa un espacio de moto, porque `buscarEspacioDisponible` exige que el tipo coincida.

> [!question] ¿Dónde se guardan los datos? ¿Hay base de datos?
> No hay base de datos. Los datos viven **en memoria** (listas en `Parqueadero`) mientras la app corre. Es suficiente para el alcance de Programación I. → [[08 - Puntos débiles a defender]]

---

## 🃏 Preguntas trampa (las difíciles)

> [!danger] "Tu proyecto lo hizo una IA. ¿De verdad lo entiendes?"
> Responde con HECHOS, no con nervios: explica una decisión de diseño concreta (composición vs agregación, o el polimorfismo del descuento) y por qué se tomó. **Entender el porqué es lo que demuestra dominio.** Esta guía completa existe justo para eso.

> [!danger] "¿Qué mejorarías?"
> Tienes respuestas reales en [[08 - Puntos débiles a defender]]: persistencia en base de datos, unificar `TipoVehiculo`/`TipoEspacio`, dar feedback cuando no se encuentra un espacio o tarifa. Mostrar autocrítica madura suma muchísimo.

---

🔗 Anterior: [[06 - Cómo ejecutar y demostrar]] · Siguiente: [[08 - Puntos débiles a defender]]
