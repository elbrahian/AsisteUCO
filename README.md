#  AsisteUCO

**AsisteUCO** es una aplicación web para la toma de asistencia académica, diseñada con un enfoque moderno utilizando **arquitectura limpia**, **microservicios** y tecnologías de última generación tanto en el frontend como en el backend.

---

##  Arquitectura y tecnologías

###  Backend

- **Lenguaje:** Java 21
- **Arquitectura:** Clean Architecture
- **Estilo de servicios:** Microservicios
- **Base de datos:** PostgreSQL con Hibernate (ORM)
- **Autenticación y autorización:**
  - OAuth 2.0
  - JWT (JSON Web Tokens)
  - Clasificación por roles (admin, profesor, estudiante, etc.)
- **Seguridad:** Azure Key Vault (para gestión de secretos y credenciales)
- **Mensajería y logs:** Apache Message Catalog (integración de catálogos de mensajes)
- **Email transaccional:** SendGrid

###  Frontend

- **Framework:** React
- **Herramienta de build:** Vite
- **Comunicación con backend:** API RESTful

---

##  Seguridad

La aplicación utiliza múltiples capas de seguridad:

- Autenticación vía **OAuth 2.0**
- Autorización por **roles**
- Manejo de tokens mediante **JWT**
- Gestión de claves sensibles con **Azure Key Vault**

---

##  Notificaciones

Los usuarios reciben notificaciones por correo electrónico (confirmación de asistencia, reportes, etc.) a través de **SendGrid**.

---
