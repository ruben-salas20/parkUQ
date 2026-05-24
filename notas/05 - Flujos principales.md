# 05 — Flujos principales

> [!info] Objetivo
> Seguir, paso a paso, qué pasa por dentro cuando ocurre cada operación. Si entiendes estos dos flujos (ingreso y salida), entiendes el 80% del sistema.

---

## 🟢 Flujo A — Registrar INGRESO

Método: `Parqueadero.registrarIngreso(vehiculo, idConductor)`

```mermaid
flowchart TD
    START([Operador da clic en 'Registrar ingreso']) --> CTRL["OperadorControlador<br>crea el Carro/Moto/Bici<br>según el ComboBox"]
    CTRL --> P["parqueadero.registrarIngreso(vehiculo, id)"]
    P --> DUP{"¿Ya hay un vehículo<br>activo con esa placa?"}
    DUP -->|Sí| EXDUP["❌ lanza<br>PlacaDuplicadaException"]
    DUP -->|No| SEARCH{"¿Hay un espacio<br>DISPONIBLE del<br>tipo correcto?"}
    SEARCH -->|No| EXESP["❌ lanza<br>SinEspacioDisponibleException"]
    SEARCH -->|Sí| ASIGN["espacio.asignarVehiculo()<br>→ marca OCUPADO"]
    ASIGN --> SET["vehiculo: hora=ahora,<br>estado=DENTRO,<br>espacio asignado"]
    SET --> ADD["agrega a vehiculosActivos"]
    ADD --> OK([✅ devuelve el espacio asignado])
    style EXDUP fill:#FFEBEE,stroke:#C62828
    style EXESP fill:#FFEBEE,stroke:#C62828
    style OK fill:#E8F5E9,stroke:#2E7D32
```

> [!note] Las dos validaciones
> 1. **Placa duplicada**: no puede entrar dos veces el mismo vehículo.
> 2. **Espacio disponible**: debe haber un cajón libre del tipo correcto (un carro no cabe en espacio de moto).

---

## 🔴 Flujo B — Registrar SALIDA (el más importante)

Método: `Parqueadero.registrarSalida(placa)`

```mermaid
flowchart TD
    START([Operador ingresa la placa]) --> FIND{"¿Existe ese vehículo<br>en activos?"}
    FIND -->|No| EX["❌ VehiculoNoEncontradoException"]
    FIND -->|Sí| TIME["calcula minutos =<br>ahora − horaIngreso"]
    TIME --> TARIFA["obtiene la Tarifa<br>de su tipo"]
    TARIFA --> COSTO["tarifaBase =<br>calcularCosto(minutos)"]
    COSTO --> DESC["busca descuento<br>del conductor"]
    DESC --> TOTAL["total = tarifaBase × (1 − descuento)"]
    TOTAL --> REG["crea RegistroSalida (recibo)"]
    REG --> LIB["libera el espacio<br>→ DISPONIBLE"]
    LIB --> RM["quita de activos +<br>guarda en historial"]
    RM --> OK([✅ devuelve el recibo])
    style EX fill:#FFEBEE,stroke:#C62828
    style OK fill:#E8F5E9,stroke:#2E7D32
```

### 💰 La fórmula de la tarifa (memorízala)

En `Tarifa.calcularCosto(minutos)`:

```java
double horas = minutos / 60.0;
double horasEfectivas = Math.max(1.0, horas);   // ← mínimo 1 hora
return horasEfectivas * valorPorHora;
```

Y en `Parqueadero`:

$$ \text{total} = \underbrace{\max(1, \tfrac{minutos}{60}) \times valorHora}_{\text{tarifaBase}} \times (1 - descuento) $$

> [!example] Ejemplo numérico
> Un **docente** (30% descuento) deja su **carro** ($3.000/hora) durante **90 minutos**:
> - horas = 90/60 = 1.5 → horasEfectivas = max(1, 1.5) = **1.5**
> - tarifaBase = 1.5 × 3.000 = **$4.500**
> - total = 4.500 × (1 − 0.30) = **$3.150**

> [!tip] El "mínimo 1 hora" es una regla de negocio
> Aunque el vehículo esté 5 minutos, se cobra 1 hora completa. `Math.max(1.0, horas)` lo garantiza. Es una decisión de diseño que debes saber justificar: refleja cómo cobran los parqueaderos reales.

---

## 📊 Flujo C — Reporte del día

Lo hace `GestorReportes.generarResumenDia()`. Recorre el `historialDia` y calcula:

```mermaid
flowchart LR
    H["historialDia<br>(lista de RegistroSalida)"] --> T1["Total vehículos<br>= cantidad"]
    H --> T2["Ingresos<br>= suma de totales"]
    H --> T3["Tiempo promedio<br>= suma minutos / cantidad"]
    H --> T4["Conteo por tipo<br>(carros, motos, bicis)"]
```

> [!note] GestorReportes no guarda datos
> Recibe el `Parqueadero` por el constructor (inyección de dependencia) y solo **lee** sus datos para calcular. No duplica información. Ver [[02 - Arquitectura en capas]].

---

## 🧾 El recibo y la inmutabilidad

`RegistroSalida` tiene todos sus campos `final` y **no tiene setters**:

```java
private final double totalCobrado;   // no se puede cambiar después de crearlo
```

> [!success] ¿Por qué inmutable?
> Un recibo, una vez emitido, **no debe cambiar**. Hacerlo inmutable evita que por error se modifique un cobro ya registrado. Es otra buena práctica que suma en la exposición.

---

🔗 Anterior: [[04 - Diagrama de clases explicado]] · Siguiente: [[06 - Cómo ejecutar y demostrar]]
