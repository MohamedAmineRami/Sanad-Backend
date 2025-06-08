# Aplicación Sanad

Sanad es una aplicación full-stack diseñada para para simplificar la donación caritativa y conectar a los usuarios con causas significativas. Ofrece herramientas fáciles de usar para descubrir campañas, realizar un seguimiento de las donaciones y apoyar una variedad de iniciativas.

## 🏗️ Arquitectura

Esta aplicación está construida con una arquitectura moderna cliente-servidor:

- **Frontend**: Aplicación móvil React Native
- **Backend**: Servidor API RESTful

## 📱 Frontend

La aplicación móvil construida con React Native, proporcionando una experiencia fluida multiplataforma.

**Repositorio**: [Sanad Frontend](https://github.com/MohamedAmineRami/Sanad-Frontend)

### Características
- Compatibilidad multiplataforma (iOS y Android)
- Diseño UI/UX moderno
- Sincronización de datos en tiempo real

### Stack Tecnológico
- React Native
- TypeScript
- React Navigation

## 🔧 Backend

La aplicación del lado del servidor que maneja la lógica de negocio, procesamiento de datos y endpoints de la API.

**Repositorio**: [Sanad Backend](https://github.com/MohamedAmineRami/Sanad-Backend)

### Características
- Diseño de API RESTful
- Gestión de base de datos
- Autenticación y autorización
- Validación y procesamiento de datos

### Stack Tecnológico
- Java
- Spring Boot
- Docker
- MySQL
- Autenticación JWT

## 🚀 Comenzando

### Prerrequisitos
- Node.js (v14 o superior)
- npm o yarn
- Entorno de desarrollo React Native
- Java JDK 17
- Maven
- Docker y Docker Compose
- MySQL

### Instalación

1. **Clonar ambos repositorios:**
   ```bash
   # Frontend
   git clone https://github.com/MohamedAmineRami/Sanad-Frontend.git
   
   # Backend
   git clone https://github.com/MohamedAmineRami/Sanad-Backend.git
   ```

2. **Configurar el Backend:**
   ```bash
   cd Sanad-Backend
   # Levantar la base de datos MySQL con Docker
   docker-compose up -d
   ```

3. **Configurar el Frontend:**
   ```bash
   cd Sanad-Frontend
   npm install
   # Iniciar la aplicación
   npx expo run:android  # o npx expo run:ios
   ```

## 📝 Documentación de la API

El backend proporciona endpoints de API RESTful para:
- Autenticación de usuarios
- Gestión de datos
- Operaciones en tiempo real

## 👥 Equipo

- **Mohamed Amine Rami** - Desarrollador Full Stack
  - Frontend: [GitHub](https://github.com/MohamedAmineRami/Sanad-Frontend)
  - Backend: [GitHub](https://github.com/MohamedAmineRami/Sanad-Backend)
