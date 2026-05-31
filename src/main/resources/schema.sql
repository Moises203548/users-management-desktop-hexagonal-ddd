-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS crud_usuarios
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE crud_usuarios;

CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('ADMIN', 'MEMBER', 'REVIEWER') NOT NULL,
    status      ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED') NOT NULL DEFAULT 'PENDING',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$placeholderHashReplaceWithRealBCryptHash',
    'ADMIN',
    'ACTIVE'
);
-- =============================================
-- Script adicional: tabla residuo
-- Módulo: Gestión de Residuos Peligrosos
-- =============================================

USE crud_usuarios;

CREATE TABLE IF NOT EXISTS residuo (
    id               VARCHAR(36)   NOT NULL PRIMARY KEY,
    id_productor     VARCHAR(36)   NOT NULL,
    tipo_residuo     VARCHAR(100)  NOT NULL,
    peligroso        TINYINT(1)    NOT NULL DEFAULT 0,
    peso_kg          DECIMAL(12,3) NOT NULL,
    fecha_generacion DATE          NOT NULL,
    CONSTRAINT chk_peso_positivo CHECK (peso_kg > 0)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Datos de prueba
INSERT INTO residuo (id, id_productor, tipo_residuo, peligroso, peso_kg, fecha_generacion)
VALUES
  ('res-0001-0000-0000-000000000004', 'prod-0001-0000-0000-000000000001', 'Aceite usado',      1, 150.500, '2025-01-15'),
  ('res-0002-0000-0000-000000000005', 'prod-0001-0000-0000-000000000001', 'Cartón reciclable', 0, 320.000, '2025-02-20'),
  ('res-0003-0000-0000-000000000006', 'prod-0002-0000-0000-000000000002', 'Solvente orgánico', 1,  45.750, '2025-03-10');

